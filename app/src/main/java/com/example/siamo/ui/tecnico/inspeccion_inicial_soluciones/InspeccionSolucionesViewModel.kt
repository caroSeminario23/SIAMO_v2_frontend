package com.example.siamo.ui.tecnico.inspeccion_inicial_soluciones
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siamo.data.otros_ara.ListaProblemasRepository
import com.example.siamo.data.otros_ara.ProblemaRepository
import com.example.siamo.data.otros_ara.SolucionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class InspeccionInicialUiState(
    val problemasConSoluciones: List<ProblemaConSolucion>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

data class ProblemaConSolucion(
    val idProblema: Int,
    val descripcionProblema: String,
    val descripcionSolucion: String
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

                // Mapeamos los problemas a una lista de ProblemaConSolucion
                val problemasConSoluciones = listaProblemas.map { listaProblema ->
                    val problema = problemaRepository.getProblemaPorId(listaProblema.id_Problema)
                    val solucion = solucionRepository.getSolucionPorId(problema.id_Solucion)

                    // Crear un objeto con la descripción del problema y la descripción de la solución
                    ProblemaConSolucion(
                        idProblema = problema.id,
                        descripcionProblema = problema.descripcion.ifEmpty { "Problema no disponible" },
                        descripcionSolucion = solucion?.descripcion ?: "Solución no disponible"
                    )
                }


                _uiState.value = InspeccionInicialUiState(problemasConSoluciones = problemasConSoluciones, isLoading = false)
            } catch (e: Exception) {

                _uiState.value = InspeccionInicialUiState(isLoading = false, errorMessage = "Error al obtener los datos")
            }
        }
    }
}
