package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class Persona(
    val apellidos: String,
    val correo: String? = null,
    val direccion: String? = null,
    val id_persona: Int? = null,
    val nombres: String,
    val num_doc: String? = null,
    val sexo: String? = null,
    val telefono: String? = null,
    val tipo_doc: Int? = null
)
