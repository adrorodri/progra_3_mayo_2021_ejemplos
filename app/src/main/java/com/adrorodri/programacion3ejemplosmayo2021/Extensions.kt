package com.adrorodri.programacion3ejemplosmayo2021

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

internal inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)

fun AppCompatActivity.mostrarAlerta(titulo: String, mensaje: String, botonPositivo: String) {
    val alertDialog = AlertDialog.Builder(this).apply {
        setTitle(titulo)
        setMessage(mensaje)
        setPositiveButton(botonPositivo) { _, _ ->
            Toast.makeText(this@mostrarAlerta, "Click en OK!!", Toast.LENGTH_SHORT).show()
        }
    }.create()

    alertDialog.show()
}