package com.example.perludilindungi.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.perludilindungi.R
import com.example.perludilindungi.model.Faskes.FaskesData

class FaskesListAdapter(private val context: Activity, private val arrayList: ArrayList<FaskesData>): ArrayAdapter<FaskesData>(context, R.layout.fragment_faskes_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.fragment_faskes_item, null)
        view.findViewById<TextView>(R.id.tipeItemFaskes).text = arrayList[position].nama
        view.findViewById<TextView>(R.id.alamatItemFaskes).text = arrayList[position].alamat
        view.findViewById<TextView>(R.id.noTelpItemFaskes).text = arrayList[position].telp
        view.findViewById<TextView>(R.id.kodeItemFaskes).text = arrayList[position].kode

        return view
    }
}