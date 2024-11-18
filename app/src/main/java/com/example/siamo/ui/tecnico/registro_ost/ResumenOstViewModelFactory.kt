package com.example.siamo.ui.tecnico.registro_ost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.siamo.ui.tecnico.resumen_ost.ResumenOstViewModel

class ResumenOstViewModelFactory(
    private val registroOstViewModel: RegistroOstViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResumenOstViewModel::class.java)) {
            return ResumenOstViewModel(registroOstViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}