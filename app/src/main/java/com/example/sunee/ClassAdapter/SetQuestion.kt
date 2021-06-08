package com.example.sunee.ClassAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sunee.DataClass.SetQuestion
import com.example.sunee.R
import kotlinx.android.synthetic.main.model_question.view.*

class SetQuestion(
    var context: Context,
    var item: List<SetQuestion>,
    var clickItemQuestion: ClickItem
) :
    RecyclerView.Adapter<com.example.sunee.ClassAdapter.SetQuestion.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.model_question, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var setItem = item[position]
        holder.itemView.myQuestion.text = setItem.textMyQuestion
        holder.itemView.imgQuestion.setImageResource(setItem.imgicon)
        holder.itemView.lineImgQuestion.setImageResource(setItem.imgline)
        holder.clickPosition(item.get(position), clickItemQuestion)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun clickPosition(Item: SetQuestion, action: ClickItem) {
            itemView.setOnClickListener {
                action.items(Item, adapterPosition)
            }
        }
    }

    interface ClickItem {
        fun items(Item: SetQuestion, position: Int)
    }
}