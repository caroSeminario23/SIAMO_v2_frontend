package com.example.siamo.ui.tecnico

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siamo.data.EstadoVehiculoRepository
import com.example.siamo.data.FichaIngresoRepository
import com.example.siamo.model.EstadoVehiculo
import com.example.siamo.model.FichaIngreso
import com.example.siamo.model.FichaIngresoCompleta
import com.example.siamo.model.FichaIngresoRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import retrofit2.HttpException

data class Ost(
    val id_ost: Int,
    val clienteNombre: String,
    val placa: String
)


data class IngresoAutoUiState(
    val isLoading: Boolean = false,
    //val ingresoList: List<Ost> = emptyList(),
    val ingresoList: List<FichaIngresoCompleta> = emptyList(),
    val salidaList: List<Ost> = emptyList(),
    val registroEstadoSuccess: Boolean = false,
    val fichaIngreso: FichaIngreso? = null,
    val estadoVehiculo: EstadoVehiculo? = null,
    val flag_ok_registrar: Boolean = false,
    val flag_error_registrar : Boolean = false,

)

class IngresoSalidaViewModel(
    private val fichaIngresoRepository: FichaIngresoRepository,
    private val estadoVehiculoRepository: EstadoVehiculoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(IngresoAutoUiState())
    val uiState: StateFlow<IngresoAutoUiState> = _uiState.asStateFlow()


    /*private val ingresoList = listOf(
        Ost(id_ost = 1, clienteNombre = "Juan Pérez", placa = "ABC123"),
        Ost(id_ost = 2, clienteNombre = "Ana Gómez", placa = "XYZ456")
    )*/

    private val salidaList = listOf(
        Ost(id_ost = 3, clienteNombre = "Carlos Ramírez", placa = "LMN789"),
        Ost(id_ost = 4, clienteNombre = "Luis González", placa = "DEF012")
    )


    fun cargarIngresoSalida() {
        viewModelScope.launch {
            _uiState.value = IngresoAutoUiState(isLoading = true)


            kotlinx.coroutines.delay(1000)

            _uiState.value = IngresoAutoUiState(
                isLoading = false,
                //ingresoList = ingresoList,
                salidaList = salidaList
            )
        }
    }
    fun cargarFichasPorTecnico(idTecnico: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val fichasIngresoPorTecnico =
                    fichaIngresoRepository.getFullFichasIngresoPorTecnico(idTecnico)

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    ingresoList = fichasIngresoPorTecnico
                )
            } catch (e: Exception) {
                // Manejo de errores
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                )
            }
        }
    }


        fun registrarFichaIngresoYEstadoVehiculo(fichaIngreso: FichaIngreso) {
            viewModelScope.launch {
                try {

                    val fichaIngresoResponse = fichaIngresoRepository.insertarFichaIngreso(
                        FichaIngresoRequest(
                            fecha_aprox_recojo = fichaIngreso.fecha_aprox_recojo ?: "",
                            id_ost = fichaIngreso.estado_vehiculo?.id_estado_vehiculo ?: 0
                        )
                    )

                    val idFichaIngreso = fichaIngresoResponse.id_ficha_ingreso


                    val estadoVehiculo =
                        fichaIngreso.estado_vehiculo?.copy(id_estado_vehiculo = idFichaIngreso)
                    if (estadoVehiculo != null) {
                        estadoVehiculoRepository.insertarEstadoVehiculo(estadoVehiculo)
                    }

                    // Actualizar el estado de la UI
                    _uiState.value =
                        _uiState.value.copy(isLoading = false, flag_ok_registrar = true)

                } catch (e: IOException) {
                    _uiState.value =
                        _uiState.value.copy(flag_error_registrar = true, isLoading = false)
                } catch (e: HttpException) {
                    _uiState.value =
                        _uiState.value.copy(flag_error_registrar = true, isLoading = false)
                }
            }

        }

        fun resetFlags() {
            _uiState.value =
                _uiState.value.copy(flag_error_registrar = false, flag_ok_registrar = false)
        }

        fun resetUiState() {
            _uiState.value = IngresoAutoUiState()
        }


    }
