package com.example.siamo.ui.tecnico.presupuesto

import androidx.lifecycle.ViewModel
import com.example.siamo.data.repuesto.RepuestoSeleccionado
import com.example.siamo.data.repuesto.Repuesto
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstUiState
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PresupuestoViewModel (
    private val registroOstViewModel: RegistroOstViewModel
) : ViewModel() {
    companion object {
        const val TARIFA_POR_TECNICO = 100.0
    }

    val uiState: StateFlow<RegistroOstUiState> = registroOstViewModel.uiState

    init {
        cargarRepuestos()
    }

    private fun cargarRepuestos() {
        val repuestos = listOf(
            Repuesto(1, "Repuesto 1", 100.00),
            Repuesto(2, "Repuesto 2", 200.00),
            Repuesto(3, "Repuesto 3", 300.00),
            Repuesto(4, "Repuesto 4", 400.00),
            Repuesto(5, "Repuesto 5", 500.00),
            Repuesto(6, "Repuesto 6", 600.00),
            Repuesto(7, "Repuesto 7", 700.00),
            Repuesto(8, "Repuesto 8", 800.00),
            Repuesto(9, "Repuesto 9", 900.00),
            Repuesto(10 ,"Repuesto 10", 1000.00)
        )
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(listaRepuestos = repuestos)
        )
    }

    private fun buscarRepuesto(query: String) {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                resultadosBusquedaRepuesto = uiState.value.listaRepuestos.filter {
                        repuesto -> repuesto.descripcion.contains(query, ignoreCase = true)
                },
            )
        )
    }

    fun actualizarNumeroTecnicos(numero: String) {
        if (numero.isEmpty() || numero.toIntOrNull() != null) {
            registroOstViewModel.actualizarUiState(uiState.value.copy(numeroTecnicos = numero))
            calcularPresupuesto()
        }
    }

    fun actualizarCantidadRepuesto(cantidad: String) {
        if (cantidad.isEmpty() || cantidad.toIntOrNull() != null) {
            registroOstViewModel.actualizarUiState(uiState.value.copy(cantidadRepuesto = cantidad))
        }
    }

    fun actualizarPorcentajeDescuento(porcentaje: String) {
        if (porcentaje.isEmpty() || porcentaje.toDoubleOrNull() != null) {
            registroOstViewModel.actualizarUiState(uiState.value.copy(porcentajeDescuento = porcentaje))
            calcularPresupuesto()
        }
    }

    fun actualizarQuery(newQuery: String) {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                repuestoBuscado = newQuery,
                repuestoSeleccionadoTemp = null,
                mostrarResultadosBusquedaRepuestos = true
            )
        )
        buscarRepuesto(newQuery)
    }

    fun registrarRepuesto() {
        val state = uiState.value
        val cantidad = state.cantidadRepuesto.toIntOrNull() ?: 0
        val repuestoTemp = state.repuestoSeleccionadoTemp

        if (cantidad > 0 && repuestoTemp != null) {
            val repuestoSeleccionado = RepuestoSeleccionado(
                repuesto = repuestoTemp,
                cantidad = cantidad
            )
            registroOstViewModel.actualizarUiState(
                state.copy(
                    listaRepuestosSeleccionados = state.listaRepuestosSeleccionados + repuestoSeleccionado,
                    cantidadRepuesto = "",
                    repuestoBuscado = "",
                    repuestoSeleccionadoTemp = null
                )
            )
            calcularPresupuesto()
        }
    }

    fun seleccionarRepuesto(repuesto: Repuesto) {
        val cantidad = uiState.value.cantidadRepuesto.toIntOrNull() ?: 0
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                repuestoSeleccionadoTemp = repuesto,
                cantidadRepuesto = cantidad.toString(),
                mostrarResultadosBusquedaRepuestos = false,
                repuestoBuscado = repuesto.descripcion
            )
        )
    }

    fun toggleMarcadoRepuesto(index: Int) {
        val state = uiState.value
        val nuevaLista = state.listaRepuestosSeleccionados.toMutableList()
        val repuesto = nuevaLista[index]
        nuevaLista[index] = repuesto.copy(marcado = !repuesto.marcado)
        registroOstViewModel.actualizarUiState(state.copy(listaRepuestosSeleccionados = nuevaLista))
        calcularPresupuesto()
    }

    private fun calcularPresupuesto() {
        val state = uiState.value
        val costoManoObra = (state.numeroTecnicos.toIntOrNull() ?: 0) * TARIFA_POR_TECNICO
        val costoRepuestos = state.listaRepuestosSeleccionados
            .filter { it.marcado }
            .sumOf { it.subtotal }
        val presupuestoEstimado = costoManoObra + costoRepuestos
        val porcentajeDescuento = state.porcentajeDescuento.toDoubleOrNull() ?: 0.0
        val descuentoEnSoles = (presupuestoEstimado * porcentajeDescuento) / 100
        val presupuestoFinal = presupuestoEstimado - descuentoEnSoles

        registroOstViewModel.actualizarUiState(
            state.copy(
                presupuestoEstimado = presupuestoEstimado,
                descuentoEnSoles = descuentoEnSoles,
                presupuestoFinal = presupuestoFinal
            )
        )
    }

    fun onSearch() {
        registroOstViewModel.actualizarUiState(uiState.value.copy(mostrarResultadosBusquedaRepuestos = false))
    }
}