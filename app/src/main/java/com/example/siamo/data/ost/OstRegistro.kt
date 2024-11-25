package com.example.siamo.data.ost

import kotlinx.serialization.Serializable

@Serializable
data class OstRegistro(
//    val fechaRegistro: String,
//    val estado: Int,
    val id_consulta: Int,
    val fecha_aprox_ingreso: String
)
