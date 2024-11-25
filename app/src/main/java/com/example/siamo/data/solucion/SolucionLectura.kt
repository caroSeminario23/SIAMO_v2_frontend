package com.example.siamo.data.solucion

import com.example.siamo.model.Solucion
import kotlinx.serialization.Serializable

@Serializable
data class SolucionLectura(
    val id_problema: Int,
    val solucion: Solucion
)
