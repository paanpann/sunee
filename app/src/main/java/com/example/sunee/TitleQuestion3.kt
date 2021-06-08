package com.example.sunee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.title_question3.*

class TitleQuestion3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.title_question3)

        back_titleQuestion3.setOnClickListener {
            onBackPressed()
        }

        var getTitleQuestion = intent.getStringExtra("titleMyQuestion")
        txtQuestion3.text = getTitleQuestion
    }
}