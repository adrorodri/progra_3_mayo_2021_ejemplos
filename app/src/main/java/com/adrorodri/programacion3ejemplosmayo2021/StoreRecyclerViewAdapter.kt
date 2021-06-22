package com.adrorodri.programacion3ejemplosmayo2021

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StoreRecyclerViewAdapter(val context: Context, val list: List<Producto>): RecyclerView.Adapter<ProductViewHolder>() {

    var funcionMenuOptionClick: ((producto: Producto) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemListView = layoutInflater.inflate(R.layout.layout_store_list_item, parent, false)
        return ProductViewHolder(itemListView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(list[position])
        holder.imageButton.setOnClickListener {
            funcionMenuOptionClick?.invoke(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnProductClickListener(funcion: (producto: Producto) -> Unit) {
        funcionMenuOptionClick = funcion
    }
}

class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textViewTitulo: TextView = itemView.findViewById(R.id.textViewTitulo)
    val textViewSubtitulo: TextView = itemView.findViewById(R.id.textViewSubtitulo)
    val imageView: ImageView = itemView.findViewById(R.id.imageView)
    val imageButton: ImageButton = itemView.findViewById(R.id.imageButton)

    fun bind(producto: Producto) {
        textViewTitulo.text = producto.nombre
        textViewSubtitulo.text = "${producto.descripcion} - Bs. ${producto.precio}"
        imageView.setImageResource(producto.imagen)
    }
}