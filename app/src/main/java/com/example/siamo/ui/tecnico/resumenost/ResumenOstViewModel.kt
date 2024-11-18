package com.example.siamo.ui.tecnico.resumenost

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ResumenOstViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ResumenOstUiState())
    val uiState: StateFlow<ResumenOstUiState> = _uiState

    fun registrarFechaActual() {
        var fechaActual = System.currentTimeMillis()
        _uiState.update {
            it.copy(
                fechaAproxIngreso = fechaActual.toString(),
                registroCancelacionActivo = true
            )
        }
    }

    fun activarRegistroManual() {
        _uiState.update {
            it.copy(
                ingresoFechaActivo = true
            )
        }
    }

    fun actualizarFechaIngreso(fecha: String) {
        _uiState.update {
            it.copy(
                fechaAproxIngreso = fecha,
                registroCancelacionActivo = true
            )
        }
    }

    fun guardarOst() {
        // enviar datos a la base de datos
        val codRespuesta = 200
        if (codRespuesta == 200) {
            _uiState.update {
                it.copy(
                    errorRegistro = 2 // 2 es el código de éxito
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    errorRegistro = 1 // 1 es el código de error
                )
            }
        }
    }

    fun cerrarAlertar() {
        _uiState.update {
            it.copy(
                errorRegistro = 0
            )
        }
    }
}