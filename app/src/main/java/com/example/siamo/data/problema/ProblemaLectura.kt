package com.example.siamo.data.problema

import kotlinx.serialization.Serializable

@Serializable
data class ProblemaLectura(
    val descripcion: String? = null,
    val detalle: String? = null,
    val id_problema: Int? = null
)
