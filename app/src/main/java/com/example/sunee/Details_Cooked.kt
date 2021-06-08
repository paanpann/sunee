package com.example.sunee

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.sunee.DataClass.ListData
import com.example.sunee.Obajct.Constansts
import com.example.sunee.Obajct.DataDetails
import com.example.sunee.Obajct.LodeImageCooked
import kotlinx.android.synthetic.main.details__cooked.*

class Details_Cooked : AppCompatActivity() {

    var TAG = "Details"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details__cooked)
        Log.d(TAG, "onCreated")
        setDataProduct()
        setButtonCooked()
        setAmountCooked()

        icon_details.setOnClickListener {
            startActivity(Intent(this, Setting::class.java))
            onStart()
        }


    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }


    var total = 0
    var price = 0
    var amount = 1
    var nameproduct = ""
    var imageproduct = ""
    var idproduct = ""

    private fun setAmountCooked() {

        amount_Product_Cooked.text = amount.toString()
        setSummary()


        plus_Amount_Cooked.setOnClickListener {
            if (amount >= 1) {
                amount_Product_Cooked.text = "${++amount}"
                setSummary()

            }
        }
        minus_Amount_Cooked.setOnClickListener {
            if (amount > 1) {
                amount_Product_Cooked.text = "${--amount}"
                setSummary()

            }
        }


    }

    private fun setSummary() {
        total = amount * price
        price_product__Cooked.text = total.toString() + ".00 บาท"
    }

    private fun setDataProduct() {

        Constansts.list.forEach { result ->
            var detailsProductCooked = result.details
            var nameProductCooked = result.name
            var setProductCooked = result.setproduct
            var priceProductCooked = result.price
            var imageProductCooked = result.image


            details_product_Cooked.text = detailsProductCooked
            name_product_Cooked.text = nameProductCooked
            set_product_Cooked.text = setProductCooked
            progressImage.visibility = View.VISIBLE
            LodeImageCooked.uplode(this, imageProductCooked, image_product_Cooked)
            progressImage.visibility.and(View.GONE)
            price_product__Cooked.text = priceProductCooked + ".00 บาท"

            price = priceProductCooked.toInt()
            nameproduct = nameProductCooked
            imageproduct = imageProductCooked
            idproduct = result.id


        }
        Constansts.list.clear()


    }

    private fun setButtonCooked() {
        back_details_Cooked.setOnClickListener {
            onBackPressed()
        }
        confirm_button_product_Cooked.setOnClickListener {
            DataDetails.listDetail.add(
                ListData(
                    total,
                    amount,
                    nameproduct,
                    imageproduct,
                    idproduct,
                    price
                )
            )
            startActivity(Intent(this, ShoppingBasket::class.java))

        }
    }
}