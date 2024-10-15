package com.juaracoding.latihanbcafrecycleviewretrofit

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.juaracoding.latihanbcafrecycleviewretrofit.adapter.AdapterKTP
import com.juaracoding.latihanbcafrecycleviewretrofit.videmodel.DataKtpViewModel

class MainActivity : AppCompatActivity() {

    lateinit var  lstDataKtp : RecyclerView

    private val viewModel: DataKtpViewModel by viewModels()

    lateinit var fabAdd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        lstDataKtp = findViewById(R.id.lstDataKtp)
        lstDataKtp.layoutManager = LinearLayoutManager(this)

        fabAdd = findViewById(R.id.fabAdd)
        fabAdd.setOnClickListener {
            startActivity(Intent(this, AddDataKtp::class.java))
        }


        viewModel.dataKtp.observe(this){

            lstDataKtp.adapter = AdapterKTP(it.data?.dataKtp!!)

        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllDataKtp()
    }
}