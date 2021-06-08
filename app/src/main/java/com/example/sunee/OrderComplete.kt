package com.example.sunee

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.sunee.DataClass.ListData
import com.example.sunee.Obajct.DataDetails
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.cancel_dialog.view.*
import kotlinx.android.synthetic.main.cancel_dialog.view.click
import kotlinx.android.synthetic.main.complete_dialog.view.*
import kotlinx.android.synthetic.main.order_complete.*
import java.io.File
import java.io.FileOutputStream
import java.util.*


class OrderComplete : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance()
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_complete)
        showOrderComplete()
        allowClearOrder()

        ActivityCompat.requestPermissions(
            this,
            arrayOf(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE),
            PackageManager.PERMISSION_GRANTED
        )
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        savePhoto.setOnClickListener {
            takeScreenshot()
        }

        Complete.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }

    }

    private fun allowClearOrder() {
        DataDetails.listDetail.clear()
    }

    private fun takeScreenshot() {
        val now = Date()
        DateFormat.format("yyyy-MM-dd_hh:mm:ss", now)
        try {
            // image naming and path  to include sd card  appending name you choose for file
            val mPath =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    .toString() + "/" + now + ".jpg"

            // create bitmap screen capture
            val v1 = window.decorView.findViewById<View>(R.id.View)
            v1.isDrawingCacheEnabled = true
            val bitmap = Bitmap.createBitmap(v1.drawingCache)
            v1.isDrawingCacheEnabled = false
            val imageFile = File(mPath)
            val outputStream = FileOutputStream(imageFile)
            val quality = 100
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            outputStream.flush()
            outputStream.close()
            dialog()
        } catch (e: Throwable) {
            // Several error may come out with file handling or DOM
            e.printStackTrace()
        }
    }

    private fun dialog() {
        val view =
            android.view.View.inflate(this, R.layout.complete_dialog, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        view.clicks.setOnClickListener {
            dialog.dismiss()
        }
    }


    private fun showOrderComplete() {
        progressDialog = ProgressDialog(this)
        progressDialog.show()
        progressDialog.setContentView(R.layout.show_dialog)
        progressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        db.collection("myorder")
            .get().addOnSuccessListener {
                it.forEach {
                    var get = intent.getStringExtra("orderId")

                    if (it.id == get) {
                        name_customer.text = it.data.get("name").toString()
                        name_customer_s.text = it.data.get("name").toString()

                        datetime.text = it.data.get("date").toString()
                        orderId.text = it.data.get("orderId").toString()
                        phone.text = it.data.get("phone").toString()
                        Channel.text = it.data.get("payment").toString()
                        total.text = it.data.get("summary").toString() + ".00 บาท"

                        val result = StringBuffer()
                        adressed.text = result.append(it.data.getValue("adressed")).append(" ต.")
                            .append(it.data.getValue("tumbon")).append(" อ.")
                            .append(it.data.getValue("district")).append(" จ.")
                            .append(it.data.getValue("province"))
                    }
                }
                progressDialog.dismiss()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "ไม่พบข้อมูล", Toast.LENGTH_SHORT).show()
            }
    }

}

