package com.example.siamo.model

import com.example.siamo.data.repuesto.Repuesto
import kotlinx.serialization.Serializable

@Serializable
data class PresupuestoRepuesto(
    val id_presupuesto: Int? = null,
    val repuestos: List<Repuesto> = emptyList()
)