package com.adrorodri.programacion3ejemplosmayo2021.model

import com.google.gson.annotations.Expose

data class Pedido(@Expose val nombre: String, @Expose val total: Double)