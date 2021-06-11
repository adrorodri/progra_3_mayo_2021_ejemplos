package com.adrorodri.programacion3ejemplosmayo2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject

class ThirdActivity : AppCompatActivity() {
    lateinit var buttonVolverConExtras: Button

    val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        buttonVolverConExtras = findViewById(R.id.buttonVolverConExtras)

        val contador = intent.getIntExtra("contador", 0)
//        val pedido = intent.getSerializableExtra("pedido") as Pedido
        val pedidosJson = intent.getStringExtra("pedidos")

        val pedidos: List<Pedido> = gson.fromJson(pedidosJson!!)

//        val jsonArray = JSONArray()
//        jsonArray.put("123123")
//        jsonArray.put("123123")
//        jsonArray.put("123123")
//        jsonArray.put("123123")
//
//        val json = JSONObject()
//        json.put("nombre", "Darth Vader")
//        json.put("total", 10.0)
//        json.put("productos", jsonArray)
//        json.toString()

        Toast.makeText(this, "Contador: $contador", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Pedidos: $pedidos", Toast.LENGTH_LONG).show()

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