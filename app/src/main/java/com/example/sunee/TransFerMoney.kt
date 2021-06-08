package com.example.sunee


import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.provider.MediaStore
import android.text.format.DateFormat
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.sunee.ClassAdapter.TransFerMoneyAdapter
import com.example.sunee.DataClass.ImageTransFerMoney
import com.example.sunee.DataClass.ListData
import com.example.sunee.Obajct.DataDetails
import com.example.sunee.Obajct.GetInformation
import com.example.sunee.Obajct.MyOrder
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlinx.android.synthetic.main.complete_dialog.view.*
import kotlinx.android.synthetic.main.transfermoney.*
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class TransFerMoney : AppCompatActivity() {

    lateinit var transFerMoneyAdapter: TransFerMoneyAdapter
    var listTranFerMoney = mutableListOf<ImageTransFerMoney>()


    var db = FirebaseFirestore.getInstance()
    lateinit var filepath: Uri
    private var storageProfilePicRef: StorageReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transfermoney)
        showImage()
        tackPermission()
        startFileChooser()

        summaryTotal.text = MyOrder.myOrder!!.summary.toString() + ".00 บาท"



        back_Transfermoney.setOnClickListener {
            onBackPressed()
        }

        submit.setOnClickListener {
            uploadFile()
        }
        icon_transfer.setOnClickListener {
            startActivity(Intent(this, Setting::class.java))
        }


    }

    private fun tackPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            PackageManager.PERMISSION_GRANTED
        )
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        saveQR.setOnClickListener {
            saveQRScreenshot()
        }

    }

    private fun saveQRScreenshot() {
        val now = Date()
        DateFormat.format("yyyy-MM-dd_hh:mm:ss", now)
        try {
            // image naming and path  to include sd card  appending name you choose for file
            val mPath =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    .toString() + "/" + now + ".jpg"

            // create bitmap screen capture
            val v1 = window.decorView.findViewById<View>(R.id.ViewPager2)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == Activity.RESULT_OK && data != null) {
            filepath = data.data!!
            var bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filepath)
            imgSlip.setImageBitmap(bitmap)
        }
    }

    private fun startFileChooser() {
        imgSlip.setOnClickListener {
            var i = Intent()
            i.setType("image/*")
            i.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(i, "เลือกรูปภาพ"), 111)
        }

    }

    private fun uploadFile() {
        var getPayment = intent.getStringExtra("payment")
        var listMyOrder = DataDetails.listDetail
        var summaryOrder = MyOrder.myOrder!!.summary


        //setDateTime
        var sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        var datetime = Date()
        var date = sdf.format(datetime).toString()

        //showDataUser
        var nameUser = GetInformation.getInformation!!.getname
        var phoneUser = GetInformation.getInformation!!.getphone
        var provinceUser = GetInformation.getInformation!!.getprovince
        var districtUser = GetInformation.getInformation!!.getdistrict
        var tumbonUser = GetInformation.getInformation!!.gettumbon
        var adressedUser = GetInformation.getInformation!!.getadressed
        var noteUser = GetInformation.getInformation!!.note

        //setOrderId
        var setDate = SimpleDateFormat("dd-MM")
        val r = Random()
        var settext = "SU-0"
        val setorderId = settext + r.nextInt(10000) + setDate.format(datetime)



        createOrder(
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
            getPayment,
            setorderId
        )

    }

    private fun createOrder(
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

        try {
            if (filepath != null) {
                var pd = ProgressDialog(this)
                pd.setContentView(R.layout.show_dialog)
                pd.show()

                storageProfilePicRef = FirebaseStorage.getInstance().reference.child("slipPayment")
                val fileRef = storageProfilePicRef!!.child("${UUID.randomUUID()}" + ".jpg")
                fileRef.putFile(filepath).addOnSuccessListener { p0 ->
                    pd.dismiss()

                    fileRef.downloadUrl.addOnSuccessListener {
                        var myOrder: MutableMap<String, Any> = HashMap()
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
                        myOrder["slip"] = it.toString()
                        myOrder["orderId"] = orderId
                        myOrder["status"] = payment
                        pd.show()

                        db.collection("myorder").document("${orderId}").set(myOrder)
                            .addOnSuccessListener { documentReference ->
                                pd.dismiss()
                                var i = Intent(this, OrderComplete::class.java)
                                i.putExtra("orderId", orderId)
                                startActivity(i)
                                finish()
                            }
                            .addOnFailureListener { e ->
                                pd.dismiss()
                                Toast.makeText(this, "ผิดพลาด", Toast.LENGTH_LONG).show()
                            }
                    }
                }.addOnFailureListener { p0 ->
                    pd.dismiss()
                    Toast.makeText(this, "ผิดพลาด", Toast.LENGTH_LONG).show()
                }.addOnProgressListener { p0 ->
                    var progress = (100.0 * p0.bytesTransferred) / p0.totalByteCount
                    pd.setMessage("กำลังดำเนินการ ${progress.toInt()}%")

                }
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    private fun showImage() {
        DotsLoaderQr.visibility = View.VISIBLE
        db.collection("QRCODE")
            .get().addOnSuccessListener { result ->
                result.forEach {
                    listTranFerMoney.add(
                        ImageTransFerMoney(
                            it.data.get("imageTransFer").toString()
                        )
                    )
                }
                Log.e("image", "${listTranFerMoney}")
                try {
                    DotsLoaderQr.visibility = View.GONE
                    setViewPager()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "ไม่พบข้อมูล", Toast.LENGTH_SHORT).show()
            }
    }

    private fun setViewPager() {
        var dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)
        transFerMoneyAdapter = TransFerMoneyAdapter(this, listTranFerMoney)
        val zoomOutPageTransformer = ZoomOutPageTransformer()
        ViewPager2.adapter = transFerMoneyAdapter
        ViewPager2.setPageTransformer { page, position ->
            zoomOutPageTransformer.transformPage(page, position)
        }
        dotsIndicator.setViewPager2(ViewPager2)
    }


}