package com.example.siamo.data.presupuesto

import kotlinx.serialization.Serializable

@Serializable
data class PresupuestoRegistro(
    val id_consulta: Int,
    val tarifa_mano_obra: Double,
    val tarifa_repuestos: Double,
    val descuento_negociado: Double
)
