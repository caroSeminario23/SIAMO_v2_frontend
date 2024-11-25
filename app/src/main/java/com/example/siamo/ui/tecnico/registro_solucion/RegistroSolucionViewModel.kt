package com.example.siamo.ui.tecnico.registro_solucion

import androidx.lifecycle.ViewModel
import com.example.siamo.data.problema.ProblemaLectura
import com.example.siamo.data.problema.ProblemaRegistro
import com.example.siamo.data.solucion.SolucionLectura
import com.example.siamo.data.solucion.SolucionRegistro
import com.example.siamo.model.Solucion
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
                detalle = problemaDetalle,
                id_solucion = 4
            )

            // Registrar el problema y hacer un get para obtener su id
            val problemaRegistrado = ProblemaLectura(
                id_problema = 4,
                descripcion = problemaDescripcion,
                detalle = problemaDetalle
            )

            //val idProblema = problemaRegistrado.idProblema

            val solucionNueva = SolucionRegistro(
                descripcion = solucionPropuesta,
                idProblema = problemaRegistrado.id_problema?:0
            )

            // se registra y luego se llama con get para obtener su id
            val solucionLectura = SolucionLectura(
                id_problema = problemaRegistrado.id_problema?:0,
                solucion = Solucion(
                    id_solucion = 4,
                    descripcion = solucionPropuesta
                )
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
