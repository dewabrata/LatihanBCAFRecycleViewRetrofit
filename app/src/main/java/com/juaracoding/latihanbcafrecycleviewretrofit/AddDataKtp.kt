package com.juaracoding.latihanbcafrecycleviewretrofit

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.juaracoding.latihanbcafrecycleviewretrofit.videmodel.DataKtpViewModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream

class AddDataKtp : AppCompatActivity() {

    lateinit var txtNama: EditText
    lateinit var txtNoKtp: EditText
    lateinit var imgKtp: ImageView
    lateinit var btnSend: Button
    lateinit var btnCapture: Button

    var imageUriKTP: Bitmap? = null

    private val viewModel: DataKtpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_data_ktp)

        txtNama = findViewById(R.id.txtNamaInput)
        txtNoKtp = findViewById(R.id.txtNoKtpInput)
        imgKtp = findViewById(R.id.imageView)

        btnSend = findViewById(R.id.btnSend)
        btnCapture = findViewById(R.id.button)


        viewModel.postDataKtp.observe(this){

            if(it !=null){
                finish()
            }

        }


        btnSend.setOnClickListener {

            sendData()
        }

        btnCapture.setOnClickListener {
            selectImageKTP()
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun selectImageKTP() {
        val implicitIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncherKtp.launch(implicitIntent)

    }

    private val resultLauncherKtp =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                imageUriKTP = result.data?.extras?.get("data") as Bitmap
                imgKtp.setImageBitmap(imageUriKTP)
            }

        }

    fun sendData() {
        val nama = txtNama.text.toString()
        val noKtp = txtNoKtp.text.toString()

        if (nama.isNotEmpty() && noKtp.isNotEmpty() && imageUriKTP != null) {

            val fileKTP = createFile(imageUriKTP!!, "ktp.png")


            viewModel.postDataKtp(nama,noKtp,fileKTP)



        }

    }

    fun createFile(bitmap: Bitmap, tempName: String): File {
        val file = File(cacheDir, tempName)
        val fos = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        fos.flush()
        fos.close()
        return file
    }
}