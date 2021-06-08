package com.example.sunee.ClassAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.sunee.DataClass.ImageTransFerMoney
import com.example.sunee.R
import com.squareup.picasso.Picasso

class TransFerMoneyAdapter(var context: Context, var item: MutableList<ImageTransFerMoney>) :
    RecyclerView.Adapter<TransFerMoneyAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.model_qrcode, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var showitem = item[position]
        holder.showImg(showitem.imageTransFer)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = itemView.findViewById(R.id.imgTranFerMoney)
        fun showImg(Image: String) {
            Picasso.get()
                .load(Image)
                .into(image)
        }
    }
}