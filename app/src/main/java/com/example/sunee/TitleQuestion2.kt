package com.example.sunee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.title_question2.*


class TitleQuestion2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.title_question2)
        back_titleQuestion2.setOnClickListener {
            onBackPressed()
        }

        var getTitleQuestion = intent.getStringExtra("titleMyQuestion")
        txtQuestion2.text = getTitleQuestion
    }
}