package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class Consulta(
    val id_consulta: Int? = null,
    val id_cliente: Int? = null,
    val id_automovil: Int? = null,
    val id_tecnico: Int? = null,
    val estado: Int,
    val prob_declarado: String,
    val persona: Persona? = null,
    val automovil: Automovil? = null,
)

@Serializable
data class ConsultaRequest(
    val prob_declarado: String,
    val estado: Int,
    val id_cliente: Int,
    val id_tecnico: Int,
    val id_automovil: Int
)
