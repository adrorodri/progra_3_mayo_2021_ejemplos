package com.adrorodri.programacion3ejemplosmayo2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.AppCompatButton
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class LoginActivity : AppCompatActivity() {
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var buttonLogin: AppCompatButton
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Toast.makeText(this, getString(R.string.iniciar_sesion), Toast.LENGTH_SHORT).show()

        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        buttonLogin = findViewById(R.id.buttonLogin)
        navigationView = findViewById(R.id.navigationView)

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
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_item_mis_direcciones -> {
                    Toast.makeText(this, "Click en Mis Direcciones", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }
}