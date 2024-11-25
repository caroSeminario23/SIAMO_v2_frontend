package com.example.siamo.ui.tecnico.inspeccion_inicial_soluciones

import androidx.lifecycle.ViewModel
import com.example.siamo.data.problema.ProblemaLectura
import com.example.siamo.data.problema.ProblemaRegistro
import com.example.siamo.data.problema.ProblemaSeleccionado
import com.example.siamo.data.solucion.SolucionLectura
import com.example.siamo.model.Solucion
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstUiState
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstViewModel
import kotlinx.coroutines.flow.StateFlow

class InspeccionSolucionesViewModel(
    private val registroOstViewModel: RegistroOstViewModel
) : ViewModel() {
    val uiState: StateFlow<RegistroOstUiState> = registroOstViewModel.uiState

    init {
        //cargarProblemas()
        //cargarSoluciones()
    }

    private fun cargarProblemas() {
        val problema1 = ProblemaLectura(id_problema = 1, descripcion = "Problema 1", detalle = "Detalle 1")
        val problema2 = ProblemaLectura(id_problema = 2, descripcion = "Problema 2", detalle = "Detalle 2")
        val problema3 = ProblemaLectura(id_problema = 3, descripcion = "Problema 3", detalle = "Detalle 3")
        val problema4 = ProblemaLectura(id_problema = 4, descripcion = "Problema 4", detalle = "Detalle 4")
        val problema5 = ProblemaLectura(id_problema = 5, descripcion = "Problema 5", detalle = "Detalle 5")
        val problema6 = ProblemaLectura(id_problema = 6, descripcion = "Problema 6", detalle = "Detalle 6")

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
            SolucionLectura(id_problema = 1, Solucion(id_solucion = 1, descripcion = "Solución 1")),
            SolucionLectura(id_problema = 2, Solucion(id_solucion = 2, descripcion = "Solución 2")),
            SolucionLectura(id_problema = 4, Solucion(id_solucion = 4, descripcion = "Solución 4")),
            SolucionLectura(id_problema = 5, Solucion(id_solucion = 5, descripcion = "Solución 5"))
        )

        registroOstViewModel.actualizarUiState(
            uiState.value.copy(listaSolucionesRegistradas = soluciones)
        )
    }

    fun guardarProblema(problemaSeleccionado: ProblemaSeleccionado) {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                problemaAGuardar = problemaSeleccionado
            )
        )
    }
}
