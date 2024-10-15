package com.juaracoding.latihanbcafrecycleviewretrofit.services

import com.juaracoding.aplikasiabsensi.model.ResponseServices
import com.juaracoding.latihanbcafrecycleviewretrofit.model.ResponseDataKTP



import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface DataKtpServices {

    @Multipart
    @POST("data_ktp/add")
    fun addDataKtp (@Part("nama") nama:RequestBody,
                            @Part("no_ktp") no_ktp:RequestBody,
                            @Part foto_ktp : MultipartBody.Part,

    ): Call<ResponseServices>


    @GET("data_ktp/all")
    fun getAllDataKTP(): Call<ResponseDataKTP>
}