package com.adrorodri.programacion3ejemplosmayo2021.activities

import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import androidx.core.app.NotificationCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adrorodri.programacion3ejemplosmayo2021.model.MenuOption
import com.adrorodri.programacion3ejemplosmayo2021.adapters.OptionRecyclerViewAdapter
import com.adrorodri.programacion3ejemplosmayo2021.R
import com.adrorodri.programacion3ejemplosmayo2021.persistency.SharedPreferencesController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainMenuActivity : AppCompatActivity() {
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var recyclerView: RecyclerView
    lateinit var buttonAgregarALaLista: Button
    lateinit var buttonPopupMenu: Button
    lateinit var buttonNotificacion: Button
    val sharedPrefsController = SharedPreferencesController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        recyclerView = findViewById(R.id.recyclerView)
        toolbar = findViewById(R.id.toolbar)
        buttonAgregarALaLista = findViewById(R.id.buttonAgregarALaLista)
        buttonPopupMenu = findViewById(R.id.buttonPopupMenu)
        buttonNotificacion = findViewById(R.id.buttonNotificacion)
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

        buttonPopupMenu.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.inflate(R.menu.popup_menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.menu_item_agregar -> {
                        Snackbar.make(this, drawerLayout, "Click en Agregar!", Snackbar.LENGTH_SHORT).show()
                    }
                    R.id.menu_item_remover -> {
                        Snackbar.make(this, drawerLayout, "Click en Remover!", Snackbar.LENGTH_SHORT).show()
                    }
                }
                true
            }
            popupMenu.show()
        }

        buttonNotificacion.setOnClickListener {
            val builder = NotificationCompat.Builder(this, "12345")
                .setSmallIcon(R.drawable.icon_gears)
                .setContentTitle("Ejemplo de notificacion!")
                .setContentText("Aqui ponemos cualquier contenido que querramos mostrar en la notificacion")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            val notification = builder.build()
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(123, notification)
        }

        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_ajustes -> {
                    Toast.makeText(this, "Click en Ajustes", Toast.LENGTH_SHORT).show()
                }
                R.id.menu_item_acerca_de -> {
                    val alertDialog = AlertDialog.Builder(this).apply {
                        setTitle("Acerca De")
                        setMessage("Aplicacion de prueba para Programacion 3 UPB")
                        setPositiveButton("OK") { _, _ ->
                            Toast.makeText(this@MainMenuActivity, "Click en OK!!", Toast.LENGTH_SHORT).show()
                        }
                    }.create()

                    alertDialog.show()
                }
                R.id.menu_item_cerrar_sesion -> {
                    Toast.makeText(this, "Click en Cerrar Sesion", Toast.LENGTH_SHORT).show()
                    sharedPrefsController.borrarUsuario(this)
                    finish()
                }
                R.id.menu_item_compartir_app -> {
                    val intent = Intent()
                    intent.action = Intent.ACTION_SEND
                    intent.putExtra(Intent.EXTRA_TEXT, "Hey usa nuestra nueva app!")
                    intent.type = "text/plain"
                    startActivity(intent)
                }
                R.id.menu_item_llamar_soporte -> {
                    val intent = Intent(Intent.ACTION_CALL)
                    intent.data = Uri.parse("tel:123456789")
                    startActivity(intent)
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