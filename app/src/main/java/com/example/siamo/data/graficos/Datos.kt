package com.example.siamo.data.graficos

import kotlinx.serialization.Serializable

@Serializable
data class Datos(
    var etiqueta: String? = null,
    var valor: Float? = null
)
