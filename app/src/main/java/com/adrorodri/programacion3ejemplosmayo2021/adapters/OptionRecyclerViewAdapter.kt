package com.adrorodri.programacion3ejemplosmayo2021.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adrorodri.programacion3ejemplosmayo2021.model.MenuOption
import com.adrorodri.programacion3ejemplosmayo2021.R

class OptionRecyclerViewAdapter(val context: Context, val list: List<MenuOption>): RecyclerView.Adapter<OptionsViewHolder>() {

    var funcionMenuOptionClick: ((menuOption: MenuOption) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemListView = layoutInflater.inflate(R.layout.layout_options_list_item, parent, false)
        return OptionsViewHolder(itemListView)
    }

    override fun onBindViewHolder(holder: OptionsViewHolder, position: Int) {
        holder.bind(list[position])
        holder.imageButton.setOnClickListener {
            funcionMenuOptionClick?.invoke(list[position])
        }

        // En todo el elemento
//        holder.itemView.setOnClickListener {
//            funcionMenuOptionClick?.invoke(list[position])
//        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnMenuOptionClickListener(funcion: (menuOption: MenuOption) -> Unit) {
        funcionMenuOptionClick = funcion
    }
}

class OptionsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textViewTitulo: TextView = itemView.findViewById(R.id.textViewTitulo)
    val textViewSubtitulo: TextView = itemView.findViewById(R.id.textViewPrecioTotal)
    val imageView: ImageView = itemView.findViewById(R.id.imageView)
    val imageButton: ImageButton = itemView.findViewById(R.id.textViewCantidad)

    fun bind(menuOption: MenuOption) {
        textViewTitulo.text = menuOption.titulo
        textViewSubtitulo.text = menuOption.subtitulo
        imageView.setImageResource(menuOption.icono)
    }
}