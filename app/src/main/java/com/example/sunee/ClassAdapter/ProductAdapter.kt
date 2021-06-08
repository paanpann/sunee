package com.example.sunee.ClassAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.sunee.DataClass.DataProduct
import com.example.sunee.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.model_cooked.view.*


class ProductAdapter(
    var context: Context,
    var itemproduct: MutableList<DataProduct>,
    var clickListener: OnItemClickListener
) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.model_cooked, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var product = itemproduct[position]
        holder.upimage(product.image)
        holder.itemView.name_product.text = product.name
        holder.itemView.details_product.text = product.details
        holder.itemView.set_product.text = product.setproduct
        holder.itemView.price_product.text = product.price.toString() + ".00 บาท"
        holder.initialize(itemproduct.get(position), clickListener)


    }

    override fun getItemCount(): Int {
        return itemproduct.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = itemView.findViewById(R.id.image_product)
        fun upimage(Image: String) {
            Picasso.get()
                .load(Image)
                .into(image)
        }

        fun initialize(itemproduct: DataProduct, action: OnItemClickListener) {
            itemView.setOnClickListener {
                action.click(itemproduct, adapterPosition)
            }
        }
    }

    interface OnItemClickListener {
        fun click(itemproduct: DataProduct, position: Int)
    }


}