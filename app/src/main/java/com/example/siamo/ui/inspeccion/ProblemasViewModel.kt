package com.example.siamo.ui.inspeccion
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.siamo.data.ListaProblemasRepository
import com.example.siamo.data.ProblemaRepository
import com.example.siamo.model.ListaProblemas
import com.example.siamo.model.Problema
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class ProblemasUIState(
    val problemasFiltrados: List<ProblemaUI> = emptyList(),
    val problemasSeleccionados: List<ProblemaUI> = emptyList(),
    val searchQuery: String = ""
)


data class ProblemaUI(
    val problema: Problema,
    var isChecked: Boolean = false
)

class ProblemasViewModel(
    private val problemaRepository: ProblemaRepository,
    private val listaProblemasRepository: ListaProblemasRepository
) : ViewModel() {


    private val _uiState = mutableStateOf(ProblemasUIState())
    val uiState: ProblemasUIState get() = _uiState.value

    // Lista de problemas obtenidos de la base de datos
    private val _problemasBase = mutableStateListOf<Problema>()
    private val _problemasSeleccionados = mutableStateListOf<ProblemaUI>()


    suspend fun inicializarProblemas() {
        val problemasExistentes = problemaRepository.getProblemas()
        _problemasBase.clear()
        _problemasBase.addAll(problemasExistentes)

        actualizarProblemasFiltrados("") // Al inicio, no hay filtro
    }


    fun updateSearchQuery(query: String) {
        actualizarProblemasFiltrados(query)
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    // Actualizar la lista de problemas filtrados según la búsqueda
    private fun actualizarProblemasFiltrados(query: String) {
        val filtrados = _problemasBase.filter {
            it.descripcion.contains(query, ignoreCase = true)
        }.map { problema ->
            ProblemaUI(problema)
        }
        _uiState.value = _uiState.value.copy(problemasFiltrados = filtrados)
    }

    // Agregar un problema existente a la lista de seleccionados
    fun seleccionarProblemaExistente(problema: Problema) {
        val problemaUI = ProblemaUI(problema, isChecked = true)
        if (!_problemasSeleccionados.any { it.problema.id  == problema.id }) {
            _problemasSeleccionados.add(problemaUI)
            actualizarEstadoSeleccionados()
        }
    }

    // Agregar un nuevo problema temporal cuando el usuario presiona Enter
    fun agregarNuevoProblema(descripcion: String) {
        if (descripcion.isNotBlank() && !_problemasSeleccionados.any { it.problema.descripcion == descripcion }) {
            val nuevoProblema = Problema(id = 0, descripcion = descripcion, detalle = "", id_Solucion = 0)
            _problemasSeleccionados.add(ProblemaUI(nuevoProblema, isChecked = true))
            actualizarEstadoSeleccionados()
        }
    }

    // Actualizar la lista de seleccionados en el estado de la UI
    private fun actualizarEstadoSeleccionados() {
        _uiState.value = _uiState.value.copy(problemasSeleccionados = _problemasSeleccionados)
    }

    // Cambiar el estado del checkbox (marcar/desmarcar)
    fun toggleCheckbox(problemaUI: ProblemaUI) {
        problemaUI.isChecked = !problemaUI.isChecked
        actualizarEstadoSeleccionados()
    }

    suspend fun guardarProblemasSeleccionados(idConsulta: Int) {
        withContext(Dispatchers.IO) {
            _problemasSeleccionados.filter { it.isChecked }.forEach { problemaUI ->
                val problema = problemaUI.problema
                if (problema.id == 0) {
                    // Insertar nuevo problema en la tabla problema
                    val problemaGuardado = problemaRepository.insertarProblema(problema)
                    problema.id = problemaGuardado.id

                }
                // Insertar en la tabla lista_problemas
                val listaProblema = ListaProblemas(
                    id_Consulta = idConsulta,
                    id_Problema = problema.id
                )
                listaProblemasRepository.insertarListaDeProblemas(listaProblema)
            }
        }
    }


    fun onEnterPressed() {
        if (_uiState.value.searchQuery.isNotEmpty()) {
            agregarNuevoProblema(_uiState.value.searchQuery)
            updateSearchQuery("") // Limpiar el buscador
        }
    }
}




