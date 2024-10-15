package com.juaracoding.latihanbcafrecycleviewretrofit.videmodel

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.juaracoding.aplikasiabsensi.model.ResponseServices
import com.juaracoding.latihanbcafrecycleviewretrofit.model.ResponseDataKTP
import com.juaracoding.latihanbcafrecycleviewretrofit.services.NetworkConfig
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class DataKtpViewModel(application: Application) : AndroidViewModel(application) {
    private val _postDataKtp = MutableLiveData<ResponseServices>()

    val postDataKtp: LiveData<ResponseServices>
        get() = _postDataKtp


    private val _dataKtp = MutableLiveData<ResponseDataKTP>()
    val dataKtp: LiveData<ResponseDataKTP>
        get() = _dataKtp


    init {
        getAllDataKtp()
    }



    fun getAllDataKtp(){
        NetworkConfig().getDataKTP().getAllDataKTP().enqueue(object : Callback<ResponseDataKTP> {
            override fun onResponse(p0: Call<ResponseDataKTP>, p1: Response<ResponseDataKTP>) {
                _dataKtp.postValue(p1.body())
            }

            override fun onFailure(p0: Call<ResponseDataKTP>, p1: Throwable) {

            }

        })
    }

    fun postDataKtp(nama: String, no_ktp: String, foto_ktp: File) {
        val nama = nama.toRequestBody("text/plain".toMediaType())
        val no_ktp = no_ktp.toRequestBody("text/plain".toMediaType())
        val foto_ktp  = MultipartBody.Part.createFormData("foto_ktp", foto_ktp.getName(), RequestBody.create("image/jpg".toMediaType(), foto_ktp))


        NetworkConfig().getDataKTP().addDataKtp(nama, no_ktp, foto_ktp).enqueue(object: Callback<ResponseServices>{
            override fun onResponse(p0: Call<ResponseServices>, p1: Response<ResponseServices>) {
                _postDataKtp.postValue(p1.body())
            }

            override fun onFailure(p0: Call<ResponseServices>, p1: Throwable) {

            }

        })


    }

}