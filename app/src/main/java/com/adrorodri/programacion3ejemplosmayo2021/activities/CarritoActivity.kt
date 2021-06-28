package com.adrorodri.programacion3ejemplosmayo2021.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adrorodri.programacion3ejemplosmayo2021.persistency.DatabaseController
import com.adrorodri.programacion3ejemplosmayo2021.R
import com.adrorodri.programacion3ejemplosmayo2021.adapters.CarritoRecyclerViewAdapter
import com.adrorodri.programacion3ejemplosmayo2021.model.ProductoCarrito
import com.adrorodri.programacion3ejemplosmayo2021.mostrarAlerta

class CarritoActivity : AppCompatActivity() {

    lateinit var recyclerViewCarrito: RecyclerView
    lateinit var editText: EditText
    val databaseController = DatabaseController(this)
    var listaCarrito: MutableList<ProductoCarrito> = mutableListOf()
    var listaCarritoStrings: MutableList<String> = mutableListOf()
    var adapter: CarritoRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        recyclerViewCarrito = findViewById(R.id.recyclerViewCarrito)
        editText = findViewById(R.id.editTextBuscador)

        listaCarrito = databaseController.obtenerCarrito().toMutableList()
//        listaCarritoStrings = listaCarrito.map { "${it.nombre} - ${it.precioTotal} - ${it.cantidad}" }.toMutableList()

        adapter = CarritoRecyclerViewAdapter(this, listaCarrito)

        recyclerViewCarrito.adapter = adapter
        recyclerViewCarrito.layoutManager = LinearLayoutManager(this)

        adapter?.setOnProductClickListener { productoABorrar ->
            val alertDialog = AlertDialog.Builder(this).apply {
                setTitle("BORRAR DEL CARRITO")
                setMessage("Estas 100% seguro que quieres borrar ${productoABorrar.nombre} de tu carrito?")
                setPositiveButton("SI") { _, _ ->
                    databaseController.borrarDelCarrito(productoABorrar.nombre)
                    editText.setText("")
                    Toast.makeText(this@CarritoActivity, "Producto borrado de la lista", Toast.LENGTH_SHORT).show()
                    updateList()
                }
                setCancelable(false)
                setNegativeButton("NO") { _, _ -> }
            }.create()
            alertDialog.show()
        }
//
//        listView.setOnItemClickListener { parent, view, position, id ->
//            val idProductoABorrar = listaCarritoStrings[position].split(" - ")[0].toInt()
//            databaseController.actualizarPrecio(idProductoABorrar, 1000.0)
//            Toast.makeText(this, "Producto actualizado", Toast.LENGTH_SHORT).show()
//            updateList()
//        }

        editText.doAfterTextChanged {
            listaCarrito = databaseController.obtenerCarritoPorNombre(it.toString()).toMutableList()
            updateList()
        }
//        textViewPrueba.text = AlmacenamientoTemporal.carritoDeCompras.toString()
//        textViewPrueba.text = .toString()
    }

    fun updateList() {
        listaCarrito.clear()
        listaCarrito.addAll(databaseController.obtenerCarrito())
//        listaCarritoStrings.clear()
//        listaCarritoStrings.addAll(listaCarrito.map { "${it.nombre} - ${it.precioTotal} - ${it.cantidad}" })
        adapter?.notifyDataSetChanged()
    }
}