package com.adrorodri.programacion3ejemplosmayo2021.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.AppCompatButton
import androidx.drawerlayout.widget.DrawerLayout
import com.adrorodri.programacion3ejemplosmayo2021.R
import com.adrorodri.programacion3ejemplosmayo2021.model.Usuario
import com.adrorodri.programacion3ejemplosmayo2021.persistency.SharedPreferencesController
import com.google.android.material.navigation.NavigationView

class LoginActivity : AppCompatActivity() {
    lateinit var buttonLogin: AppCompatButton

    lateinit var editTextUsuario: EditText
    lateinit var editTextPassword: EditText

    val sharedPrefsController = SharedPreferencesController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Toast.makeText(this, getString(R.string.iniciar_sesion), Toast.LENGTH_SHORT).show()

        buttonLogin = findViewById(R.id.buttonLogin)

        editTextUsuario = findViewById(R.id.editTextUsuario)
        editTextPassword = findViewById(R.id.editTextPassword)

        buttonLogin.setOnClickListener {
            val usuario = Usuario(editTextUsuario.text.toString(), editTextPassword.text.toString())
            sharedPrefsController.guardarUsuario(this, usuario)
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }

        // One Time Login!
        if(sharedPrefsController.obtenerUsuario(this) != null) {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }
    }
}