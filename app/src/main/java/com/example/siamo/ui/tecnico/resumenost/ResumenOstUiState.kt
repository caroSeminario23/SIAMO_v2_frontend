package com.example.siamo.ui.tecnico.resumenost

import com.example.siamo.ui.tecnico.presupuesto.PresupuestoUiState

data class ResumenOstUiState(
    val fechaAproxIngreso: String = "",
    //provisional
    val problemas: List<String> = listOf("Problema 1", "Problema 2", "Problema 3"),
    val soluciones: List<String> = listOf("Solucion 1", "Solucion 2", "Solucion 3"),

    val presupuestoUiState: PresupuestoUiState = PresupuestoUiState(),
    val ingresoFechaActivo: Boolean = false,
    val registroCancelacionActivo: Boolean = false,
    val errorRegistro: Int = 0 // 0 es el código de no haber intentado registrar
)
