package com.adrorodri.programacion3ejemplosmayo2021.persistency

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.adrorodri.programacion3ejemplosmayo2021.model.Producto
import com.adrorodri.programacion3ejemplosmayo2021.model.ProductoCarrito

class DatabaseController(val context: Context): SQLiteOpenHelper(context, "Progra3Database", null, 2) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE CarritoDeCompras (_id INTEGER PRIMARY KEY, nombre TEXT, precio DOUBLE, imagen TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun agregarProductoAlCarrito(producto: Producto) {
        val contentVales = ContentValues()
        contentVales.put("nombre", producto.nombre)
        contentVales.put("precio", producto.precio)
        contentVales.put("imagen", producto.imagen)
        writableDatabase.insert("CarritoDeCompras", null, contentVales)
    }

    fun obtenerCarrito(): List<ProductoCarrito> {
        val cursor = readableDatabase.rawQuery("SELECT nombre, imagen, COUNT(0), SUM(precio) FROM CarritoDeCompras GROUP BY nombre", arrayOf())
        val listaCarrito = mutableListOf<ProductoCarrito>()
        while(cursor.moveToNext()) {
            val nombre = cursor.getString(0)
            val imagen = cursor.getString(1)
            val cantidad = cursor.getInt(2)
            val precioTotal = cursor.getDouble(3)
            listaCarrito.add(ProductoCarrito(nombre, imagen, precioTotal, cantidad))
        }
        cursor.close()
        return listaCarrito
    }

    fun obtenerCarritoPorNombre(nombre: String): List<ProductoCarrito> {
        val cursor = readableDatabase.rawQuery("SELECT nombre, imagen, COUNT(0), SUM(precio) FROM CarritoDeCompras WHERE nombre LIKE \"%${nombre}%\" GROUP BY nombre", arrayOf())
        val listaCarrito = mutableListOf<ProductoCarrito>()
        while(cursor.moveToNext()) {
            val nombre = cursor.getString(0)
            val imagen = cursor.getString(1)
            val cantidad = cursor.getInt(2)
            val precioTotal = cursor.getDouble(3)
            listaCarrito.add(ProductoCarrito(nombre, imagen, precioTotal, cantidad))
        }
        cursor.close()
        return listaCarrito
    }

    fun borrarDelCarrito(nombre: String) {
        // DELETE FROM CarritoDeCompras WHERE nombre = "nombreProductoABorrar"
        writableDatabase.delete("CarritoDeCompras", "nombre = \"${nombre}\"", arrayOf())
    }

    fun actualizarPrecio(id: Int, nuevoPrecio: Double) {
        val contentVales = ContentValues()
        contentVales.put("precio", nuevoPrecio)
        writableDatabase.update("CarritoDeCompras", contentVales, "_id = \"${id}\"", arrayOf())
    }
}