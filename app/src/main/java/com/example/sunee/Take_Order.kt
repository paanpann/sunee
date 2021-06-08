package com.example.sunee

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.sunee.DataClass.GetData
import com.example.sunee.Obajct.GetInformation
import kotlinx.android.synthetic.main.take__order.*


class Take_Order : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.take__order)
        setButtonTakeOrder()
        getinformation()

        icon_TakeOrder.setOnClickListener {
            startActivity(Intent(this, Setting::class.java))
        }
    }

    var getName = ""
    var getPhone = ""
    var getProvince = ""
    var getDistrict = ""
    var getTumBon = ""
    var getAdressed = ""
    var getNote = ""


    private fun getinformation() {

        confirms_OrderInTakeOrder.setOnClickListener {
            getName = name.text.toString().trim()
            getPhone = phone.text.toString().trim()
            getProvince = province.text.toString().trim()
            getDistrict = district.text.toString().trim()
            getTumBon = tumbon.text.toString().trim()
            getAdressed = adressed.text.toString().trim()
            getNote = note.text.toString().trim()

            if (name.text.isEmpty()) {
                name.error = "กรุณากรอกข้อมูล"
                return@setOnClickListener
            } else if (getName.length < 3) {
                name.error = "ควรมีอักษรมากกว่า 3 ตัวอักษรหรือมากกว่านั้น"
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(getPhone)) {
                phone.error = "กรุณากรอกเบอร์มือถือ"
                return@setOnClickListener
            } else if (getPhone.length < 10) {
                phone.error = "กรุณากรอกหมายเลขโทรศัพท์ให้ครบ 10 หลัก"
                return@setOnClickListener
            }

            if (province.text.isEmpty()) {
                province.error = "กรุณากรอกข้อมูล"
                return@setOnClickListener
            }
            if (district.text.isEmpty()) {
                district.error = "กรุณากรอกข้อมูล"
                return@setOnClickListener
            }
            if (tumbon.text.isEmpty()) {
                tumbon.error = "กรุณากรอกข้อมูล"
                return@setOnClickListener
            }
            if (adressed.text.isEmpty()) {
                adressed.error = "กรุณากรอกข้อมูล"
                return@setOnClickListener
            }

            atGetData()
        }

    }

    private fun atGetData() {
        GetInformation.getInformation =
            GetData(getName, getPhone, getProvince, getDistrict, getTumBon, getAdressed, getNote)
        startActivity(Intent(this, PaymentChannel::class.java))
    }

    private fun setButtonTakeOrder() {
        back_TakeOrder.setOnClickListener {
            onBackPressed()
        }

    }
}