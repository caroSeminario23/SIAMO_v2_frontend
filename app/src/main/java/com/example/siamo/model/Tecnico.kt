package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class Tecnico(
    val id_tecnico: Int? = null,
    val id_empleado: Int? = null,
    val empleado: Empleado? = null,
    val ost_count: Int? = null,
)