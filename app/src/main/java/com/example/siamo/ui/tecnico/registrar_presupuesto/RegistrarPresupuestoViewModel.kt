package com.example.siamo.ui.tecnico.registrar_presupuesto

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.siamo.SiamoApplication
import com.example.siamo.data.consulta.ConsultaLectura
import com.example.siamo.data.ficha_ingreso.FichaIngresoRegistro
import com.example.siamo.data.ost.OstLectura
import com.example.siamo.data.ost.OstRegistro
import com.example.siamo.data.otros_ara.ConsultaRepository
import com.example.siamo.data.otros_ara.ListaProblemasRepository
import com.example.siamo.data.otros_ara.ProblemaRepository
import com.example.siamo.data.otros_ara.SolucionRepository
import com.example.siamo.data.presupuesto.PresupuestoLectura
import com.example.siamo.data.presupuesto.PresupuestoRegistro
import com.example.siamo.data.problema.ProblemaLectura
import com.example.siamo.data.problema.ProblemaRegistro
import com.example.siamo.data.problema.ProblemaSeleccionado
import com.example.siamo.data.repuesto.Repuesto
import com.example.siamo.data.repuesto.RepuestoSeleccionado
import com.example.siamo.data.solucion.SolucionLectura
import com.example.siamo.model.ListaProblemas
import com.example.siamo.model.PresupuestoRepuesto
import com.example.siamo.model.Solucion
import com.example.siamo.ui.tecnico.presupuesto.PresupuestoRepository
import com.example.siamo.ui.tecnico.presupuesto.PresupuestoViewModel.Companion.TARIFA_POR_TECNICO
import com.example.siamo.ui.tecnico.resumen_ost.ResumenOstRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class RegistrarPresupuestoUiState(
    val id_consulta: Int = 23,
    val id_presupuesto: Int? = null,

    // Busqueda y seleccion de problemas
    val listaProblemasBitacora: List<ProblemaLectura> = emptyList(), //todos los registrados
    val problemaBuscado: String = "",
    val mostrarResultadosBusquedaProblemas: Boolean = false,
    val resultadosBusquedaProblemas: List<ProblemaLectura> = emptyList(),
    val listaProblemasSeleccionados: List<ProblemaSeleccionado> = emptyList(),
    val searchBarActivaProblema: Boolean = false,
    val problemaSeleccionadoTemp: ProblemaLectura? = null,

    // Mostrar lista de soluciones de los problemas registrados
    val listaSolucionesRegistradas: List<SolucionLectura> = emptyList(),

    // Registro de problema y solución no registrados
    val descripcionProblema: String = "",
    val detalleProblema: String = "",
    val solucionPropuesta: String = "",
    val problemaNuevo: ProblemaRegistro? = null,
    val solucionNueva: Solucion? = null,
    val problemaAGuardar: ProblemaSeleccionado? = null,
    val activoRegistroParaProblemaNoRegistrado: Boolean = false,

    val flag_ok_registro_problema: Boolean = false,
    val flag_error_registro_problema: Boolean = false,

    // Captura del número de técnicos
    val numeroTecnicos: String = "",

    // Busqueda y seleccion de repuestos
    val listaRepuestos: List<Repuesto> = emptyList(),
    val repuestoBuscado: String = "", // Palabra para buscar el repuesto
    val mostrarResultadosBusquedaRepuestos: Boolean = false,
    val resultadosBusquedaRepuesto: List<Repuesto> = emptyList(),
    val listaRepuestosSeleccionados: List<RepuestoSeleccionado> = emptyList(),
    val searchBarActivaRepuesto: Boolean = false,
    val repuestoSeleccionadoTemp: Repuesto? = null,
    val cantidadRepuesto: String = "",

    // Cálculo de presupuesto
    val presupuestoEstimado: Double = 0.0,
    val porcentajeDescuento: String = "",
    val descuentoEnSoles: Double = 0.0,
    val presupuestoFinal: Double = 0.0,

    // Llamado a la consulta (id)
    val idConsulta: ConsultaLectura? = null,

    // Se crea el presupuesto sin pasarle id
    val presupuesto: PresupuestoRegistro? = null,

    // Luego se registran sus repuestos asociados
    val idPresupuesto: PresupuestoLectura? = null,
    val presupuestoRepuesto: PresupuestoRepuesto? = null,

    // Registro de fecha de ingreso
    val expandedDia: Boolean = false,
    val expandedMes: Boolean = false,
    val expandedAnio: Boolean = false,
    val diaSeleccionado: Int = 0,
    val mesSeleccionado: Int = 0,
    val anioSeleccionado: Int = 0,
    val desplegablesHabilitados: Boolean = false,
    val fechaAproxIngreso: String = "",

    // Registro de Ost
    val ost: OstRegistro? = null, // definir fechaRegistro y estado: 1

    // Registro de ficha de ingreso cuando el vehículo ingresa a taller en ese momento
    val fechaIngresoFicha: String = "",
    val fechaRecogidaAprox: String = "",
    val idOst: OstLectura? = null,
    val fichaIngreso: FichaIngresoRegistro? = null, // definir fechaIngreso como fechaAproxIngreso

    // Activación de botones de registro y cancelación
    val registroCancelacionActivo1: Boolean = false,
    val registroCancelacionActivo2: Boolean = false,
    val registroCancelacionActivo3: Boolean = false,

    val flag_ok_registro_presupuesto: Boolean = false,
    val flag_error_registro_presupuesto: Boolean = false,

    val flag_ok_registro_ost: Boolean = false,
    val flag_error_registro_ost: Boolean = false
)

class RegistrarPresupuestoViewModel(
    private val problemaRepository: ProblemaRepository,
    private val solucionRepository: SolucionRepository,
    private val presupuestoRepository: PresupuestoRepository,
    private val consultaRepository: ConsultaRepository,
    private val listaProblemasRepository: ListaProblemasRepository,
    private val ostRepository: ResumenOstRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegistrarPresupuestoUiState())
    val uiState: StateFlow<RegistrarPresupuestoUiState> = _uiState.asStateFlow()

    init {
        cargarProblemas()
    }

    private fun cargarProblemas() {
        viewModelScope.launch {
            try {
                val problemas = problemaRepository.getProblemas()
                _uiState.value = _uiState.value.copy(listaProblemasBitacora = problemas)
            } catch (e: Exception) {
                Log.e("RegistrarPresupuestoViewModel", "Error al cargar problemas", e)
            } catch (e: HttpException) {
                Log.e("RegistrarPresupuestoViewModel", "Error al cargar problemas", e)
            }
        }
    }

    private fun buscarProblema(query: String) {
        _uiState.value = _uiState.value.copy(
            resultadosBusquedaProblemas = _uiState.value.listaProblemasBitacora.filter { problema ->
                problema.descripcion?.contains(query, ignoreCase = true) ?: false
            }
        )
    }

    fun actualizarQuery(newQuery: String) {
        _uiState.value = _uiState.value.copy(
            problemaBuscado = newQuery,
            problemaSeleccionadoTemp = null,
            mostrarResultadosBusquedaProblemas = true
        )
        buscarProblema(newQuery)
    }

    fun onSearch() {
        _uiState.value = _uiState.value.copy(
            mostrarResultadosBusquedaProblemas = false
        )
    }

    fun seleccionarProblema(problema: ProblemaLectura) {
        _uiState.value = _uiState.value.copy(
            problemaSeleccionadoTemp = problema,
            mostrarResultadosBusquedaProblemas = false,
            problemaBuscado = problema.descripcion ?: "",
            activoRegistroParaProblemaNoRegistrado = false
        )
    }

    fun toogleMarcadoProblema(index: Int) {
        val state = uiState.value
        val nuevaLista = state.listaProblemasSeleccionados.toMutableList()
        val problema = nuevaLista[index]
        nuevaLista[index] = problema.copy(seleccionado = !problema.seleccionado)
        _uiState.value = state.copy(
            listaProblemasSeleccionados = nuevaLista
        )
    }

    fun registrarProblema() {
        val state = uiState.value
        val problema = state.problemaSeleccionadoTemp
        if (problema != null) {
            _uiState.value = state.copy(
                listaProblemasSeleccionados = state.listaProblemasSeleccionados + ProblemaSeleccionado(
                    problema = problema
                ),
                problemaSeleccionadoTemp = null,
//                mostrarResultadosBusquedaProblemas = false,
                problemaBuscado = ""
            )
        }
    }

    fun registrarNuevoProblema() {
        if (uiState.value.descripcionProblema.isNotEmpty()) {
            val nuevoProblema = ProblemaLectura(
                descripcion = uiState.value.descripcionProblema,
                detalle = uiState.value.detalleProblema
            )

            var problemaSeleccionado = ProblemaSeleccionado(
                problema = nuevoProblema
            )

            _uiState.value = uiState.value.copy(
                problemaAGuardar = problemaSeleccionado,
                listaProblemasSeleccionados = uiState.value.listaProblemasSeleccionados + problemaSeleccionado,
                activoRegistroParaProblemaNoRegistrado = false,
            )
        }
    }

    fun actualizarDescripcionProblema(descripcion: String) {
        if (descripcion.isEmpty() || descripcion != null) {
            _uiState.value = uiState.value.copy(
                descripcionProblema = descripcion,
                activoRegistroParaProblemaNoRegistrado = true
            )
        }
    }

    fun cargarSoluciones() {
        viewModelScope.launch {
            for (
            problema in uiState.value.listaProblemasSeleccionados
            ) {
                try {
                    val soluciones =
                        problemaRepository.getProblemaPorId(problema.problema.id_problema ?: 0)
                    _uiState.value = _uiState.value.copy(
                        listaSolucionesRegistradas = uiState.value.listaSolucionesRegistradas + soluciones
                    )

                } catch (e: Exception) {
                    Log.e("RegistrarPresupuestoViewModel", "Error al cargar soluciones", e)
                } catch (e: HttpException) {
                    Log.e("RegistrarPresupuestoViewModel", "Error al cargar soluciones", e)
                }
            }
        }
    }

    fun guardarProblema(problemaSeleccionado: ProblemaSeleccionado) {
        _uiState.value = _uiState.value.copy(
            problemaAGuardar = problemaSeleccionado
        )
    }

    fun actualizarDetalleProblema(detalleProblema: String) {
        if (detalleProblema.isEmpty() || detalleProblema != null) {
            _uiState.value = _uiState.value.copy(
                detalleProblema = detalleProblema
            )
        }
    }

    fun actualizarSolucionPropuesta(solucionPropuesta: String) {
        if (solucionPropuesta.isEmpty() || solucionPropuesta != null) {
            _uiState.value = _uiState.value.copy(
                solucionPropuesta = solucionPropuesta
            )
        }
    }

    fun registrarSolucion() {
        val state = uiState.value
        val problemaDescripcion = state.descripcionProblema
        val problemaDetalle = state.detalleProblema
        val solucionPropuesta = state.solucionPropuesta

        if (problemaDescripcion.isNotEmpty() && problemaDetalle.isNotEmpty() && solucionPropuesta.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    // Crear la solución
                    val solucion = Solucion(descripcion = solucionPropuesta)
                    val response = solucionRepository.insertarSolucion(solucion)

                    // Crear el problema con el ID de la solución
                    val problemaRegistro = ProblemaRegistro(
                        descripcion = problemaDescripcion,
                        detalle = problemaDetalle,
                        id_solucion = response.id ?: 0
                    )

                    val responseProblema = problemaRepository.insertarProblema(problemaRegistro)

                    // Actualizar el estado de la UI si es necesario
                    _uiState.value = state.copy(
                        problemaNuevo = problemaRegistro,
                        solucionNueva = solucion,
                        listaProblemasSeleccionados = state.listaProblemasSeleccionados.filter {
                            it.problema != state.problemaAGuardar?.problema
                        } + ProblemaSeleccionado(
                            problema = ProblemaLectura(
                                id_problema = responseProblema.id ?: 0,
                                descripcion = problemaDescripcion,
                                detalle = problemaDetalle
                            )
                        ),
                        listaSolucionesRegistradas = state.listaSolucionesRegistradas + SolucionLectura(
                            id_problema = responseProblema.id ?: 0,
                            solucion = solucion,
                        ),
                        flag_ok_registro_problema = true
                    )

                    Log.d(
                        "ListaProblemas",
                        "Lista de problemas: ${_uiState.value.listaProblemasSeleccionados.size}"
                    )
                    Log.d(
                        "ListaSoluciones",
                        "Lista de soluciones: ${_uiState.value.listaSolucionesRegistradas.size}"
                    )
                } catch (e: Exception) {
                    Log.e(
                        "RegistrarPresupuestoViewModel",
                        "Error al registrar solución y problema",
                        e
                    )
                    _uiState.value = state.copy(
                        flag_error_registro_problema = true
                    )
                } catch (e: HttpException) {
                    Log.e(
                        "RegistrarPresupuestoViewModel",
                        "Error al registrar solución y problema",
                        e
                    )
                    _uiState.value = state.copy(
                        flag_error_registro_problema = true
                    )
                }
            }
        }
    }

    fun onResetRegitserProblema() {
        _uiState.value = _uiState.value.copy(
            flag_ok_registro_problema = false,
            flag_error_registro_problema = false
        )
    }

    fun cargarRepuestos() {
        viewModelScope.launch {
            try {
                val repuestos = presupuestoRepository.getRepuestos()
                _uiState.value = _uiState.value.copy(listaRepuestos = repuestos)
            } catch (e: Exception) {
                Log.e("RegistrarPresupuestoViewModel", "Error al cargar repuestos", e)
            } catch (e: HttpException) {
                Log.e("RegistrarPresupuestoViewModel", "Error al cargar repuestos", e)
            }
        }
    }

    private fun buscarRepuesto(query: String) {
        _uiState.value = _uiState.value.copy(
            resultadosBusquedaRepuesto = _uiState.value.listaRepuestos.filter { repuesto ->
                repuesto.descripcion?.contains(query, ignoreCase = true) ?: false
            }
        )
    }

    fun actualizarNumeroTecnicos(numero: String) {
        if (numero.isEmpty() || numero.toIntOrNull() != null) {
            _uiState.value = _uiState.value.copy(numeroTecnicos = numero)
            calcularPresupuesto()
        }
    }

    fun actualizarCantidadRepuesto(cantidad: String) {
        if (cantidad.isEmpty() || cantidad.toIntOrNull() != null) {
            _uiState.value = _uiState.value.copy(cantidadRepuesto = cantidad)
        }
    }

    fun actualizarPorcentajeDescuento(porcentaje: String) {
        if (porcentaje.isEmpty() || porcentaje.toDoubleOrNull() != null) {
            _uiState.value = _uiState.value.copy(porcentajeDescuento = porcentaje)
            calcularPresupuesto()
        }
    }

    fun actualizarQueryRepuesto(newQuery: String) {
        _uiState.value = _uiState.value.copy(
            repuestoBuscado = newQuery,
            repuestoSeleccionadoTemp = null,
            mostrarResultadosBusquedaRepuestos = true
        )
        buscarRepuesto(newQuery)
    }

    fun registrarRepuesto() {
        val state = uiState.value
        val cantidad = state.cantidadRepuesto.toIntOrNull() ?: 0
        Log.d("RegistrarPresupuestoViewModel", "Cantidad: $cantidad")
        val repuestoTemp = state.repuestoSeleccionadoTemp

        if (cantidad > 0 && repuestoTemp != null) {
            val repuestoSeleccionado = RepuestoSeleccionado(
                repuesto = repuestoTemp,
                cantidad = cantidad
            )
            _uiState.value = state.copy(
                listaRepuestosSeleccionados = state.listaRepuestosSeleccionados + repuestoSeleccionado,
                repuestoSeleccionadoTemp = null,
                cantidadRepuesto = ""
            )
            calcularPresupuesto()
        }
    }

    fun seleccionarRepuesto(repuesto: Repuesto) {
        val cantidad = uiState.value.cantidadRepuesto.toIntOrNull() ?: 0
        _uiState.value = uiState.value.copy(
            repuestoSeleccionadoTemp = repuesto,
            cantidadRepuesto = cantidad.toString(),
            mostrarResultadosBusquedaRepuestos = false,
            repuestoBuscado = repuesto.descripcion ?: ""
        )
    }

    fun toggleMarcadoRepuesto(index: Int) {
        val state = uiState.value
        val nuevaLista = state.listaRepuestosSeleccionados.toMutableList()
        val repuesto = nuevaLista[index]
        nuevaLista[index] = repuesto.copy(marcado = !repuesto.marcado)
        _uiState.value = state.copy(
            listaRepuestosSeleccionados = nuevaLista
        )
        calcularPresupuesto()
    }

    private fun calcularPresupuesto() {
        val state = uiState.value
        val costoManoObra = (state.numeroTecnicos.toIntOrNull() ?: 0) * TARIFA_POR_TECNICO
        val costoRepuestos = state.listaRepuestosSeleccionados
            .filter { it.marcado }
            .sumOf { it.subtotal }
        val presupuestoEstimado = costoManoObra + costoRepuestos
        val porcentajeDescuento = state.porcentajeDescuento.toDoubleOrNull() ?: 0.0
        val descuentoEnSoles = (presupuestoEstimado * porcentajeDescuento) / 100
        val presupuestoFinal = presupuestoEstimado - descuentoEnSoles

        _uiState.value = state.copy(
            presupuestoEstimado = presupuestoEstimado,
            descuentoEnSoles = descuentoEnSoles,
            presupuestoFinal = presupuestoFinal
        )
    }

    fun onSearchPresupuesto() {
        _uiState.value = _uiState.value.copy(
            mostrarResultadosBusquedaRepuestos = false
        )
    }

    fun rechazarPresupuesto() {
        viewModelScope.launch {
            try {
                val idConsulta = _uiState.value.id_consulta ?: 0
                consultaRepository.rechazarConsulta(idConsulta)
            } catch (e: Exception) {
                Log.e("RegistrarPresupuestoViewModel", "Error al rechazar presupuesto", e)
            } catch (e: HttpException) {
                Log.e("RegistrarPresupuestoViewModel", "Error al rechazar presupuesto", e)
            }
        }
        guardarListaProblemas()
        registrarPresupuesto()

    }

    fun aceptarPresupuesto() {
        viewModelScope.launch {
            try {
                val idConsulta = _uiState.value.id_consulta ?: 0
                consultaRepository.aceptarConsulta(idConsulta)
            } catch (e: Exception) {
                Log.e("RegistrarPresupuestoViewModel", "Error al aceptar presupuesto", e)
            } catch (e: HttpException) {
                Log.e("RegistrarPresupuestoViewModel", "Error al aceptar presupuesto", e)
            }
        }
        guardarListaProblemas()
        registrarPresupuesto()
    }

    private fun guardarListaProblemas() {
        viewModelScope.launch {
            try {
                val problemas = _uiState.value.listaProblemasSeleccionados
                listaProblemasRepository.insertarListaDeProblemas(ListaProblemas(
                    id_consulta = _uiState.value.id_consulta,
                    problemas = _uiState.value.listaProblemasSeleccionados.map { it.problema }
                ))
            } catch (e: Exception) {
                Log.e("RegistrarPresupuestoViewModel", "Error al guardar problemas", e)
            } catch (e: HttpException) {
                Log.e("RegistrarPresupuestoViewModel", "Error al guardar problemas", e)
            }
        }
    }

    private fun guardarListaRepuestos() {
        viewModelScope.launch {
            try {
                val repuestos = _uiState.value.listaRepuestosSeleccionados.map {
                    Repuesto(
                        id_repuesto = it.repuesto.id_repuesto,
                        cantidad = it.cantidad
                    )
                }
                val presupuestoRepuesto = PresupuestoRepuesto(
                    id_presupuesto = _uiState.value.id_presupuesto ?: 0,
                    repuestos = repuestos
                )
                Log.d("Presupuesto repuestos", "Repuestos: ${presupuestoRepuesto}")
                problemaRepository.insertarPresupuestoRepuesto(presupuestoRepuesto)
            } catch (e: Exception) {
                Log.e("RegistrarPresupuestoViewModel", "Error al guardar repuestos", e)
            } catch (e: HttpException) {
                Log.e("RegistrarPresupuestoViewModel", "Error al guardar repuestos", e)
            }
        }
    }

    fun resetPresupuesto() {
        _uiState.value = _uiState.value.copy(
            idConsulta = null,
            presupuesto = null,
            idPresupuesto = null,
            presupuestoRepuesto = null,
            fechaIngresoFicha = "",
            fechaRecogidaAprox = "",
            idOst = null,
            fichaIngreso = null,
            registroCancelacionActivo1 = false,
            registroCancelacionActivo2 = false,
            registroCancelacionActivo3 = false,
        )
        resetPresupuestoFlags()
    }

    private fun registrarPresupuesto() {
        val state = uiState.value
        val presupuesto = PresupuestoRegistro(
            id_consulta = state.id_consulta ?: 0,
            tarifa_mano_obra = (state.numeroTecnicos.toIntOrNull() ?: 0) * TARIFA_POR_TECNICO,
            tarifa_repuestos = state.listaRepuestosSeleccionados
                .filter { it.marcado }
                .sumOf { it.subtotal },
            descuento_negociado = state.porcentajeDescuento.toDoubleOrNull() ?: 0.0
        )

        viewModelScope.launch {
            try {
                val respuesta = presupuestoRepository.insertPresupuesto(presupuesto)
                _uiState.value = state.copy(
                    id_presupuesto = respuesta.id,
                    flag_ok_registro_presupuesto = true
                )
                guardarListaRepuestos()
            } catch (e: Exception) {
                Log.e("RegistrarPresupuestoViewModel", "Error al registrar presupuesto", e)
                _uiState.value = state.copy(
                    flag_error_registro_presupuesto = true
                )
            } catch (e: HttpException) {
                Log.e("RegistrarPresupuestoViewModel", "Error al registrar presupuesto", e)
                _uiState.value = state.copy(
                    flag_error_registro_presupuesto = true
                )
            }
        }
    }

    fun resetPresupuestoFlags() {
        _uiState.value = _uiState.value.copy(
            flag_ok_registro_ost = false,
            flag_error_registro_ost = false
        )
    }

    @SuppressLint("NewApi")
    fun registrarFechaActual() {
        val fechaActual = LocalDateTime.now()
        val formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val fechaFormateada = fechaActual.format(formatoFecha)
        val formatoFechaFicha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val fechaFormateadaFicha = fechaActual.format(formatoFechaFicha)

        _uiState.value = _uiState.value.copy(
            fechaAproxIngreso = fechaFormateada,
            fechaIngresoFicha = fechaFormateadaFicha,
            registroCancelacionActivo1 = true,
            registroCancelacionActivo2 = true,
            registroCancelacionActivo3 = true
        )
    }

    fun activarRegistroManual() {
        _uiState.value = _uiState.value.copy(
            desplegablesHabilitados = true
        )
    }

    fun guardarOst() {
        viewModelScope.launch {
            try {
                ostRepository.insertOst(
                    OstRegistro(
                        id_consulta = _uiState.value.id_consulta ?: 0,
                        fecha_aprox_ingreso = _uiState.value.fechaAproxIngreso
                    )
                )
                _uiState.value = _uiState.value.copy(
                    flag_ok_registro_ost = true
                )
            } catch (e: Exception) {
                Log.e("RegistrarPresupuestoViewModel", "Error al guardar OST", e)
                _uiState.value = _uiState.value.copy(
                    flag_error_registro_ost = true
                )
            } catch (e: HttpException) {
                Log.e("RegistrarPresupuestoViewModel", "Error al guardar OST", e)
                _uiState.value = _uiState.value.copy(
                    flag_error_registro_ost = true
                )
            }
        }
    }

    fun seleccionarDia(dia: Int) {
        _uiState.value = _uiState.value.copy(
            diaSeleccionado = dia,
            expandedDia = false,
            registroCancelacionActivo1 = true
        )
    }

    fun seleccionarMes(mes: Int) {
        _uiState.value = _uiState.value.copy(
            mesSeleccionado = mes,
            expandedMes = false,
            registroCancelacionActivo2 = true
        )
    }

    fun seleccionarAnio(anio: Int) {
        _uiState.value = _uiState.value.copy(
            anioSeleccionado = anio,
            expandedAnio = false,
            registroCancelacionActivo3 = true,
            fechaAproxIngreso = String.format(
                "%04d/%02d/%02d",
                anio,
                uiState.value.mesSeleccionado,
                uiState.value.diaSeleccionado
            )
        )
    }

    fun desactivarDesplegable() {
        _uiState.value = _uiState.value.copy(
            expandedDia = false,
            expandedMes = false,
            expandedAnio = false
        )
    }

    fun activarDesplegableDia() {
        _uiState.value = _uiState.value.copy(
            expandedDia = true
        )
    }

    fun activarDesplegableMes() {
        _uiState.value = _uiState.value.copy(
            expandedMes = true
        )
    }

    fun activarDesplegableAnio() {
        _uiState.value = _uiState.value.copy(
            expandedAnio = true
        )
    }

    fun resetRegistroOst() {
        _uiState.value = _uiState.value.copy(
            flag_ok_registro_ost = false,
            flag_error_registro_ost = false
        )
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                            as SiamoApplication)
                val problemaRepository = application.container.problemaRepository
                val solucionRepository = application.container.solucionRepository
                val presupuestoRepository = application.container.presupuestoRepository
                val consultaRepository = application.container.consultaRepository
                val listaProblemasRepository = application.container.listaProblemasRepository
                val ostRepository = application.container.resumenOstRepository
                RegistrarPresupuestoViewModel(
                    problemaRepository = problemaRepository,
                    solucionRepository = solucionRepository,
                    presupuestoRepository = presupuestoRepository,
                    consultaRepository = consultaRepository,
                    listaProblemasRepository = listaProblemasRepository,
                    ostRepository = ostRepository
                )
            }
        }
    }
}