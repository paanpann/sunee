package com.example.sunee

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sunee.ClassAdapter.SetQuestion
import kotlinx.android.synthetic.main.question.*


class Question : AppCompatActivity(), SetQuestion.ClickItem {
    lateinit var setQuestion: SetQuestion
    val REQUEST_PHONE_CALL = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question)
        setAdapterQuestion()


        callCenters.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    REQUEST_PHONE_CALL
                )
            } else {
                callCenter()
            }
        }

        sendEmails.setOnClickListener {
            sendEmail()

        }


        back_setting.setOnClickListener {
            onBackPressed()
        }
    }

    private fun callCenter() {
        val phoneNumber = "0612658082"
        val callIntent = Intent(Intent.ACTION_CALL)

        callIntent.setData(Uri.parse("tel:" + phoneNumber))
        startActivity(callIntent)
    }

    private fun sendEmail() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/html"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("pan.jeerawan@gmail.com"))
        startActivity(intent)
    }

    private fun setAdapterQuestion() {
        var listQuestion = listOf<com.example.sunee.DataClass.SetQuestion>(
            com.example.sunee.DataClass.SetQuestion(
                "ฉันจะสั่งซื้อสินค้าได้อย่างไร?",
                R.drawable.linesetting,
                R.drawable.next
            ),
            com.example.sunee.DataClass.SetQuestion(
                "ช่องทางการชำระเงินมีกี่ช่องทาง?",
                R.drawable.linesetting,
                R.drawable.next
            ),
            com.example.sunee.DataClass.SetQuestion(
                "จะติดตามสถานะการจัดส่งได้อย่างไร?",
                R.drawable.linesetting,
                R.drawable.next
            ),
            com.example.sunee.DataClass.SetQuestion(
                "มีช่องทางการติดต่อหรือไม่?",
                R.drawable.linesetting,
                R.drawable.next
            ),

            )
        setQuestion = SetQuestion(this, listQuestion, this)
        Question.layoutManager = LinearLayoutManager(this)
        Question.setHasFixedSize(true)
        Question.adapter = setQuestion
    }

    override fun items(Item: com.example.sunee.DataClass.SetQuestion, position: Int) {
        var titleMyQuestion = Item.textMyQuestion

        when (position) {
            0 -> {
                var i = Intent(this, TitleQuestion::class.java)
                i.putExtra("titleMyQuestion", titleMyQuestion)
                startActivity(i)
            }
            1 -> {
                var i = Intent(this, TitleQuestion2::class.java)
                i.putExtra("titleMyQuestion", titleMyQuestion)
                startActivity(i)
            }
            2 -> {
                var i = Intent(this, TitleQuestion3::class.java)
                i.putExtra("titleMyQuestion", titleMyQuestion)
                startActivity(i)
            }
            3 -> {
                var i = Intent(this, TitleQuestion4::class.java)
                i.putExtra("titleMyQuestion", titleMyQuestion)
                startActivity(i)
            }

        }
    }
}