package com.example.siamo.sin_usar

import com.example.siamo.data.repuesto.RepuestoSeleccionado
import com.example.siamo.data.repuesto.Repuesto

data class PresupuestoUiState(
    val listaRepuestos: List<Repuesto> = emptyList(),
    val listaRepuestosSeleccionados: List<RepuestoSeleccionado> = emptyList(),
    val query: String = "",
    val cantidadRepuesto: String = "",
    val numeroTecnicos: String = "",
    val porcentajeDescuento: String = "",
    val presupuestoEstimado: Double = 0.0,
    val descuentoEnSoles: Double = 0.0,
    val presupuestoFinal: Double = 0.0,
    val mostrarResultadosBusqueda: Boolean = false,
    val resultadosBusqueda: List<Repuesto> = emptyList(),
    val searchBarActiva: Boolean = false,
    val repuestoSeleccionadoTemp: Repuesto? = null
)
