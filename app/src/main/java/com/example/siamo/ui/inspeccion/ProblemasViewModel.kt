package com.example.siamo.ui.inspeccion
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import com.example.siamo.data.inspeccion_problema
import androidx.compose.runtime.setValue

class ProblemasViewModel : ViewModel() {

    // Lista completa de problemas
    private val _listaProblemas = mutableStateListOf(
        inspeccion_problema("Problema 1"),
        inspeccion_problema("Problema 2"),
        inspeccion_problema("Problema 3"),
        inspeccion_problema("Problema 4"),
        inspeccion_problema("Problema 5"),
        inspeccion_problema("Problema 6"),
        inspeccion_problema("Problema 7"),
        inspeccion_problema("Problema 8"),
        inspeccion_problema("Problema 9"),
        inspeccion_problema("Problema 10"),
        inspeccion_problema("Problema 11")

    )
    val listaProblemas: List<inspeccion_problema> = _listaProblemas

    // Lista de problemas seleccionados
    private val _listaSeleccionada = mutableStateListOf<inspeccion_problema>()
    val listaSeleccionada: List<inspeccion_problema> = _listaSeleccionada

    // Estado del texto de búsqueda
    var searchQuery by mutableStateOf("")
        private set

    // Función para filtrar problemas
    val filteredProblemas: List<inspeccion_problema>
        get() = listaProblemas.filter {
            it.descripcion.contains(searchQuery, ignoreCase = true) && searchQuery.isNotEmpty()
        }

    // Actualizar el texto de búsqueda
    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    // Seleccionar un problema desde el menú desplegable
    fun seleccionarProblema(problema: inspeccion_problema) {
        if (!_listaSeleccionada.contains(problema)) {
            _listaSeleccionada.add(problema)
        }
        searchQuery = "" // Limpiar el texto después de seleccionar
    }

    // Deseleccionar un problema (desde la lista seleccionada)
    fun deseleccionarProblema(problema: inspeccion_problema) {
        _listaSeleccionada.remove(problema)
    }
    fun onEnterPressed() {
        if (searchQuery.isNotEmpty()) {
            val nuevoProblema = inspeccion_problema(searchQuery)
            seleccionarProblema(nuevoProblema)
            updateSearchQuery("")
        }
    }


}
