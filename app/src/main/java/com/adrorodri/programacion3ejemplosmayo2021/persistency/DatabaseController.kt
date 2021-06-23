package com.adrorodri.programacion3ejemplosmayo2021.persistency

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.adrorodri.programacion3ejemplosmayo2021.model.Producto
import com.adrorodri.programacion3ejemplosmayo2021.model.ProductoCarrito

class DatabaseController(val context: Context): SQLiteOpenHelper(context, "Progra3Database", null, 2) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE CarritoDeCompras (_id INTEGER PRIMARY KEY, nombre TEXT, precio DOUBLE)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun agregarProductoAlCarrito(producto: Producto) {
        val contentVales = ContentValues()
        contentVales.put("nombre", producto.nombre)
        contentVales.put("precio", producto.precio)
        writableDatabase.insert("CarritoDeCompras", null, contentVales)
    }

    fun obtenerCarrito(): List<ProductoCarrito> {
        val cursor = readableDatabase.rawQuery("SELECT * FROM CarritoDeCompras", arrayOf())
        val listaCarrito = mutableListOf<ProductoCarrito>()
        while(cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val nombre = cursor.getString(1)
            val precio = cursor.getDouble(2)
            listaCarrito.add(ProductoCarrito(id, nombre, precio))
        }
        cursor.close()
        return listaCarrito
    }

    fun obtenerCarritoPorNombre(nombre: String): List<ProductoCarrito> {
        val cursor = readableDatabase.rawQuery("SELECT * FROM CarritoDeCompras WHERE nombre LIKE \"%${nombre}%\"", arrayOf())
        val listaCarrito = mutableListOf<ProductoCarrito>()
        while(cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val nombre = cursor.getString(1)
            val precio = cursor.getDouble(2)
            listaCarrito.add(ProductoCarrito(id, nombre, precio))
        }
        cursor.close()
        return listaCarrito
    }

    fun borrarDelCarrito(id: Int) {
        // DELETE FROM CarritoDeCompras WHERE nombre = "nombreProductoABorrar"
        writableDatabase.delete("CarritoDeCompras", "_id = \"${id}\"", arrayOf())
    }

    fun actualizarPrecio(id: Int, nuevoPrecio: Double) {
        val contentVales = ContentValues()
        contentVales.put("precio", nuevoPrecio)
        writableDatabase.update("CarritoDeCompras", contentVales, "_id = \"${id}\"", arrayOf())
    }
}