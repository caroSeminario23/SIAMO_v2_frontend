package com.example.siamo.data.problema

import kotlinx.serialization.Serializable

@Serializable
data class ProblemaRegistro (
    val descripcion: String,
    val detalle: String,
    val id_solucion: Int
)