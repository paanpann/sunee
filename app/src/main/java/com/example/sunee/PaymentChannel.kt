package com.example.sunee

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sunee.ClassAdapter.SetPaymentAdapter
import com.example.sunee.DataClass.ListData
import com.example.sunee.DataClass.SetDataPayment
import com.example.sunee.Obajct.DataDetails
import com.example.sunee.Obajct.GetInformation
import com.example.sunee.Obajct.MyOrder
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.payment_channel.*
import java.text.SimpleDateFormat
import java.util.*

class PaymentChannel : AppCompatActivity(), SetPaymentAdapter.OnClickInItem {
    lateinit var setPaymentAdapter: SetPaymentAdapter
    var db = FirebaseFirestore.getInstance()
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_channel)
        setModelRecyclerView()
        setButtonPaymentChannel()

        icon_payment.setOnClickListener {
            startActivity(Intent(this, Setting::class.java))
        }


    }

    private fun setButtonPaymentChannel() {
        back_PaymentChannel.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setModelRecyclerView() {
        var showItem = listOf<SetDataPayment>(
            SetDataPayment("E-Banking", "โอนผ่านเงินธนาคาร", R.drawable.mobilebanking),
            SetDataPayment("Cash on delivery", "เก็บเงินปลายทาง", R.drawable.collectcash)
        )
        setPaymentAdapter = SetPaymentAdapter(this, showItem, this)
        RecyclerView_Show_Payment.layoutManager = LinearLayoutManager(this)
        RecyclerView_Show_Payment.setHasFixedSize(true)
        RecyclerView_Show_Payment.adapter = setPaymentAdapter
    }

    override fun onClickInItem(Item: SetDataPayment, position: Int) {

        var payment = Item.action2Text
        var listMyOrder = DataDetails.listDetail
        var summaryOrder = MyOrder.myOrder!!.summary

        //showDataUser
        var nameUser = GetInformation.getInformation!!.getname
        var phoneUser = GetInformation.getInformation!!.getphone
        var provinceUser = GetInformation.getInformation!!.getprovince
        var districtUser = GetInformation.getInformation!!.getdistrict
        var tumbonUser = GetInformation.getInformation!!.gettumbon
        var adressedUser = GetInformation.getInformation!!.getadressed
        var noteUser = GetInformation.getInformation!!.note

        //setDateTime
        var sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        var datetime = Date()
        var date = sdf.format(datetime).toString()

        //setOrderId
        var setDate = SimpleDateFormat("dd-MM")
        val r = Random()
        var settext = "SU-0"
        val setorderId = settext + r.nextInt(10000) + setDate.format(datetime)


        when (position) {
            0 -> {
                var i = Intent(this, TransFerMoney::class.java)
                i.putExtra("payment", payment)
                startActivity(i)
            }
            1 -> {
                createMyOrder(
                    nameUser,
                    phoneUser,
                    provinceUser,
                    districtUser,
                    tumbonUser,
                    adressedUser,
                    noteUser,
                    listMyOrder,
                    date,
                    summaryOrder,
                    payment,
                    setorderId,
                )
            }
        }

    }

    private fun createMyOrder(
        name: String,
        phone: String,
        province: String,
        district: String,
        tumbon: String,
        adressed: String,
        note: String,
        list: MutableList<ListData>,
        date: String,
        summary: Int,
        payment: String,
        orderId: String

    ) {

        progressDialog = ProgressDialog(this)
        progressDialog.show()
        progressDialog.setContentView(R.layout.show_dialogs)
        progressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        val myOrder: MutableMap<String, Any> = HashMap()
        myOrder["name"] = name
        myOrder["phone"] = phone
        myOrder["province"] = province
        myOrder["district"] = district
        myOrder["tumbon"] = tumbon
        myOrder["adressed"] = adressed
        myOrder["note"] = note
        myOrder["payment"] = payment
        myOrder["list"] = list
        myOrder["date"] = date
        myOrder["summary"] = summary
        myOrder["orderId"] = orderId
        myOrder["status"] = payment

        //randomOrderID
//        var UniqueID = randomUUID().toString()
        try {
            db.collection("myorder").document("${orderId}").set(myOrder)
                .addOnSuccessListener { documentReference ->
                    progressDialog.dismiss()
                    var i = Intent(this, OrderComplete::class.java)
                    i.putExtra("orderId", orderId)
                    startActivity(i)
                    finish()
                }
                .addOnFailureListener { e ->
                    progressDialog.dismiss()
                    Toast.makeText(this, "ผิดพลาด", Toast.LENGTH_LONG).show()
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
