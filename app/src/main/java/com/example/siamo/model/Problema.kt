package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class Problema(
    var id: Int,
    var descripcion: String,
    val detalle: String,
    val id_Solucion:Int,
    val solucion: Solucion? = null,

)