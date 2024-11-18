package com.example.siamo.ui.tecnico.registro_ost

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegistroOstViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RegistroOstUiState())
    val uiState: StateFlow<RegistroOstUiState> = _uiState

    fun actualizarUiState(newState: RegistroOstUiState) {
        _uiState.value = newState
    }
}