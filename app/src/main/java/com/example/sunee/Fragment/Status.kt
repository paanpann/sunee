package com.example.sunee.Fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sunee.R
import com.example.sunee.Setting
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.cancel_dialog.view.*
import kotlinx.android.synthetic.main.fragment_status.*


class Status : Fragment() {

    var searchStatus = FirebaseFirestore.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkStatus()

        refresh.setOnRefreshListener {
            //s1
            payments.setBackgroundResource(R.drawable.bg_changestatus)
            txt1.setTextColor(resources.getColor(R.color.textStatus))
            txt1.setText("การชำระเงิน")
            //s2
            line1.setImageResource(R.drawable.vertical_line)
            confirmorder.setBackgroundResource(R.drawable.bg_changestatus)
            txt2.setTextColor(resources.getColor(R.color.textStatus))
            //s3
            line2.setImageResource(R.drawable.vertical_line)
            delivery.setBackgroundResource(R.drawable.bg_changestatus)
            txt3.setTextColor(resources.getColor(R.color.textStatus))
            //s4
            line3.setImageResource(R.drawable.vertical_line)
            success.setBackgroundResource(R.drawable.bg_changestatus)
            txt4.setTextColor(resources.getColor(R.color.textStatus))
            refresh.isRefreshing = false
        }

        icon_setup_satus.setOnClickListener {
            startActivity(Intent(requireContext(), Setting::class.java))
            onStart()
        }

    }

    private fun checkStatus() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchStatus.collection("myorder")
                    .addSnapshotListener { snapshots, error ->
                        if (error != null) {
                            val mToast =
                                Toast.makeText(requireContext(), "ไม่พบข้อมูล", Toast.LENGTH_SHORT)
                            mToast.setGravity(Gravity.CENTER, 0, 0)
                            mToast.show()
                            return@addSnapshotListener
                        }
                        if (snapshots != null) {
                            snapshots.forEach {
//                                Log.e("see","${it.id}")
                                if (query.toString() == (it.id)) {
                                    var getStatus = it.data.get("status")
                                    var confirmOrder = "ยืนยันรายการสั่งซื้อ"
                                    var inTransits = "อยู่ในระหว่างการจัดส่ง"
                                    var ySuccess = "สำเร็จ"

                                    var payment = "เก็บเงินปลายทาง"
                                    var transFer = "โอนผ่านเงินธนาคาร"
                                    var cancel = "ยกเลิก"

                                    //เก็บเงินปลายทาง
                                    if (getStatus == payment) {
                                        //s1
                                        payments.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt1.setTextColor(resources.getColor(R.color.green))
                                        txt1.setText("เก็บเงินปลายทาง")

                                    } else if (getStatus == confirmOrder) {
                                        //s1
                                        payments.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt1.setTextColor(resources.getColor(R.color.green))
                                        txt1.setText("เก็บเงินปลายทาง")
                                        //s2
                                        line1.setImageResource(R.drawable.vertical_straightline)
                                        confirmorder.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt2.setTextColor(resources.getColor(R.color.green))

                                    } else if (getStatus == inTransits) {
                                        //s1
                                        payments.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt1.setTextColor(resources.getColor(R.color.green))
                                        txt1.setText("เก็บเงินปลายทาง")
                                        //s2
                                        line1.setImageResource(R.drawable.vertical_straightline)
                                        confirmorder.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt2.setTextColor(resources.getColor(R.color.green))
                                        //s3
                                        line2.setImageResource(R.drawable.vertical_straightline)
                                        delivery.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt3.setTextColor(resources.getColor(R.color.green))


                                    } else if (getStatus == ySuccess) {
                                        //s1
                                        payments.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt1.setTextColor(resources.getColor(R.color.green))
                                        txt1.setText("เก็บเงินปลายทาง")
                                        //s2
                                        line1.setImageResource(R.drawable.vertical_straightline)
                                        confirmorder.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt2.setTextColor(resources.getColor(R.color.green))
                                        //s3
                                        line2.setImageResource(R.drawable.vertical_straightline)
                                        delivery.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt3.setTextColor(resources.getColor(R.color.green))
                                        //s4
                                        line3.setImageResource(R.drawable.vertical_straightline)
                                        success.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt4.setTextColor(resources.getColor(R.color.green))

                                    }

                                    //ชำระเงินเเล้ว
                                    if (getStatus == transFer) {
                                        //s1
                                        payments.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt1.setTextColor(resources.getColor(R.color.green))
                                        txt1.setText("ชำระเงินแล้ว")
                                    } else if (getStatus == confirmOrder) {
                                        //s1
                                        payments.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt1.setTextColor(resources.getColor(R.color.green))
                                        txt1.setText("ชำระเงินแล้ว")
                                        //s2
                                        line1.setImageResource(R.drawable.vertical_straightline)
                                        confirmorder.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt2.setTextColor(resources.getColor(R.color.green))
                                    } else if (getStatus == inTransits) {
                                        //s1
                                        payments.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt1.setTextColor(resources.getColor(R.color.green))
                                        txt1.setText("ชำระเงินแล้ว")
                                        //s2
                                        line1.setImageResource(R.drawable.vertical_straightline)
                                        confirmorder.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt2.setTextColor(resources.getColor(R.color.green))
                                        //s3
                                        line2.setImageResource(R.drawable.vertical_straightline)
                                        delivery.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt3.setTextColor(resources.getColor(R.color.green))
                                    } else if (getStatus == ySuccess) {
                                        //s1
                                        payments.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt1.setTextColor(resources.getColor(R.color.green))
                                        txt1.setText("ชำระเงินแล้ว")
                                        //s2
                                        line1.setImageResource(R.drawable.vertical_straightline)
                                        confirmorder.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt2.setTextColor(resources.getColor(R.color.green))
                                        //s3
                                        line2.setImageResource(R.drawable.vertical_straightline)
                                        delivery.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt3.setTextColor(resources.getColor(R.color.green))
                                        //s4
                                        line3.setImageResource(R.drawable.vertical_straightline)
                                        success.setBackgroundResource(R.drawable.bg_change_green_status)
                                        txt4.setTextColor(resources.getColor(R.color.green))
                                    }


                                    if (getStatus == cancel) {
                                        val view =
                                            View.inflate(
                                                requireContext(),
                                                R.layout.cancel_dialog,
                                                null
                                            )
                                        val builder = AlertDialog.Builder(requireContext())
                                        builder.setView(view)

                                        val dialog = builder.create()
                                        dialog.show()
                                        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

                                        view.click.setOnClickListener {
                                            dialog.dismiss()
                                        }
                                    }
                                }

                            }

                        }

                    }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })
    }
}