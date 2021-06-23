package com.adrorodri.programacion3ejemplosmayo2021.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.adrorodri.programacion3ejemplosmayo2021.R

class LlenarDatosActivity : AppCompatActivity() {

    lateinit var spinner: Spinner
    lateinit var listView: ListView
    lateinit var recyclerView: RecyclerView
    val listOfDepartments = listOf(
        "La Paz",
        "Cochabamba",
        "Santa Cruz",
        "Oruro",
        "Potosi",
        "Pando",
        "Beni",
        "Chuquisaca",
        "tarija",
        "La Paz",
        "Cochabamba",
        "Santa Cruz",
        "Oruro",
        "Potosi",
        "Pando",
        "Beni",
        "Chuquisaca",
        "tarija",
        "La Paz",
        "Cochabamba",
        "Santa Cruz",
        "Oruro",
        "Potosi",
        "Pando",
        "Beni",
        "Chuquisaca",
        "tarija",
        "La Paz",
        "Cochabamba",
        "Santa Cruz",
        "Oruro",
        "Potosi",
        "Pando",
        "Beni",
        "Chuquisaca",
        "tarija",
        "La Paz",
        "Cochabamba",
        "Santa Cruz",
        "Oruro",
        "Potosi",
        "Pando",
        "Beni",
        "Chuquisaca",
        "tarija",
        "La Paz",
        "Cochabamba",
        "Santa Cruz",
        "Oruro",
        "Potosi",
        "Pando",
        "Beni",
        "Chuquisaca",
        "tarija",
        "La Paz",
        "Cochabamba",
        "Santa Cruz",
        "Oruro",
        "Potosi",
        "Pando",
        "Beni",
        "Chuquisaca",
        "tarija"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llenar_datos)

        spinner = findViewById(R.id.spinner)
        listView = findViewById(R.id.listView)
        recyclerView = findViewById(R.id.recyclerView)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listOfDepartments)
        spinner.adapter = adapter

        listView.adapter = adapter

//        recyclerView.adapter =
    }
}