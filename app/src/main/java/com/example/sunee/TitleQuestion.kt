package com.example.sunee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.title_question.*

class TitleQuestion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.title_question)

        back_titleQuestion.setOnClickListener {
            onBackPressed()
        }

        var getTitleQuestion = intent.getStringExtra("titleMyQuestion")
        txtQuestion.text = getTitleQuestion
    }
}