package com.pupanka.spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_ref_spinner.view.*

class RefSpinnerAdapter(
    var context: Context,
    var data: ArrayList<RefModel>,
    var listener:  ClickListener
) : RecyclerView.Adapter<RefSpinnerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ref_spinner, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(data[position].name.isNullOrEmpty()){
            holder.itemView.txtRefName.text = data[position].id
        }else{
            holder.itemView.txtRefName.text = data[position].name
        }

        holder.itemView.setOnClickListener {
            listener.onClick(data[position])
        }

    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface ClickListener{
        fun onClick(data: RefModel)
    }


}