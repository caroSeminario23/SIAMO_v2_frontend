package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class Problema(
    var id: Int? = null,
    var descripcion: String? = null,
    val detalle: String? = null,
    val id_Solucion:Int? = null,
    val solucion: Solucion? = null,

)