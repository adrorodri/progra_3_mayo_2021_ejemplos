package com.adrorodri.programacion3ejemplosmayo2021.persistency

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.adrorodri.programacion3ejemplosmayo2021.model.Usuario
import com.google.gson.Gson

class SharedPreferencesController() {
    val gson = Gson()

    companion object {
        private const val KEY_USUARIO = "usuarioGuardado"
    }

    fun guardarUsuario(context: Context, usuario: Usuario?) {
        val sharedPreferences = context.getSharedPreferences("SharedPrefsUsuario", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_USUARIO, gson.toJson(usuario))
        editor.apply()
    }

    fun obtenerUsuario(context: Context): Usuario? {
        val sharedPreferences = context.getSharedPreferences("SharedPrefsUsuario", MODE_PRIVATE)

        if(sharedPreferences.contains(KEY_USUARIO)) {
            val usuarioString = sharedPreferences.getString(KEY_USUARIO, null)
            val usuario = gson.fromJson(usuarioString, Usuario::class.java)
            return usuario
        } else {
            return null
        }
    }

    fun borrarUsuario(context: Context) {
        val sharedPreferences = context.getSharedPreferences("SharedPrefsUsuario", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(KEY_USUARIO)
        editor.apply()
    }

    fun actualizarPassword(context: Context, passwordNuevo: String) {
        val usuario = obtenerUsuario(context)
        usuario?.password = passwordNuevo
        guardarUsuario(context, usuario)
    }
}