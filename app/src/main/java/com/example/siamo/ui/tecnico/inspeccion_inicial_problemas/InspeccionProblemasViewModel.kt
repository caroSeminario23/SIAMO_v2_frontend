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
            ProblemaLectura(id_problema = 1, descripcion = "Problema 1", detalle = "Detalle 1"),
            ProblemaLectura(id_problema = 2, descripcion = "Problema 2", detalle = "Detalle 2"),
            ProblemaLectura(id_problema = 3, descripcion = "Problema 3", detalle = "Detalle 3"),
            ProblemaLectura(id_problema = 4, descripcion = "Problema 4", detalle = "Detalle 4"),
            ProblemaLectura(id_problema = 5, descripcion = "Problema 5", detalle = "Detalle 5"),
            ProblemaLectura(id_problema = 6, descripcion = "Problema 6", detalle = "Detalle 6"),
            ProblemaLectura(id_problema = 7, descripcion = "Problema 7", detalle = "Detalle 7"),
            ProblemaLectura(id_problema = 8, descripcion = "Problema 8", detalle = "Detalle 8"),
            ProblemaLectura(id_problema = 9, descripcion = "Problema 9", detalle = "Detalle 9"),
            ProblemaLectura(id_problema = 10, descripcion = "Problema 10", detalle = "Detalle 10")
        )
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(listaProblemasBitacora = problemas)
        )
    }

    private fun buscarProblema(query: String) {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                resultadosBusquedaProblemas = uiState.value.listaProblemasBitacora.filter { problema ->
                    problema.descripcion?.contains(query, ignoreCase = true) == true
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
                problemaBuscado = problema.descripcion?.toString() ?: "NA",
                activoRegistroParaProblemaNoRegistrado = false
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

    fun registrarNuevoProblema() {
        if (uiState.value.descripcionProblema.isNotEmpty()) {
            var nuevoProblema = ProblemaLectura(
                id_problema = 0, // ID temporal
                descripcion = uiState.value.descripcionProblema,
                detalle = ""
            )

            var problemaSeleccionado = ProblemaSeleccionado(
                problema = nuevoProblema
            )

            registroOstViewModel.actualizarUiState(
                uiState.value.copy(
                    problemaAGuardar = problemaSeleccionado,
                    listaProblemasSeleccionados = uiState.value.listaProblemasSeleccionados + problemaSeleccionado,
                    activoRegistroParaProblemaNoRegistrado = false,
                )
            )
        }

    }

    fun actualizarDescripcionProblema(descripcion: String) {
        if (descripcion.isEmpty() || descripcion != null) {
            registroOstViewModel.actualizarUiState(
                uiState.value.copy(
                    descripcionProblema = descripcion,
                    activoRegistroParaProblemaNoRegistrado = true
                )
            )
        }
    }
}