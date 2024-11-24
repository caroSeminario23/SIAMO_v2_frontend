package com.example.siamo.ui.tecnico.registro_solucion

import androidx.lifecycle.ViewModel
import com.example.siamo.data.problema.ProblemaLectura
import com.example.siamo.data.problema.ProblemaRegistro
import com.example.siamo.data.solucion.SolucionLectura
import com.example.siamo.data.solucion.SolucionRegistro
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstUiState
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstViewModel
import kotlinx.coroutines.flow.StateFlow

class RegistroSolucionViewModel(
    private val registroOstViewModel: RegistroOstViewModel
) : ViewModel() {
    val uiState: StateFlow<RegistroOstUiState> = registroOstViewModel.uiState

    fun actualizarDetalleProblema(detalleProblema: String) {
        if (detalleProblema.isEmpty() || detalleProblema != null) {
            registroOstViewModel.actualizarUiState(
                uiState.value.copy(
                    detalleProblema = detalleProblema
                )
            )
        }
    }

    fun actualizarSolucionPropuesta(solucionPropuesta: String) {
        if (solucionPropuesta.isEmpty() || solucionPropuesta != null) {
            registroOstViewModel.actualizarUiState(
                uiState.value.copy(
                    solucionPropuesta = solucionPropuesta
                )
            )
        }
    }

    fun registrarSolucion() {
        val state = uiState.value
        val problemaDescripcion = uiState.value.descripcionProblema
        val problemaDetalle = uiState.value.detalleProblema
        val solucionPropuesta = uiState.value.solucionPropuesta

        if (problemaDescripcion.isNotEmpty() && problemaDetalle.isNotEmpty() && solucionPropuesta.isNotEmpty()) {
            val problemaNuevo = ProblemaRegistro(
                descripcion = problemaDescripcion,
                detalle = problemaDetalle
            )

            // Registrar el problema y hacer un get para obtener su id
            val problemaRegistrado = ProblemaLectura(
                idProblema = 4,
                descripcion = problemaDescripcion,
                detalle = problemaDetalle
            )

            //val idProblema = problemaRegistrado.idProblema

            val solucionNueva = SolucionRegistro(
                descripcion = solucionPropuesta,
                idProblema = problemaRegistrado.idProblema
            )

            // se registra y luego se llama con get para obtener su id
            val solucionLectura = SolucionLectura(
                idSolucion = 4,
                descripcion = solucionPropuesta,
                idProblema = problemaRegistrado.idProblema
            )

            registroOstViewModel.actualizarUiState(
                state.copy(
                    problemaNuevo = problemaNuevo,
                    solucionNueva = solucionNueva,
                    listaSolucionesRegistradas = state.listaSolucionesRegistradas + solucionLectura
                )
            )
        }
    }
}
