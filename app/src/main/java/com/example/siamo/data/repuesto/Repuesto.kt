package com.example.siamo.data.repuesto

import kotlinx.serialization.Serializable

@Serializable
data class Repuesto(
    val id_repuesto: Int? = null,
    val cantidad: Int? = null,
    val precio: Double? = null,
    val descripcion: String? = null,
)
