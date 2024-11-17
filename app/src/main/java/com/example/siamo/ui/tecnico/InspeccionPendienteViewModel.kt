package com.example.siamo.ui.tecnico
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.siamo.data.inspeccion_pendiente


class InspeccionPendienteViewModel : ViewModel() {

    // Lista mutable de inspecciones pendientes
    private val _inspeccionPendientes = mutableStateListOf(
        inspeccion_pendiente("Erik", "Toyota pequeño", "23X244", "Falla del motor", "001"),
        inspeccion_pendiente("Juan", "Honda Civic", "87Y654", "Luces intermitentes", "002"),
        inspeccion_pendiente("Ana", "Chevrolet Aveo", "12A987", "Frenos desgastados", "003")
    )

    // Lista pública que proporciona acceso solo de lectura
    val inspeccionPendientes: List<inspeccion_pendiente> = _inspeccionPendientes
}
