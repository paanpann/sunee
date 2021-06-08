package com.example.sunee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.title_question4.*

class TitleQuestion4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.title_question4)

        back_titleQuestion4.setOnClickListener {
            onBackPressed()
        }

        var getTitleQuestion = intent.getStringExtra("titleMyQuestion")
        txtQuestion4.text = getTitleQuestion
    }
}