package com.example.siamo.ui.tecnico.presupuesto

import com.example.siamo.data.repuesto_seleccionado
import com.example.siamo.data.consulta_repuesto

data class PresupuestoUiState(
    val listaRepuestos: List<consulta_repuesto> = emptyList(),
    val listaRepuestosSeleccionados: List<repuesto_seleccionado> = emptyList(),
    val query: String = "",
    val cantidadRepuesto: String = "",
    val numeroTecnicos: String = "",
    val porcentajeDescuento: String = "",
    val presupuestoEstimado: Double = 0.0,
    val descuentoEnSoles: Double = 0.0,
    val presupuestoFinal: Double = 0.0,
    val mostrarResultadosBusqueda: Boolean = false,
    val resultadosBusqueda: List<consulta_repuesto> = emptyList(),
    val searchBarActiva: Boolean = false,
    val repuestoSeleccionadoTemp: consulta_repuesto? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
