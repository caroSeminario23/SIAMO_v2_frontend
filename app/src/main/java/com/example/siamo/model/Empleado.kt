package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class Empleado(
    val id_empleado: Int? = null,
    val id_persona: Int? = null,
    val persona: Persona? = null,
)