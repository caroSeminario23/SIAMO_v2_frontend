package com.example.siamo.ui.tecnico.inspecciones_pendientes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siamo.data.otros_ara.ConsultaRepository
import com.example.siamo.model.Consulta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class InspeccionUiState(
    val inspeccionPendientes: List<Consulta>? = null ,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)


class InspeccionPendienteViewModel(
    private val consultaRepository: ConsultaRepository
) : ViewModel() {


    private val _uiState = MutableStateFlow(InspeccionUiState())
    val uiState: StateFlow<InspeccionUiState> = _uiState.asStateFlow()

    fun consultasPorIdTecnico(idTecnico: Int) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            try {

                val consultas = consultaRepository.getConsulta(idTecnico)


                _uiState.value = _uiState.value.copy(
                    inspeccionPendientes = consultas,
                    isLoading = false
                )
            } catch (e: Exception) {

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Error al cargar las consultas: ${e.message}"
                )
            }
        }
    }
}

