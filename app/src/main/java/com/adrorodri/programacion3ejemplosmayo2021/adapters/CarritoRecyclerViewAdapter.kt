package com.adrorodri.programacion3ejemplosmayo2021.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adrorodri.programacion3ejemplosmayo2021.R
import com.adrorodri.programacion3ejemplosmayo2021.model.ProductoCarrito
import com.bumptech.glide.Glide

class CarritoRecyclerViewAdapter(val context: Context, val list: List<ProductoCarrito>) :
    RecyclerView.Adapter<CarritoViewHolder>() {
    var funcionMenuOptionClick: ((producto: ProductoCarrito) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemListView = layoutInflater.inflate(R.layout.layout_carrito_list_item, parent, false)
        return CarritoViewHolder(itemListView)
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        holder.bind(context, list[position])
        holder.imageViewRemove.setOnClickListener {
            funcionMenuOptionClick?.invoke(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnProductClickListener(funcion: (producto: ProductoCarrito) -> Unit) {
        funcionMenuOptionClick = funcion
    }
}

class CarritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.imageView)
    val imageViewRemove: ImageView = itemView.findViewById(R.id.imageViewRemove)
    val textViewTitulo: TextView = itemView.findViewById(R.id.textViewTitulo)
    val textViewPrecioTotal: TextView = itemView.findViewById(R.id.textViewPrecioTotal)
    val textViewCantidad: TextView = itemView.findViewById(R.id.textViewCantidad)

    fun bind(context: Context, producto: ProductoCarrito) {
        Glide.with(context)
            .load(producto.imagen)
            .centerCrop()
            .placeholder(R.drawable.icon_loading)
            .into(imageView)
        textViewTitulo.text = producto.nombre
        textViewPrecioTotal.text = producto.precioTotal.toString()
        textViewCantidad.text = producto.cantidad.toString()
    }
}