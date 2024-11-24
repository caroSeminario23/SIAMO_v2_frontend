package com.example.siamo.ui.ost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.siamo.SiamoApplication
import com.example.siamo.data.OstRepository
import com.example.siamo.model.OrderServicioTecnico
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

data class BuscarOstUiState(
    val isLoading: Boolean = false,
    val ost: OrderServicioTecnico? = null,
    val flag_error_buscar_ost: Boolean = false,
    val flag_ok_buscar_ost: Boolean = false
)

class BuscarOstViewModel (
    private val ostRepository: OstRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BuscarOstUiState())
    val uiState: StateFlow<BuscarOstUiState> = _uiState.asStateFlow()

    fun getOstById(id: Int) {
        viewModelScope.launch {
            _uiState.value = BuscarOstUiState(isLoading = true)
            try {
                val ost = ostRepository.getOstById(id)
                _uiState.value = _uiState.value.copy(ost = ost, flag_ok_buscar_ost = true)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(flag_error_buscar_ost = true)
            } catch (e: HttpException) {
                _uiState.value = _uiState.value.copy(flag_error_buscar_ost = true)
            }
        }
    }

    fun resetFlags() {
        _uiState.value = _uiState.value.copy(flag_error_buscar_ost = false, flag_ok_buscar_ost = false)
    }

    fun resetUiState() {
        _uiState.value = BuscarOstUiState()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as SiamoApplication)
                val ostRepository = application.container.ostRepository
                BuscarOstViewModel(ostRepository = ostRepository)
            }
        }
    }
}

