package com.adrorodri.programacion3ejemplosmayo2021.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adrorodri.programacion3ejemplosmayo2021.model.MenuOption
import com.adrorodri.programacion3ejemplosmayo2021.adapters.OptionRecyclerViewAdapter
import com.adrorodri.programacion3ejemplosmayo2021.R
import com.adrorodri.programacion3ejemplosmayo2021.persistency.SharedPreferencesController
import com.google.android.material.navigation.NavigationView

class MainMenuActivity : AppCompatActivity() {
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var recyclerView: RecyclerView
    lateinit var buttonAgregarALaLista: Button
    val sharedPrefsController = SharedPreferencesController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        recyclerView = findViewById(R.id.recyclerView)
        toolbar = findViewById(R.id.toolbar)
        buttonAgregarALaLista = findViewById(R.id.buttonAgregarALaLista)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigationView)

        val menuOptionsList = mutableListOf(
            MenuOption("Adopciones", R.drawable.icon_cat, "adopta ya!!"),
            MenuOption("Perdidos", R.drawable.icon_gears, "encuentralo ya!!"),
            MenuOption("Tienda", R.drawable.icon_cat, "compra ya!!")
        )

        val adapter = OptionRecyclerViewAdapter(this, menuOptionsList)
        val layoutManager = LinearLayoutManager(this)
//        val layoutManager = GridLayoutManager(this, 2)
//        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        adapter.setOnMenuOptionClickListener { menuOption ->
            Toast.makeText(this, "Click en ${menuOption.titulo}", Toast.LENGTH_SHORT).show()
            if(menuOption.titulo == "Tienda") {
                val intent = Intent(this, TiendaActivity::class.java)
                startActivity(intent)
            }
        }

        buttonAgregarALaLista.setOnClickListener {
            menuOptionsList.add(MenuOption("Mi cuenta", R.drawable.icon_email, "Configura tu cuenta"))
            adapter.notifyDataSetChanged()
        }

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
                    sharedPrefsController.borrarUsuario(this)
                    finish()
                }
            }
            true
        }
        toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "Abrir Drawer", Toast.LENGTH_SHORT).show()
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
    }
}