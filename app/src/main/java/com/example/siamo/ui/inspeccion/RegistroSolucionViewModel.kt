package com.example.siamo.ui.inspeccion

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siamo.data.ProblemaRepository
import com.example.siamo.data.SolucionRepository
import com.example.siamo.model.ProblemaActualizar
import com.example.siamo.model.Solucion
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RegistroSolucionUIState(
    val descripcionSolucion: String = "",
    val detalleProblema: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)


class RegistroSolucionViewModel (
    private val solucionRepository: SolucionRepository,
    private val problemaRepository: ProblemaRepository
) : ViewModel() {

    // Estado de la UI con MutableStateFlow
    private val _uiState = MutableStateFlow(RegistroSolucionUIState())
    val uiState: StateFlow<RegistroSolucionUIState> get() = _uiState

    fun actualizarDescripcionSolucion(descripcion: String) {
        _uiState.update { it.copy(descripcionSolucion = descripcion) }
    }

    fun actualizarDetalleProblema(detalle: String) {
        _uiState.update { it.copy(detalleProblema = detalle) }
    }

    fun registrarSolucionYActualizarProblema(idProblema: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                val nuevaSolucion = Solucion(descripcion = _uiState.value.descripcionSolucion)
                val solucionInsertada = solucionRepository.insertarSolucion(nuevaSolucion)


                val problemaActualizar = ProblemaActualizar(
                    detalle = _uiState.value.detalleProblema,
                    id_Solucion = solucionInsertada.id ?: 0
                )


                problemaRepository.actualizarProblema(idProblema, problemaActualizar)

                _uiState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = e.message ?: "Error")
                }
            }
        }
    }
}
