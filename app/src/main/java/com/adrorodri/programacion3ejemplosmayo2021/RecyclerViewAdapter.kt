package com.adrorodri.programacion3ejemplosmayo2021

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        return RecyclerViewViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }
}

class RecyclerViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

}