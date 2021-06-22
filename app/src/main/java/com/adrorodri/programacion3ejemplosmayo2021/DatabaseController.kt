package com.adrorodri.programacion3ejemplosmayo2021

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

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
            val nombre = cursor.getString(1)
            val precio = cursor.getDouble(2)
            listaCarrito.add(ProductoCarrito(nombre, precio))
        }
        return listaCarrito
    }
}