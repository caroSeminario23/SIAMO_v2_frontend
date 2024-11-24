package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class Cliente(
    val id_persona: Int,
    val id_cliente: Int? = null,
    val persona: Persona? = null
)

@Serializable
data class ClienteRequest(
    val id_persona: Int
)