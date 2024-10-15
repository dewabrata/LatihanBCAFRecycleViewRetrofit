package com.juaracoding.latihanbcafrecycleviewretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.juaracoding.latihanbcafrecycleviewretrofit.R
import com.juaracoding.latihanbcafrecycleviewretrofit.model.DataKtpItem
import com.squareup.picasso.Picasso

class AdapterKTP(val dataKTP: List<DataKtpItem?>): RecyclerView.Adapter<AdapterKTP.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtNama = itemView.findViewById<TextView>(R.id.txtNama)
        val txtNoKtp = itemView.findViewById<TextView>(R.id.txtNoKtp)
        val imgKtp = itemView.findViewById<ImageView>(R.id.imgKtp)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ktp, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataKTP.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataKTP[position]

         holder.txtNama.text = data?.nama
         holder.txtNoKtp.text = data?.noKtp

        Picasso.get()
            .load(data?.fotoKtp)

            .into(holder.imgKtp);



    }
}