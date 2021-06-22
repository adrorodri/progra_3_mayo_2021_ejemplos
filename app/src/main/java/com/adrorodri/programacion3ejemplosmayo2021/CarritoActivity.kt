package com.adrorodri.programacion3ejemplosmayo2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CarritoActivity : AppCompatActivity() {

    lateinit var textViewPrueba: TextView
    val databaseController = DatabaseController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        textViewPrueba = findViewById(R.id.textViewPrueba)

//        textViewPrueba.text = AlmacenamientoTemporal.carritoDeCompras.toString()
        textViewPrueba.text = databaseController.obtenerCarrito().toString()
    }
}