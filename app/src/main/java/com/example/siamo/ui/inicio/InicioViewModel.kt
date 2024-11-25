package com.example.siamo.ui.inicio

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.siamo.SiamoApplication
import com.example.siamo.data.EmpleadoRepository
import com.example.siamo.model.Empleado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

data class InicioUiState(
    val empleado: Empleado? = null,
    val flag_error_login: Boolean = false,
    val flag_ok_login: Boolean = false
)

class InicioViewModel(
    private val empleadoRepository: EmpleadoRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(InicioUiState())
    val uiState: StateFlow<InicioUiState> = _uiState.asStateFlow()

    fun loginEmpleado(codEmpleado: Int, contrasenia: String) {
        viewModelScope.launch {
            try {
                val empleado_get = empleadoRepository.loginEmpleado(codEmpleado,contrasenia)
                _uiState.value = _uiState.value.copy(empleado = empleado_get, flag_ok_login = true)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(flag_error_login = true)
            } catch (e: HttpException) {
                _uiState.value = _uiState.value.copy(flag_error_login = true)
            }
        }
    }

    fun resetFlags() {
        _uiState.value = _uiState.value.copy(
            flag_error_login = false,
            flag_ok_login = false
        )
    }

    fun logoutEmpleado() {
        _uiState.value = InicioUiState()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as SiamoApplication)
                val empleadoRepository = application.container.empleadoRepository
                InicioViewModel(empleadoRepository = empleadoRepository)
            }
        }
    }
}