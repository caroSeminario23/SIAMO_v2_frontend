package com.example.siamo.data.presupuesto

import kotlinx.serialization.Serializable

@Serializable
data class PresupuestoRepuesto(
    val idPresupuesto: Int,
    val idRepuesto: Int,
    val cantidad: Int
)
