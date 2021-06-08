package com.adrorodri.programacion3ejemplosmayo2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elementospantalla)

        val buttonContinuar = findViewById<Button>(R.id.buttonContinuar)
        val textViewBlaBlaBla = findViewById<TextView>(R.id.textViewBlaBlaBla)
        val editTextTextPersonName = findViewById<EditText>(R.id.editTextTextPersonName)
        val switchBoton = findViewById<Switch>(R.id.switchBoton)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        val buttonMostrarMensaje = findViewById<Button>(R.id.buttonMostrarMensaje)
        buttonMostrarMensaje.setOnClickListener {
            textViewBlaBlaBla.text = "CAMBIADO DESDE EL BOTON!"
        }
        textViewBlaBlaBla.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        switchBoton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                buttonMostrarMensaje.isEnabled = true
            } else {
                buttonMostrarMensaje.isEnabled = false
            }
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.radioButton1 -> {
                    textViewBlaBlaBla.text = "CAMBIADO DESDE EL RB 1!"
                }
                R.id.radioButton2 -> {
                    textViewBlaBlaBla.text = "CAMBIADO DESDE EL RB 2!"
                }
            }
        }

        textViewBlaBlaBla.text = "CAMBIADO DESDE EL CODIGO!"

        editTextTextPersonName.hint = "HINT DESDE EL CODIGO"

        editTextTextPersonName.visibility = View.GONE
    }
}