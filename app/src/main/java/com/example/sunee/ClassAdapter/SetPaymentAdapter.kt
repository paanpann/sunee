package com.example.sunee.ClassAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sunee.DataClass.SetDataPayment
import com.example.sunee.R
import kotlinx.android.synthetic.main.model_payment_channel.view.*

class SetPaymentAdapter(
    var context: Context,
    var listimg: List<SetDataPayment>,
    var onClickInItem: OnClickInItem
) :
    RecyclerView.Adapter<SetPaymentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.model_payment_channel, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var set = listimg[position]
        holder.itemView.action1_text.text = set.action1Text
        holder.itemView.action2_text.text = set.action2Text
        holder.itemView.img.setImageResource(set.img)
        holder.clickPosition(listimg.get(position), onClickInItem)
    }

    override fun getItemCount(): Int {
        return listimg.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun clickPosition(Item: SetDataPayment, action: OnClickInItem) {
            itemView.setOnClickListener {
                action.onClickInItem(Item, adapterPosition)
            }
        }
    }

    interface OnClickInItem {
        fun onClickInItem(Item: SetDataPayment, position: Int)
    }
}