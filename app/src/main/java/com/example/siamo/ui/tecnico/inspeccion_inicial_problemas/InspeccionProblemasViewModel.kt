package com.example.siamo.ui.tecnico.inspeccion_inicial_problemas

import androidx.lifecycle.ViewModel
import com.example.siamo.data.problema.ProblemaLectura
import com.example.siamo.data.problema.ProblemaSeleccionado
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstUiState
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstViewModel
import kotlinx.coroutines.flow.StateFlow

class InspeccionProblemasViewModel(
    private val registroOstViewModel: RegistroOstViewModel
) : ViewModel() {
    val uiState: StateFlow<RegistroOstUiState> = registroOstViewModel.uiState

    init {
        cargarProblemas()
    }

    private fun cargarProblemas() {
        val problemas = listOf(
            ProblemaLectura(1, "Problema 1", "Detalle 1"),
            ProblemaLectura(2, "Problema 2", "Detalle 2"),
            ProblemaLectura(3, "Problema 3", "Detalle 3"),
            ProblemaLectura(4, "Problema 4", "Detalle 4"),
            ProblemaLectura(5, "Problema 5", "Detalle 5"),
            ProblemaLectura(6, "Problema 6", "Detalle 6"),
            ProblemaLectura(7, "Problema 7", "Detalle 7"),
            ProblemaLectura(8, "Problema 8", "Detalle 8"),
            ProblemaLectura(9, "Problema 9", "Detalle 9"),
            ProblemaLectura(10, "Problema 10", "Detalle 10")
        )
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(listaProblemasBitacora = problemas)
        )
    }

    private fun buscarProblema(query: String) {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                resultadosBusquedaProblemas = uiState.value.listaProblemasBitacora.filter { problema ->
                    problema.descripcion.contains(query, ignoreCase = true)
                },
            )
        )
    }

    fun actualizarQuery(newQuery: String) {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                problemaBuscado = newQuery,
                problemaSeleccionadoTemp = null,
                mostrarResultadosBusquedaProblemas = true
            )
        )
        buscarProblema(newQuery)
    }

    fun onSearch() {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                mostrarResultadosBusquedaProblemas = false
            )
        )
    }

    fun seleccionarProblema(problema: ProblemaLectura) {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                problemaSeleccionadoTemp = problema,
                mostrarResultadosBusquedaProblemas = false,
                problemaBuscado = problema.descripcion
            )
        )
    }

    fun toogleMarcadoProblema(index: Int) {
        val state = uiState.value
        val nuevaLista = state.listaProblemasSeleccionados.toMutableList()
        val problema = nuevaLista[index]
        nuevaLista[index] = problema.copy(seleccionado = !problema.seleccionado)
        registroOstViewModel.actualizarUiState(
            state.copy(
                listaProblemasSeleccionados = nuevaLista
            )
        )
    }

    fun registrarProblema() {
        val state = uiState.value
        val problemaTemp = state.problemaSeleccionadoTemp

        if (problemaTemp != null) {
            val problemaSeleccionado = ProblemaSeleccionado(
                problema = problemaTemp
            )
            registroOstViewModel.actualizarUiState(
                state.copy(
                    listaProblemasSeleccionados = state.listaProblemasSeleccionados + problemaSeleccionado,
                    problemaBuscado = "",
                    problemaSeleccionadoTemp = null
                )
            )
        }
    }
}