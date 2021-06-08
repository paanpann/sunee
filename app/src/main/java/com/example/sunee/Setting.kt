package com.example.sunee

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.setting.*

class Setting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)
        getVersionNumber()

        back_setting.setOnClickListener {
            finish()
        }
        two.setOnClickListener {
            startActivity(Intent(this, Question::class.java))
        }

    }

    private fun getVersionNumber() {
        versionName.setText(BuildConfig.VERSION_NAME)
    }
}