package com.juaracoding.latihanbcafrecycleviewretrofit.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseDataKTP(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
) : Parcelable

@Parcelize
data class DataKtpItem(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("no_ktp")
	val noKtp: String? = null,

	@field:SerializedName("foto_ktp")
	val fotoKtp: String? = null,

	@field:SerializedName("id")
	val id: String? = null
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("data_ktp")
	val dataKtp: List<DataKtpItem?>? = null
) : Parcelable
