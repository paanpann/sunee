package com.example.sunee.ClassAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.sunee.DataClass.ListData
import com.example.sunee.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.model_shoppingbasket.view.*
import java.text.DecimalFormat

class ListProductAdapter(var context: Context) :
    RecyclerView.Adapter<ListProductAdapter.ViewHolder>() {
    var listProducts = mutableListOf<ListData>()
    private var onItemClickListener: OnItemClickListener? = null

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var upLoadURLListProduct: ImageView = itemView.image_product_basket
        fun upLode(img: String) {
            Picasso.get()
                .load(img)
                .into(upLoadURLListProduct)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.model_shoppingbasket, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listProducts.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listsData = listProducts[position]
        holder.upLode(listsData.imageproduct)
        holder.itemView.name_product_basket.text = listsData.nameproduct
        holder.itemView.price_product_basket.text =
            "ราคา " + listsData.total.toString() + ".00 บาท"
        holder.itemView.amount_Product_Basket.text = listsData.amount.toString()
        holder.itemView.delete.setOnClickListener {
            onItemClickListener!!.deleteBasket(position)
        }
        holder.itemView.minus_product_basket.setOnClickListener {
            onItemClickListener!!.minusBasket(position)
        }
        holder.itemView.plus_product_basket.setOnClickListener {
            onItemClickListener!!.plusBasket(position)
        }
    }

    interface OnItemClickListener {
        fun deleteBasket(position: Int)
        fun plusBasket(position: Int)
        fun minusBasket(position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}