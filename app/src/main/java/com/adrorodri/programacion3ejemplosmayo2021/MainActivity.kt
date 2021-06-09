package com.adrorodri.programacion3ejemplosmayo2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    var buttonContinuar: Button? = null
    var textViewBlaBlaBla: TextView? = null
    var editTextTextPersonName: EditText? = null
    var switchBoton: Switch? = null
    var checkBox: CheckBox? = null
    var radioGroup: RadioGroup? = null
    var imageViewGato: ImageView? = null
    var buttonMostrarMensaje: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elementospantalla)
        Log.d("LIFECYCLE", "onCreate MainActivity")

        initViews()

        buttonMostrarMensaje?.setOnClickListener {
            textViewBlaBlaBla?.text = "CAMBIADO DESDE EL BOTON!"
        }
        textViewBlaBlaBla?.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        switchBoton?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                buttonMostrarMensaje?.isEnabled = true
            } else {
                buttonMostrarMensaje?.isEnabled = false
            }
        }

        radioGroup?.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.radioButton1 -> {
                    imageViewGato?.scaleType = ImageView.ScaleType.CENTER_CROP
                }
                R.id.radioButton2 -> {
                    imageViewGato?.scaleType = ImageView.ScaleType.CENTER_INSIDE
                }
                R.id.radioButton3 -> {
                    imageViewGato?.scaleType = ImageView.ScaleType.FIT_XY
                }
            }
        }

        textViewBlaBlaBla?.text = "CAMBIADO DESDE EL CODIGO!"

        editTextTextPersonName?.hint = "HINT DESDE EL CODIGO"

        editTextTextPersonName?.visibility = View.GONE
    }

    fun initViews() {
        buttonContinuar = findViewById(R.id.buttonContinuar)
        textViewBlaBlaBla = findViewById(R.id.textViewBlaBlaBla)
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName)
        switchBoton = findViewById(R.id.switchBoton)
        checkBox = findViewById(R.id.checkBox)
        radioGroup = findViewById(R.id.radioGroup)
        imageViewGato = findViewById(R.id.imageViewGato)
        buttonMostrarMensaje = findViewById(R.id.buttonMostrarMensaje)
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "onStart MainActivity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "onResume MainActivity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "onPause MainActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "onStop MainActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "onDestroy MainActivity")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("LIFECYCLE", "onRestart MainActivity")
    }
}