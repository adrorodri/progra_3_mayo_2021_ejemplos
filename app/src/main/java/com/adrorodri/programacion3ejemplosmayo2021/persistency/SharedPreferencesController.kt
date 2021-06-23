package com.adrorodri.programacion3ejemplosmayo2021.persistency

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.adrorodri.programacion3ejemplosmayo2021.model.Usuario
import com.google.gson.Gson

class SharedPreferencesController() {
    val gson = Gson()

    fun guardarUsuario(context: Context, usuario: Usuario) {
        val sharedPreferences = context.getSharedPreferences("SharedPrefsUsuario", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("usuarioGuardado", gson.toJson(usuario))
        editor.apply()
    }

    fun obtenerUsuario(context: Context): Usuario? {
        val sharedPreferences = context.getSharedPreferences("SharedPrefsUsuario", MODE_PRIVATE)

        if(sharedPreferences.contains("usuarioGuardado")) {
            val usuarioString = sharedPreferences.getString("usuarioGuardado", null)
            val usuario = gson.fromJson(usuarioString, Usuario::class.java)
            return usuario
        } else {
            return null
        }
    }

    fun borrarUsuario() {

    }

    fun actualizarPassword() {}
}