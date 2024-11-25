package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class Solucion(
    val descripcion: String? = null,
    val id_solucion: Int? = null
)