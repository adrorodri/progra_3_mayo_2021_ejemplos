package com.adrorodri.programacion3ejemplosmayo2021.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adrorodri.programacion3ejemplosmayo2021.persistency.DatabaseController
import com.adrorodri.programacion3ejemplosmayo2021.R
import com.adrorodri.programacion3ejemplosmayo2021.adapters.CarritoRecyclerViewAdapter
import com.adrorodri.programacion3ejemplosmayo2021.model.ProductoCarrito

class CarritoActivity : AppCompatActivity() {

    lateinit var recyclerViewCarrito: RecyclerView
    lateinit var editText: EditText
    val databaseController = DatabaseController(this)
    var listaCarrito: List<ProductoCarrito> = listOf()
    var listaCarritoStrings: MutableList<String> = mutableListOf()
    var adapter: CarritoRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        recyclerViewCarrito = findViewById(R.id.recyclerViewCarrito)
        editText = findViewById(R.id.editTextBuscador)

        listaCarrito = databaseController.obtenerCarrito()
//        listaCarritoStrings = listaCarrito.map { "${it.nombre} - ${it.precioTotal} - ${it.cantidad}" }.toMutableList()

        adapter = CarritoRecyclerViewAdapter(this, listaCarrito)

        recyclerViewCarrito.adapter = adapter
        recyclerViewCarrito.layoutManager = LinearLayoutManager(this)

//        recyclerViewCarrito.setOnItemLongClickListener { parent, view, position, id ->
//            val idProductoABorrar = listaCarritoStrings[position].split(" - ")[0].toInt()
//            databaseController.borrarDelCarrito(idProductoABorrar)
//            editText.setText("")
//            Toast.makeText(this, "Producto borrado de la lista", Toast.LENGTH_SHORT).show()
//            updateList()
//            true
//        }
//
//        listView.setOnItemClickListener { parent, view, position, id ->
//            val idProductoABorrar = listaCarritoStrings[position].split(" - ")[0].toInt()
//            databaseController.actualizarPrecio(idProductoABorrar, 1000.0)
//            Toast.makeText(this, "Producto actualizado", Toast.LENGTH_SHORT).show()
//            updateList()
//        }

        editText.doAfterTextChanged {
            listaCarrito = databaseController.obtenerCarritoPorNombre(it.toString())
            updateList()
        }
//        textViewPrueba.text = AlmacenamientoTemporal.carritoDeCompras.toString()
//        textViewPrueba.text = .toString()
    }

    fun updateList() {
        listaCarrito = databaseController.obtenerCarrito()
        listaCarritoStrings.clear()
        listaCarritoStrings.addAll(listaCarrito.map { "${it.nombre} - ${it.precioTotal} - ${it.cantidad}" })
        adapter?.notifyDataSetChanged()
    }
}