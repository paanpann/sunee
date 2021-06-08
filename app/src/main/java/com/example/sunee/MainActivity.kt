package com.example.sunee

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sunee.Fragment.Contact
import com.example.sunee.Fragment.Product
import com.example.sunee.Fragment.Status
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val productFragment = Product()
    val statusFragment = Status()
    val contactFragment = Contact()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        makeFragment(productFragment)
        product.setOnClickListener {
            makeFragment(productFragment)
        }
        status.setOnClickListener {
            makeFragment(statusFragment)
        }
        contact.setOnClickListener {
            makeFragment(contactFragment)
        }

    }

    private fun makeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, fragment)
            commit()
        }
    }

}