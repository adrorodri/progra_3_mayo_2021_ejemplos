package com.adrorodri.programacion3ejemplosmayo2021.model

import com.google.gson.annotations.Expose

data class Usuario(@Expose val username: String, @Expose var password: String)