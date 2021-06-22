package com.adrorodri.programacion3ejemplosmayo2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TiendaActivity : AppCompatActivity() {

    lateinit var recyclerViewProductos: RecyclerView
    lateinit var buttonIrACarrito: Button
    val databaseController = DatabaseController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tienda)

        recyclerViewProductos = findViewById(R.id.recyclerViewProductos)
        buttonIrACarrito = findViewById(R.id.buttonIrACarrito)

        val listaProductos = listOf(
            Producto("Comida para Gato", "La mejor comida para tu gato", 17.0, R.drawable.gato),
            Producto("Comida para Perro", "La mejor comida para tu perro", 16.0, R.drawable.gato),
            Producto("Juguete para Perro", "Para que tu perro juegue todo el dia!", 34.0, R.drawable.gato),
            Producto("Juguete para Gato", "Para que tu gato juegue todo el dia!", 25.0, R.drawable.gato)
        )

        val adapter = StoreRecyclerViewAdapter(this, listaProductos)
        recyclerViewProductos.adapter = adapter
        recyclerViewProductos.layoutManager = LinearLayoutManager(this)

        adapter.setOnProductClickListener { producto ->
//            AlmacenamientoTemporal.carritoDeCompras.add(producto)
            databaseController.agregarProductoAlCarrito(producto)
            Toast.makeText(this, "Producto ${producto.nombre} agregado correctamente!", Toast.LENGTH_SHORT).show()
        }

        buttonIrACarrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }
    }
}