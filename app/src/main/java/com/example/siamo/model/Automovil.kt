package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class Automovil(
    var id_automovil: Int? = null,
    val placa: String,
    val marca: String,
    val modelo: String,
    val id_cliente: Int? = null
)
