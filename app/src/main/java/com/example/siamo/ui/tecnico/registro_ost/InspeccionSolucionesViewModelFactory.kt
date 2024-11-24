package com.example.siamo.ui.tecnico.registro_ost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.siamo.ui.tecnico.inspeccion_inicial_soluciones.InspeccionSolucionesViewModel

class InspeccionSolucionesViewModelFactory(
    private val registroOstViewModel: RegistroOstViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InspeccionSolucionesViewModel::class.java)) {
            return InspeccionSolucionesViewModel(registroOstViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}