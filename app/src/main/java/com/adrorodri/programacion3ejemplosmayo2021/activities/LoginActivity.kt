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
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var buttonLogin: AppCompatButton
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView

    lateinit var editTextUsuario: EditText
    lateinit var editTextPassword: EditText

    val sharedPrefsController = SharedPreferencesController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Toast.makeText(this, getString(R.string.iniciar_sesion), Toast.LENGTH_SHORT).show()

        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        buttonLogin = findViewById(R.id.buttonLogin)
        navigationView = findViewById(R.id.navigationView)

        editTextUsuario = findViewById(R.id.editTextUsuario)
        editTextPassword = findViewById(R.id.editTextPassword)

        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_ajustes -> {
                    Toast.makeText(this, "Click en Ajustes", Toast.LENGTH_SHORT).show()
                }
                R.id.menu_item_acerca_de -> {
                    Toast.makeText(this, "Click en Acerca de", Toast.LENGTH_SHORT).show()
                }
                R.id.menu_item_cerrar_sesion -> {
                    Toast.makeText(this, "Click en Cerrar Sesion", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
        toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "Abrir Drawer", Toast.LENGTH_SHORT).show()
        }
        buttonLogin.setOnClickListener {
            val usuario = Usuario(editTextUsuario.text.toString(), editTextPassword.text.toString())
            sharedPrefsController.guardarUsuario(this, usuario)
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_item_mis_direcciones -> {
                    Toast.makeText(this, "Click en Mis Direcciones", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

        // One Time Login!
        if(sharedPrefsController.obtenerUsuario(this) != null) {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }
    }
}