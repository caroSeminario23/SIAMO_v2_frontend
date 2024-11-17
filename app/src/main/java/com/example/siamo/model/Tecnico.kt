package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class Tecnico(
    val id_tecnico: Int? = null,
    val empleado: Empleado? = null,
)