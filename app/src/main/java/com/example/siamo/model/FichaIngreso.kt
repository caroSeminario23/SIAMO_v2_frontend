package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class FichaIngreso(
    val id_ficha_ingreso: Int? = null,
    val fecha_ingreso: String? = null,
    val fecha_aprox_recojo: String? = null,
    val estado_vehiculo: EstadoVehiculo? = null
)
@Serializable
data class FichaIngresoRequest(
    val fecha_aprox_recojo: String,
    val id_ost: Int
)
@Serializable
data class FichaIngresoCompleta(
    val id_ficha_ingreso: Int,
    val id_ost: Int,
    val nombre_cliente: String,
    val placa_vehiculo: String,
    val estado_vehiculo: EstadoVehiculo
)

