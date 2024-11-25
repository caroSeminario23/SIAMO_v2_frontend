package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class Empleado(
    val id_empleado: Int? = null,
    val id_persona: Int? = null,
    val id_tecnico: Int? = null,
    val cod_empleado: Int? = null,
    val persona: Persona? = null,
    val contrasenia: String? = null,
    val tipo_empleado: Integer? = null,
)