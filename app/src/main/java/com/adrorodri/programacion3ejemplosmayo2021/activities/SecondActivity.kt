package com.adrorodri.programacion3ejemplosmayo2021.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adrorodri.programacion3ejemplosmayo2021.persistency.CarritoDeCompras
import com.adrorodri.programacion3ejemplosmayo2021.model.Pedido
import com.adrorodri.programacion3ejemplosmayo2021.R
import com.google.gson.Gson

class SecondActivity: AppCompatActivity() {

    lateinit var buttonIntent: Button
    lateinit var frameLayout: FrameLayout
    lateinit var textView: TextView
    var contador = 0
    val pedidos = listOf(
        Pedido("Darth Vader", 135.50),
        Pedido("Darth 1", 15.50),
        Pedido("Vader 2", 13.50),
        Pedido("Darder 3", 45.50),
        Pedido("Darter 4", 12.0),
        Pedido("Dader 5", 1.50)
    )
    val reqCode = 1234

    val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraintlayout)
        initViews()
        buttonIntent.setOnClickListener {
            CarritoDeCompras.pedido = pedidos[0]

            val pedidosJson = gson.toJson(pedidos)

            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("contador", contador)
            intent.putExtra("pedidos", pedidosJson)
            startActivityForResult(intent, reqCode)
        }
        frameLayout.setOnClickListener {
            actualizarContador(contador + 1)
        }
        Log.d("LIFECYCLE", "onCreate SecondActivity")
    }

    fun initViews() {
        buttonIntent = findViewById(R.id.buttonIntent)
        textView = findViewById(R.id.textView)
        frameLayout = findViewById(R.id.frameLayout)
    }

    fun actualizarContador(nuevoContador: Int) {
        contador = nuevoContador
        textView.text = "CONT: $nuevoContador"
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "onStart SecondActivity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "onResume SecondActivity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "onPause SecondActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "onStop SecondActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "onDestroy SecondActivity")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("LIFECYCLE", "onRestart SecondActivity")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == reqCode) {
            Toast.makeText(this, "Contador Modificado!", Toast.LENGTH_SHORT).show()
            actualizarContador(data?.getIntExtra("contadorModificado", 0) ?: 0)
        }
    }
}