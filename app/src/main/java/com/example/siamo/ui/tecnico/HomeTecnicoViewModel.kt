package com.example.siamo.ui.tecnico

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.siamo.SiamoApplication
import com.example.siamo.data.graficos.Datos
import com.example.siamo.ui.tecnico.panel_control.GraficosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

data class HomeTecnicoUiState(
    val datosGrafico1: List<Datos> = listOf(),
    val datosGrafico2: List<Datos> = listOf(),
    val datosGrafico3: List<Datos> = listOf(),
    val flag_error_graficos_tecnico: Boolean = false,
    val flag_ok_graficos_tecnico: Boolean = false
)

class HomeTecnicoViewModel(
    private val graficoRepository: GraficosRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(HomeTecnicoUiState())
    val uiState: StateFlow<HomeTecnicoUiState> = _uiState.asStateFlow()

    fun cargarGraficos() {
        viewModelScope.launch {
            try {
                val graficos_get1 = graficoRepository.getDatosGraficoTecnico1(1)
                _uiState.value = _uiState.value.copy(datosGrafico1 = graficos_get1)
                val graficos_get2 = graficoRepository.getDatosGraficoTecnico2(1)
                _uiState.value = _uiState.value.copy(datosGrafico2 = graficos_get2)
                val graficos_get3 = graficoRepository.getDatosGraficoTecnico3(1)
                _uiState.value = _uiState.value.copy(datosGrafico3 = graficos_get3)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(flag_error_graficos_tecnico = true)
            } catch (e: HttpException) {
                _uiState.value = _uiState.value.copy(flag_error_graficos_tecnico = true)
            }
        }
    }

    fun resetFlags() {
        _uiState.value = _uiState.value.copy(
            flag_error_graficos_tecnico = false,
            flag_ok_graficos_tecnico = false
        )
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as SiamoApplication)
                val graficoRepository = application.container.graficosRepository
                HomeTecnicoViewModel(graficoRepository = graficoRepository)
            }
        }
    }
}