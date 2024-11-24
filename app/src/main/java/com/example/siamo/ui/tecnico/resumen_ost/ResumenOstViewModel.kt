package com.example.siamo.ui.tecnico.resumen_ost

import androidx.lifecycle.ViewModel
import com.example.siamo.data.problema.ProblemaLectura
import com.example.siamo.data.problema.ProblemaSeleccionado
import com.example.siamo.data.solucion.SolucionLectura
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstUiState
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstViewModel
import kotlinx.coroutines.flow.StateFlow

class ResumenOstViewModel (
    private val registroOstViewModel: RegistroOstViewModel
) : ViewModel() {
    val uiState: StateFlow<RegistroOstUiState> = registroOstViewModel.uiState

    init {
        cargarProblemasSeleccionados()
        cargarSolucionesRegistradas()
    }

    private fun cargarProblemasSeleccionados() {
        val problema1 = ProblemaLectura(1, "Problema 1", "Detalle del problema 1")
        val problema2 = ProblemaLectura(2, "Problema 2", "Detalle del problema 2")
        val problema3 = ProblemaLectura(3, "Problema 3", "Detalle del problema 3")

        val problemas = listOf(
            ProblemaSeleccionado(problema1, true),
            ProblemaSeleccionado(problema2, true),
            ProblemaSeleccionado(problema3, true)
        )

        registroOstViewModel.actualizarUiState(
            uiState.value.copy(listaProblemasSeleccionados = problemas)
        )
    }

    private fun cargarSolucionesRegistradas() {
        val soluciones = listOf(
            SolucionLectura(1, "Solucion 1", 1),
            SolucionLectura(2, "Solucion 2", 2),
            SolucionLectura(3, "Solucion 3", 3)
        )

        registroOstViewModel.actualizarUiState(
            uiState.value.copy(listaSolucionesRegistradas = soluciones)
        )
    }

    fun registrarFechaActual() {
        val fechaActual = System.currentTimeMillis()
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                fechaAproxIngreso = fechaActual.toString(),
                registroCancelacionActivo = true
            )
        )
    }

    fun activarRegistroManual() {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                ingresoFechaActivo = true
            )
        )
    }

    fun actualizarFechaIngreso(fecha: String) {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                fechaAproxIngreso = fecha,
                registroCancelacionActivo = true
            )
        )
    }

    fun guardarOst() {
        // enviar datos a la base de datos
        val codRespuesta = 200
        if (codRespuesta == 200) {
            registroOstViewModel.actualizarUiState(
                uiState.value.copy(
                    errorRegistroPresupuesto = 2 // 2 es el código de éxito
                )
            )
        } else {
            registroOstViewModel.actualizarUiState(
                uiState.value.copy(
                    errorRegistroPresupuesto = 1 // 1 es el código de error
                )
            )
        }
    }

    fun cerrarAlertar() {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                errorRegistroPresupuesto = 0
            )
        )
    }
}