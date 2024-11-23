package com.example.siamo.ui.inspeccion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siamo.data.ListaProblemasRepository
import com.example.siamo.data.ProblemaRepository
import com.example.siamo.data.SolucionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class InspeccionInicialUiState(
    val problemasConSoluciones: List<ProblemaConSolucion> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

data class ProblemaConSolucion(
    val idProblema: Int,
    val descripcionProblema: String,
    val descripcionSolucion: String?
)

class InspeccionInicialViewModel(
    private val problemaRepository: ProblemaRepository,
    private val listaProblemasRepository: ListaProblemasRepository,
    private val solucionRepository: SolucionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(InspeccionInicialUiState())
    val uiState: StateFlow<InspeccionInicialUiState> = _uiState.asStateFlow()

    fun obtenerProblemasPorConsulta(idConsulta: Int) {

        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            try {
                // Obtener lista de problemas por consulta
                val listaProblemas = listaProblemasRepository.getListaDeProblemasPorConsulta(idConsulta)

                // Mapear los problemas a la lista de ProblemaConSolucion
                val problemasConSoluciones = listaProblemas.map { listaProblema ->
                    val problema = problemaRepository.getProblemaPorId(listaProblema.id_Problema)
                    val descripcionSolucion = if (problema.id_Solucion == null || problema.id_Solucion == 0) {
                        null
                    } else {
                        val solucion = solucionRepository.getSolucionPorId(problema.id_Solucion)
                        solucion?.descripcion
                    }


                    // Crear el objeto con la descripción del problema y solución
                    ProblemaConSolucion(
                        idProblema = problema.id,
                        descripcionProblema = problema.descripcion,
                        descripcionSolucion = descripcionSolucion ,
                    )
                }

                // Actualizar el estado con los problemas cargados
                _uiState.value = InspeccionInicialUiState(
                    problemasConSoluciones = problemasConSoluciones,
                    isLoading = false
                )
            } catch (e: Exception) {

                _uiState.value = InspeccionInicialUiState(
                    isLoading = false,
                    errorMessage = "Error al obtener los datos: ${e.localizedMessage}"
                )
            }
        }
    }

    fun problemasSinSolucion(): Int {
        return _uiState.value.problemasConSoluciones.count { it.descripcionSolucion == null }
    }
}

