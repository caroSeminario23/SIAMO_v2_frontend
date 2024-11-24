package com.example.siamo.model

import kotlinx.serialization.Serializable

@Serializable
data class InformeTecnico(
    val id_informe_tecnico: Int? = null,
    val fecha_inicio_reparacion: String? = null,
    val fecha_fin_reparacion: String? = null,
    val detalle_reparacion: String? = null,
    val observaciones: String? = null,
    val saldo_final: Double? = null
)
