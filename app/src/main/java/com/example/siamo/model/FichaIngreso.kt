package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class FichaIngreso(
    val id_ficha_ingreso: Int? = null,
    val fecha_ingreso: String? = null,
    val fecha_aprox_recojo: String? = null,
    val estado_vehiculo: EstadoVehiculo? = null
)
