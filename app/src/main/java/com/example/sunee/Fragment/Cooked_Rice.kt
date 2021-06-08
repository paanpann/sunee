package com.example.sunee.Fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sunee.ClassAdapter.ProductAdapter
import com.example.sunee.DataClass.DataProduct
import com.example.sunee.Details_Cooked
import com.example.sunee.Obajct.Constansts
import com.example.sunee.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_cooked__rice.*


class Cooked_Rice : Fragment(), ProductAdapter.OnItemClickListener {

    var db = FirebaseFirestore.getInstance()
    var list_product = mutableListOf<DataProduct>()
    lateinit var productAdapter: ProductAdapter
    var TAG = "Activity2"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cooked__rice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onCreated")
        showProductCooked()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    private fun setupRecyclerViewCooked() {
        productAdapter = ProductAdapter(requireContext(), list_product, this)
        recyclerView_cooked_rice.adapter = productAdapter
        recyclerView_cooked_rice.layoutManager = LinearLayoutManager(requireContext())
        recyclerView_cooked_rice.setHasFixedSize(true)

    }

    private fun showProductCooked() {
        DotsLoaderCooked.visibility = View.VISIBLE
        db.collection("cooked_rice")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    var pd = ProgressDialog(requireContext())
                    pd.setContentView(R.layout.mydialogs)
                    pd.show()
                    pd.dismiss()
                    startActivity(Intent(requireContext(), Cooked_Rice::class.java))
                    return@addSnapshotListener
                }
                if (snapshots != null) {
                    list_product.clear()
                    snapshots.forEach {
                        list_product.add(
                            DataProduct(
                                it.data.get("details").toString(),
                                it.data.get("id").toString(),
                                it.data.get("image").toString(),
                                it.data.get("name").toString(),
                                it.data.get("price").toString(),
                                it.data.get("setproduct").toString()
                            )
                        )
                    }
                }
                try {
                    DotsLoaderCooked.visibility = View.GONE
                    setupRecyclerViewCooked()

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
    }


    override fun click(itemproduct: DataProduct, position: Int) {
        val intent = Intent(requireContext(), Details_Cooked::class.java)
        Constansts.list.add(itemproduct)
        startActivity(intent)

    }
}