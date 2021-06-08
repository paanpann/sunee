package com.example.sunee.Fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sunee.R
import com.example.sunee.Setting
import kotlinx.android.synthetic.main.fragment_contact.*


class Contact : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callphone()
        openUri()

        icon_setup_contact.setOnClickListener {
            startActivity(Intent(requireContext(), Setting::class.java))
            onStart()
        }


    }

    private fun openUri() {
        facebook.setOnClickListener {
            var face = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.facebook.com/profile.php?id=100021630850456")
            )
            startActivity(face)
        }

        line.setOnClickListener {
            var line = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://line.me/ti/p/ALuu4VYQG9?fbclid=IwAR3J5r-5Bp-V6v_Y2AD-sYpWhcccLPTxap3-0vStS1ohg0tvmnayVtWSnbE")
            )
            startActivity(line)
        }
    }

    private fun callphone() {
        var phone1 = "0861824067"
        var phone2 = "0895546815"

        phones1.setOnClickListener {
            val intent1 = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(phone1)))
            startActivity(intent1)

        }
        phones2.setOnClickListener {
            val intent1 = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(phone2)))
            startActivity(intent1)

        }
    }
}