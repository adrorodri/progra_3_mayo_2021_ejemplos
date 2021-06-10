package com.adrorodri.programacion3ejemplosmayo2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class ThirdActivity : AppCompatActivity() {
    lateinit var buttonVolverConExtras: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        buttonVolverConExtras = findViewById(R.id.buttonVolverConExtras)

        val contador = intent.getIntExtra("contador", 0)
        val pedido = intent.getSerializableExtra("pedido") as Pedido

        Toast.makeText(this, "Contador: $contador", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Pedido: ${pedido.nombre}", Toast.LENGTH_SHORT).show()

        val pedidoDeSingleton = CarritoDeCompras.pedido
        Toast.makeText(this, "Pedido Singleton: ${pedidoDeSingleton?.nombre}", Toast.LENGTH_SHORT).show()

        buttonVolverConExtras.setOnClickListener {
            val intent = Intent()
            intent.putExtra("contadorModificado", contador + 100)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}