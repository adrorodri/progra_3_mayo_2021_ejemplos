package com.adrorodri.programacion3ejemplosmayo2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elementospantalla)

        val buttonContinuar = findViewById<Button>(R.id.buttonContinuar)
        val textViewBlaBlaBla = findViewById<TextView>(R.id.textViewBlaBlaBla)
        val editTextTextPersonName = findViewById<EditText>(R.id.editTextTextPersonName)

        textViewBlaBlaBla.text = "CAMBIADO DESDE EL CODIGO!"

        editTextTextPersonName.hint = "HINT DESDE EL CODIGO"

        editTextTextPersonName.visibility = View.GONE
    }
}