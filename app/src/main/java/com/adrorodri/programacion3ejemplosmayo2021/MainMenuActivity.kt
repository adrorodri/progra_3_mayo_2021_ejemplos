package com.adrorodri.programacion3ejemplosmayo2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainMenuActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var buttonAgregarALaLista: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        recyclerView = findViewById(R.id.recyclerView)
        buttonAgregarALaLista = findViewById(R.id.buttonAgregarALaLista)

        val menuOptionsList = mutableListOf(
            MenuOption("Adopciones", R.drawable.icon_cat, "adopta ya!!"),
            MenuOption("Perdidos", R.drawable.icon_gears, "encuentralo ya!!"),
            MenuOption("Tienda", R.drawable.icon_cat, "compra ya!!")
        )

        val adapter = OptionRecyclerViewAdapter(this, menuOptionsList)
        val layoutManager = LinearLayoutManager(this)
//        val layoutManager = GridLayoutManager(this, 2)
//        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        adapter.setOnMenuOptionClickListener { menuOption ->
            Toast.makeText(this, "Click en ${menuOption.titulo}", Toast.LENGTH_SHORT).show()
            if(menuOption.titulo == "Tienda") {
                val intent = Intent(this, TiendaActivity::class.java)
                startActivity(intent)
            }
        }

        buttonAgregarALaLista.setOnClickListener {
            menuOptionsList.add(MenuOption("Mi cuenta", R.drawable.icon_email, "Configura tu cuenta"))
            adapter.notifyDataSetChanged()
        }
    }
}