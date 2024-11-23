
package com.example.siamo.model


import kotlinx.serialization.Serializable

@Serializable
data class ProblemaActualizar(
    val detalle: String,
    val id_Solucion: Int
)
