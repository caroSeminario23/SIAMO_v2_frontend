package com.example.siamo.ui.tecnico.registro_ost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.siamo.ui.tecnico.inspeccion_inicial_problemas.InspeccionProblemasViewModel

class InspeccionProblemasViewModelFactory(
    private val registroOstViewModel: RegistroOstViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InspeccionProblemasViewModel::class.java)) {
            return InspeccionProblemasViewModel(registroOstViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}