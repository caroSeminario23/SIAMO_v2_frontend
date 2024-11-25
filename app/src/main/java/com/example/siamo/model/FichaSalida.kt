package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class FichaSalida(
    val id_ficha_salida: Int? = null,
    val fecha_recojo: String? = null
)