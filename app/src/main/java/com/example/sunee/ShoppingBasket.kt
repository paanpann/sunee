package com.example.sunee

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sunee.ClassAdapter.ListProductAdapter
import com.example.sunee.DataClass.Summary
import com.example.sunee.Obajct.DataDetails
import com.example.sunee.Obajct.MyOrder
import kotlinx.android.synthetic.main.shopping_basket.*

class ShoppingBasket : AppCompatActivity() {
    lateinit var listProductAdapter: ListProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping_basket)
        setButtonShoppingBasket()
        setAdapterShoppingBasket()
        setOnItemClickListener()
        upDateTotal()
        listProductAdapter.listProducts.addAll(DataDetails.listDetail)

        icon_Basket.setOnClickListener {
            startActivity(Intent(this, Setting::class.java))
        }


    }

    private fun setOnItemClickListener() {
        listProductAdapter.setOnItemClickListener(object : ListProductAdapter.OnItemClickListener {
            override fun deleteBasket(position: Int) {
                DataDetails.listDetail.removeAt(position)
                setClearAdapter()
                upDateTotal()
            }

            override fun plusBasket(position: Int) {
                DataDetails.listDetail[position].amount++
                DataDetails.listDetail[position].total =
                    DataDetails.listDetail[position].amount * DataDetails.listDetail[position].price
                setClearAdapter()
                upDateTotal()

            }

            override fun minusBasket(position: Int) {
                if (DataDetails.listDetail[position].amount > 1) {
                    DataDetails.listDetail[position].amount--
                    DataDetails.listDetail[position].total =
                        DataDetails.listDetail[position].amount * DataDetails.listDetail[position].price
                    setClearAdapter()
                    upDateTotal()
                }
            }
        })
    }

    var summary = 0
    private fun upDateTotal() {
        summary = DataDetails.listDetail.sumBy { it.total }
        summarys.text = summary.toString() + ".00 บาท"
    }

    private fun setClearAdapter() {
        listProductAdapter.listProducts.clear()
        listProductAdapter.listProducts.addAll(DataDetails.listDetail)
        listProductAdapter.notifyDataSetChanged()
    }

    private fun setAdapterShoppingBasket() {
        listProductAdapter = ListProductAdapter(this)
        RecyclerViewBasket.adapter = listProductAdapter
        RecyclerViewBasket.layoutManager = LinearLayoutManager(this)
        RecyclerViewBasket.setHasFixedSize(true)
    }

    private fun setButtonShoppingBasket() {
        back_shoppingBasket.setOnClickListener {
            onBackPressed()
        }
        confirm_Order.setOnClickListener {
            MyOrder.myOrder = Summary(summary)
            startActivity(Intent(this, Take_Order::class.java))
        }
    }
}