package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class EstadoVehiculo(
    val id_estado_vehiculo: Int? = null,
    val estado_carroceria: String? = null,
    val estado_neumaticos: String? = null,
    val estado_motor: String? = null,
    val estado_frenos: String? = null
)