package com.example.siamo.ui.tecnico.inspeccion_inicial_soluciones

import androidx.lifecycle.ViewModel
import com.example.siamo.data.problema.ProblemaLectura
import com.example.siamo.data.problema.ProblemaSeleccionado
import com.example.siamo.data.solucion.SolucionLectura
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstUiState
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstViewModel
import kotlinx.coroutines.flow.StateFlow

class InspeccionSolucionesViewModel(
    private val registroOstViewModel: RegistroOstViewModel
) : ViewModel() {
    val uiState: StateFlow<RegistroOstUiState> = registroOstViewModel.uiState

    init {
        //cargarProblemas()
        cargarSoluciones()
    }

    private fun cargarProblemas() {
        val problema1 = ProblemaLectura(1, "Problema 1", "Detalle del problema 1")
        val problema2 = ProblemaLectura(2, "Problema 2", "Detalle del problema 2")
        val problema3 = ProblemaLectura(3, "Problema 3", "Detalle del problema 3")
        val problema4 = ProblemaLectura(4, "Problema 4", "Detalle del problema 4")
        val problema5 = ProblemaLectura(5, "Problema 5", "Detalle del problema 5")
        val problema6 = ProblemaLectura(6, "Problema 6", "Detalle del problema 6")

        val problemas = listOf(
            ProblemaSeleccionado(problema1, true),
            ProblemaSeleccionado(problema2, true),
            ProblemaSeleccionado(problema3, false),
            ProblemaSeleccionado(problema4, true),
            ProblemaSeleccionado(problema5, true),
            ProblemaSeleccionado(problema6, true)
        )

        registroOstViewModel.actualizarUiState(
            uiState.value.copy(listaProblemasSeleccionados = problemas)
        )
    }

    private  fun cargarSoluciones() {
        val soluciones = listOf(
            SolucionLectura(1, "Solución 1", 1),
            SolucionLectura(2, "Solución 2", 2),
            SolucionLectura(4, "Solución 4", 4),
            SolucionLectura(5, "Solución 5", 5)
        )

        registroOstViewModel.actualizarUiState(
            uiState.value.copy(listaSolucionesRegistradas = soluciones)
        )
    }
}
