package com.example.siamo.ui.tecnico.presupuesto

import androidx.lifecycle.ViewModel
import com.example.siamo.data.repuesto_seleccionado
import com.example.siamo.data.consulta_repuesto
import com.example.siamo.ui.tecnico.resumenost.ResumenOstUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PresupuestoViewModel : ViewModel() {
    companion object {
        const val TARIFA_POR_TECNICO = 100.0
    }

    private val _uiState = MutableStateFlow(ResumenOstUiState())
    val uiState: StateFlow<ResumenOstUiState> = _uiState

    init {
        cargarRepuestos()
    }

    fun cargarRepuestos() {
        val repuestos = listOf(
            consulta_repuesto(1, "Repuesto 1", 100.00),
            consulta_repuesto(2, "Repuesto 2", 200.00),
            consulta_repuesto(3, "Repuesto 3", 300.00),
            consulta_repuesto(4, "Repuesto 4", 400.00),
            consulta_repuesto(5, "Repuesto 5", 500.00),
            consulta_repuesto(6, "Repuesto 6", 600.00),
            consulta_repuesto(7, "Repuesto 7", 700.00),
            consulta_repuesto(8, "Repuesto 8", 800.00),
            consulta_repuesto(9, "Repuesto 9", 900.00),
            consulta_repuesto(10 ,"Repuesto 10", 1000.00)
        )
        _uiState.update { it.copy(
            presupuestoUiState = it.presupuestoUiState.copy(
                listaRepuestos = repuestos)) }
    }

    fun buscarRepuesto(query: String) {
        _uiState.update {
            it.copy(
                presupuestoUiState = it.presupuestoUiState.copy(
                    resultadosBusqueda = it.presupuestoUiState.listaRepuestos.filter {
                        repuesto -> repuesto.descripcion.contains(query, ignoreCase = true)

                    },
                ),
                /*
                resultadosBusqueda = it.listaRepuestos.filter { repuesto ->
                    repuesto.descripcion.contains(query, ignoreCase = true)
                },

                 */
            )
        }
    }

    fun actualizarNumeroTecnicos(numero: String) {
        if (numero.isEmpty() || numero.toIntOrNull() != null) {
            _uiState.update { it.copy(
                presupuestoUiState = it.presupuestoUiState.copy(
                    numeroTecnicos = numero
                )
            ) }

            //_uiState.update { it.copy(numeroTecnicos = numero)

            calcularPresupuesto()
        }
    }

    fun actualizarCantidadRepuesto(cantidad: String) {
        if (cantidad.isEmpty() || cantidad.toIntOrNull() != null) {
            _uiState.update { it.copy(
                presupuestoUiState = it.presupuestoUiState.copy(
                    cantidadRepuesto = cantidad
                )
            )
            //_uiState.update { it.copy(cantidadRepuesto = cantidad)
            }
        }
    }

    fun actualizarPorcentajeDescuento(porcentaje: String) {
        if (porcentaje.isEmpty() || porcentaje.toDoubleOrNull() != null) {
            _uiState.update { it.copy(
                presupuestoUiState = it.presupuestoUiState.copy(
                    porcentajeDescuento = porcentaje
                )
            )
            //_uiState.update { it.copy(porcentajeDescuento = porcentaje)
            }
            calcularPresupuesto()
        }
    }

    fun actualizarQuery(newQuery: String) {
        _uiState.update { it.copy(
            presupuestoUiState = it.presupuestoUiState.copy(
                query = newQuery,
                repuestoSeleccionadoTemp = null,
                mostrarResultadosBusqueda = true
            )
        )}
        /*
        _uiState.update {
            it.copy(
                query = newQuery,
                repuestoSeleccionadoTemp = null,
                mostrarResultadosBusqueda = true
            )
        }

         */
        buscarRepuesto(newQuery)
    }

    fun registrarRepuesto() {
        //val state = _uiState.value
        val state = _uiState.value.presupuestoUiState
        val cantidad = state.cantidadRepuesto.toIntOrNull() ?: 0
        val repuestoTemp = state.repuestoSeleccionadoTemp

        if (cantidad > 0 && repuestoTemp != null) {
            val repuestoSeleccionado = repuesto_seleccionado(
                repuesto = repuestoTemp,
                cantidad = cantidad
            )
            /*_uiState.update {
                it.copy(
                    listaRepuestosSeleccionados = it.listaRepuestosSeleccionados + repuestoSeleccionado,
                    cantidadRepuesto = "",
                    query = "",
                    repuestoSeleccionadoTemp = null
                )
            }*/
            _uiState.update { currentState ->
                currentState.copy(
                    presupuestoUiState = currentState.presupuestoUiState.copy(
                        listaRepuestosSeleccionados = currentState.presupuestoUiState.listaRepuestosSeleccionados + repuestoSeleccionado,
                        cantidadRepuesto = "",
                        query = "",
                        repuestoSeleccionadoTemp = null
                    )
                )
            }
            calcularPresupuesto()
        }
    }

    fun seleccionarRepuesto(repuesto: consulta_repuesto) {
        /*val cantidad = _uiState.value.cantidadRepuesto.toIntOrNull() ?: 0
        _uiState.update {
            it.copy(
                repuestoSeleccionadoTemp = repuesto,
                cantidadRepuesto = cantidad.toString(),
                mostrarResultadosBusqueda = false,
                query = repuesto.descripcion
            )
        }*/
        val cantidad = _uiState.value.presupuestoUiState.cantidadRepuesto.toIntOrNull() ?: 0
        _uiState.update { currentState ->
            currentState.copy(
                presupuestoUiState = currentState.presupuestoUiState.copy(
                    repuestoSeleccionadoTemp = repuesto,
                    cantidadRepuesto = cantidad.toString(),
                    mostrarResultadosBusqueda = false,
                    query = repuesto.descripcion
                )
            )
        }
    }

    fun toggleMarcadoRepuesto(index: Int) {
        /*_uiState.update { state ->
            val nuevaLista = state.listaRepuestosSeleccionados.toMutableList()
            val repuesto = nuevaLista[index]
            nuevaLista[index] = repuesto.copy(marcado = !repuesto.marcado)
            state.copy(listaRepuestosSeleccionados = nuevaLista)
        }*/
        _uiState.update { currentState ->
            val nuevaLista = currentState.presupuestoUiState.listaRepuestosSeleccionados.toMutableList()
            val repuesto = nuevaLista[index]
            nuevaLista[index] = repuesto.copy(marcado = !repuesto.marcado)
            currentState.copy(
                presupuestoUiState = currentState.presupuestoUiState.copy(
                    listaRepuestosSeleccionados = nuevaLista
                )
            )
        }
        calcularPresupuesto()
    }

    private fun calcularPresupuesto() {
        //val state = _uiState.value
        val state = _uiState.value.presupuestoUiState

        // Costo de mano de obra
        val costoManoObra = (state.numeroTecnicos.toIntOrNull() ?: 0) * TARIFA_POR_TECNICO

        // Costo de repuestos marcados
        val costoRepuestos = state.listaRepuestosSeleccionados
            .filter { it.marcado }
            .sumOf { it.subtotal }

        // Presupuesto estimado
        val presupuestoEstimado = costoManoObra + costoRepuestos

        // Descuento
        val porcentajeDescuento = state.porcentajeDescuento.toDoubleOrNull() ?: 0.0
        val descuentoEnSoles = (presupuestoEstimado * porcentajeDescuento) / 100

        // Presupuesto final
        val presupuestoFinal = presupuestoEstimado - descuentoEnSoles

        /*_uiState.update {
            it.copy(
                presupuestoEstimado = presupuestoEstimado,
                descuentoEnSoles = descuentoEnSoles,
                presupuestoFinal = presupuestoFinal
            )
        }*/
        _uiState.update { currentState ->
            currentState.copy(
                presupuestoUiState = currentState.presupuestoUiState.copy(
                    presupuestoEstimado = presupuestoEstimado,
                    descuentoEnSoles = descuentoEnSoles,
                    presupuestoFinal = presupuestoFinal
                )
            )
        }
    }

    fun onSearch() {
        _uiState.update {
            it.copy(
                presupuestoUiState = it.presupuestoUiState.copy(
                    mostrarResultadosBusqueda = false
                )
            )
        }
        //_uiState.update { it.copy(mostrarResultadosBusqueda = false) }
    }
}