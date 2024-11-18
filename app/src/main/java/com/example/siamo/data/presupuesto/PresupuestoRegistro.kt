package com.example.siamo.data.presupuesto

data class PresupuestoRegistro(
    val idConsulta: Int,
    val nTecnicosAsignados: Int,
    val tarifaManoObra: Double,
    val tarifaRepuesto: Double,
    val descuentoNegociado: Double
)
