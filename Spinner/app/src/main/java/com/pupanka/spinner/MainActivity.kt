package com.pupanka.spinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val programming_list = resources.getStringArray(R.array.prog_language)

        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, programming_list)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if(position != 0) {
                        Toast.makeText(
                            this@MainActivity,
                            "" + programming_list[position],
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // doing some action if nothing selected
                }
            }
        }

        val dataGender = arrayListOf<RefModel>()
        dataGender.add(RefModel("1", "Apple"))
        dataGender.add(RefModel("2", "Banana"))
        dataGender.add(RefModel("3", "Mango"))
        dataGender.add(RefModel("4", "Orange"))
        dataGender.add(RefModel("5", "Watermelon"))

        txt_gender.setOnClickListener {
            RefView(this, "Fruit", dataGender) {
                txt_gender.setText(it.name)
//                pGender = it.id
//                validateInput()
            }.show()
        }

    }
}

open class RefModel(
    @SerializedName("id") @Expose var id: String? = "",
    @SerializedName("name") @Expose var name: String? = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RefModel> {
        override fun createFromParcel(parcel: Parcel): RefModel {
            return RefModel(parcel)
        }

        override fun newArray(size: Int): Array<RefModel?> {
            return arrayOfNulls(size)
        }
    }
}