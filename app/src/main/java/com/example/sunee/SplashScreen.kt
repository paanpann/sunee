package com.example.sunee

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.splash_screen.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        val background = object : Thread() {
            override fun run() {
                try {
                    logo.alpha = 0f
                    logo.animate().setDuration(1300).alpha(1f).withEndAction {
                        val intent = Intent(baseContext, MainActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                    }


                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}
