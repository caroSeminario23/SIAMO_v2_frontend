package com.example.siamo.ui.consulta

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.siamo.SiamoApplication
import com.example.siamo.data.AutomovilRepository
import com.example.siamo.data.ClienteRepository
import com.example.siamo.data.ConsultaRepository
import com.example.siamo.data.PersonaRepository
import com.example.siamo.data.TecnicoRepository
import com.example.siamo.model.Automovil
import com.example.siamo.model.Cliente
import com.example.siamo.model.Consulta
import com.example.siamo.model.ConsultaRequest
import com.example.siamo.model.Persona
import com.example.siamo.model.Tecnico
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import retrofit2.HttpException

data class ConsultaUiState(
    val cliente: Cliente? = null,
    val automovil: Automovil? = null,
    val problema: String? = null,
    val tecnicos: List<Tecnico>? = null,
    val tecnico_asignado: Tecnico? = null,
    val flag_error_buscar_cliente: Boolean = false,
    val flag_ok_buscar_cliente: Boolean = false,
    val flag_error_buscar_automovil: Boolean = false,
    val flag_ok_buscar_automovil: Boolean = false,
    val flag_error_registrar_cliente: Boolean = false,
    val flag_ok_registrar_cliente: Boolean = false,
    val flag_error_registrar_automovil: Boolean = false,
    val flag_ok_registrar_automovil: Boolean = false,
    val flag_error_registrar_consulta: Boolean = false,
    val flag_ok_registrar_consulta: Boolean = false,
    val flag_error_actualizar_cliente: Boolean = false,
    val flag_ok_actualizar_cliente: Boolean = false,
    val flag_error_obtener_tecnico: Boolean = false,
    val flag_ok_obtener_tecnico: Boolean = false
)

class ConsultaViewModel(
    private val clienteRepository: ClienteRepository,
    private val personaRepository: PersonaRepository,
    private val automovilRepository: AutomovilRepository,
    private val consultaRepository: ConsultaRepository,
    private val tecnicoRepository: TecnicoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConsultaUiState())
    val uiState: StateFlow<ConsultaUiState> = _uiState.asStateFlow()

    fun buscarClientePorDni(dni: String) {
        viewModelScope.launch {
            try {
                val cliente = clienteRepository.getCliente(dni)
                _uiState.value = _uiState.value.copy(cliente = cliente, flag_ok_buscar_cliente = true)
            } catch (e: IOException) {
                Log.d("ConsultaViewModel", e.message.toString())
                _uiState.value = _uiState.value.copy(flag_error_buscar_cliente = true)

            } catch (e: HttpException) {
                Log.d("ConsultaViewModel", e.message.toString())
                _uiState.value = _uiState.value.copy(flag_error_buscar_cliente = true)

            }
        }
    }

    fun registrarCliente(persona: Persona) {
        viewModelScope.launch {
            try {
                val personaResponse = personaRepository.postPersona(persona)
                val personaId = personaResponse.id
                val clienteResponse = clienteRepository.postCliente(Cliente(id_persona = personaId!!))

                val cliente = Cliente(
                    id_cliente = clienteResponse.id,
                    persona = persona,
                    id_persona = personaId
                )
                _uiState.value = _uiState.value.copy(cliente = cliente, flag_ok_registrar_cliente = true)
            } catch (e: IOException) {
                _uiState.value = _uiState.value.copy(flag_error_registrar_cliente = true)
            } catch (e: HttpException) {
                _uiState.value = _uiState.value.copy(flag_error_registrar_cliente = true)
            }
        }
    }

    fun actualizarCliente(cliente: Cliente) {
        viewModelScope.launch {
            try {
                personaRepository.putPersona(cliente.id_persona, cliente.persona!!)
                _uiState.value = _uiState.value.copy(cliente = cliente, flag_ok_actualizar_cliente = true)
            } catch (e: IOException) {
                _uiState.value = _uiState.value.copy(flag_error_actualizar_cliente = true)
            } catch (e: HttpException) {
                _uiState.value = _uiState.value.copy(flag_error_actualizar_cliente = true)
            }
        }
    }

    fun aceptarCliente() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(flag_ok_buscar_cliente = true)
        }
    }

    fun buscarAutomovilPorPlaca(placa: String) {
        viewModelScope.launch {
            try {
                val automovil = automovilRepository.getAutomovil(placa)
                _uiState.value = _uiState.value.copy(automovil = automovil, flag_ok_buscar_automovil = true)
            } catch (e: IOException) {
                _uiState.value = _uiState.value.copy(flag_error_buscar_automovil = true)
            } catch (e: HttpException) {
                _uiState.value = _uiState.value.copy(flag_error_buscar_automovil = true)
            }
        }
    }

    fun registrarAutomovil(automovil: Automovil) {
        viewModelScope.launch {
            try {
                val response = automovilRepository.postAutomovil(automovil)
                automovil.id_automovil = response.id
                _uiState.value = _uiState.value.copy(automovil = automovil, flag_ok_registrar_automovil = true)
            } catch (e: IOException) {
                _uiState.value = _uiState.value.copy(flag_error_registrar_automovil = true)
            } catch (e: HttpException) {
                _uiState.value = _uiState.value.copy(flag_error_registrar_automovil = true)
            }
        }
    }

    fun registrarProblema(problema: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(problema = problema)
        }
    }

    fun obtenerTecnicos() {
        viewModelScope.launch {
            try {
                val tecnicos = tecnicoRepository.getAllTecnicos()
                _uiState.value = _uiState.value.copy(tecnicos = tecnicos, flag_ok_obtener_tecnico = true)
            } catch (e: IOException) {
                _uiState.value = _uiState.value.copy(flag_error_obtener_tecnico = true)
            } catch (e: HttpException) {
                _uiState.value = _uiState.value.copy(flag_error_obtener_tecnico = true)
            }
        }
    }

    fun asignarTecnico(tecnico: Tecnico) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(tecnico_asignado = tecnico)
        }
    }

    fun registrarConsulta() {
        viewModelScope.launch {
            try {
                val consulta = Consulta(
                    id_cliente = _uiState.value.cliente!!.id_cliente!!.toInt(),
                    id_automovil = _uiState.value.automovil!!.id_automovil!!.toInt(),
                    id_tecnico = _uiState.value.tecnico_asignado!!.id_tecnico!!.toInt(),
                    prob_declarado = _uiState.value.problema!!.toString(),
                    estado = 1
                )
                Log.d("ConsultaViewModel", "Request data: $consulta")
                consultaRepository.postConsulta(consulta)
                _uiState.value = _uiState.value.copy(flag_ok_registrar_consulta = true)
            } catch (e: IOException) {
                Log.d("ConsultaViewModel", e.message.toString())
                _uiState.value = _uiState.value.copy(flag_error_registrar_consulta = true)
            } catch (e: HttpException) {
                Log.d("ConsultaViewModel", e.message.toString())
                _uiState.value = _uiState.value.copy(flag_error_registrar_consulta = true)
            }
        }
    }

    fun resetFlags() {
        _uiState.value = _uiState.value.copy(
            flag_error_buscar_cliente = false,
            flag_ok_buscar_cliente = false,
            flag_error_buscar_automovil = false,
            flag_ok_buscar_automovil = false,
            flag_error_registrar_cliente = false,
            flag_ok_registrar_cliente = false,
            flag_error_registrar_automovil = false,
            flag_ok_registrar_automovil = false,
            flag_error_registrar_consulta = false,
            flag_ok_registrar_consulta = false,
            flag_error_actualizar_cliente = false,
            flag_ok_actualizar_cliente = false,
            flag_error_obtener_tecnico = false,
            flag_ok_obtener_tecnico = false
        )
    }

    fun resetUiState() {
        _uiState.value = ConsultaUiState()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as SiamoApplication)
                val clienteRepository = application.container.clienteRepository
                val automovilRepository = application.container.automovilRepository
                val consultaRepository = application.container.consultaRepository
                val personRepository = application.container.personaRepository
                val tecnicoRepository = application.container.tecnicoRepository
                ConsultaViewModel(
                    clienteRepository = clienteRepository,
                    automovilRepository = automovilRepository,
                    consultaRepository = consultaRepository,
                    personaRepository = personRepository,
                    tecnicoRepository = tecnicoRepository
                )
            }
        }
    }
}