package com.example.siamo.ui.tecnico.registro_ost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.siamo.ui.tecnico.registro_solucion.RegistroSolucionViewModel

class RegistroSolucionViewModelFactory(
    private val registroOstViewModel: RegistroOstViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistroSolucionViewModel::class.java)) {
            return RegistroSolucionViewModel(registroOstViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}