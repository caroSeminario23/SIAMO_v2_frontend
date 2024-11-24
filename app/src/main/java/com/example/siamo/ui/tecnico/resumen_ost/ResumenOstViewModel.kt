package com.example.siamo.ui.tecnico.resumen_ost

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.siamo.data.problema.ProblemaLectura
import com.example.siamo.data.problema.ProblemaSeleccionado
import com.example.siamo.data.solucion.SolucionLectura
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstUiState
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstViewModel
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ResumenOstViewModel (
    private val registroOstViewModel: RegistroOstViewModel
) : ViewModel() {
    val uiState: StateFlow<RegistroOstUiState> = registroOstViewModel.uiState

//    init {
//        cargarProblemasSeleccionados()
//        cargarSolucionesRegistradas()
//    }

    private fun cargarProblemasSeleccionados() {
        val problema1 = ProblemaLectura(1, "Problema 1", "Detalle del problema 1")
        val problema2 = ProblemaLectura(2, "Problema 2", "Detalle del problema 2")
        val problema3 = ProblemaLectura(3, "Problema 3", "Detalle del problema 3")

        val problemas = listOf(
            ProblemaSeleccionado(problema1, true),
            ProblemaSeleccionado(problema2, true),
            ProblemaSeleccionado(problema3, true)
        )

        registroOstViewModel.actualizarUiState(
            uiState.value.copy(listaProblemasSeleccionados = problemas)
        )
    }

    private fun cargarSolucionesRegistradas() {
        val soluciones = listOf(
            SolucionLectura(1, "Solucion 1", 1),
            SolucionLectura(2, "Solucion 2", 2),
            SolucionLectura(3, "Solucion 3", 3)
        )

        registroOstViewModel.actualizarUiState(
            uiState.value.copy(listaSolucionesRegistradas = soluciones)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun registrarFechaActual() {
        val fechaActual = LocalDateTime.now()
        val formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val fechaFormateada = fechaActual.format(formatoFecha)
        val formatoFechaFicha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val fechaFormateadaFicha = fechaActual.format(formatoFechaFicha)

        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                fechaAproxIngreso = fechaFormateada,
                fechaIngresoFicha = fechaFormateadaFicha,
                registroCancelacionActivo1 = true,
                registroCancelacionActivo2 = true,
                registroCancelacionActivo3 = true
            )
        )
    }

    fun activarRegistroManual() {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                desplegablesHabilitados = true
            )
        )
    }

    fun guardarOst() {
        // enviar datos a la base de datos
        val codRespuesta = 200
        if (codRespuesta == 200) {
            registroOstViewModel.actualizarUiState(
                uiState.value.copy(
                    errorRegistroPresupuesto = 2 // 2 es el código de éxito
                )
            )
        } else {
            registroOstViewModel.actualizarUiState(
                uiState.value.copy(
                    errorRegistroPresupuesto = 1 // 1 es el código de error
                )
            )
        }
    }

    fun cerrarAlertar() {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                errorRegistroPresupuesto = 0
            )
        )
    }

    fun seleccionarDia(dia: Int) {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                diaSeleccionado = dia,
                expandedDia = false,
                registroCancelacionActivo1 = true
            )
        )
    }

    fun seleccionarMes(mes: Int) {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                mesSeleccionado = mes,
                expandedMes = false,
                registroCancelacionActivo2 = true
            )
        )
    }

    fun seleccionarAnio(anio: Int) {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                anioSeleccionado = anio,
                expandedAnio = false,
                registroCancelacionActivo3 = true,
                fechaAproxIngreso = "${uiState.value.anioSeleccionado}-${uiState.value.mesSeleccionado}-${uiState.value.diaSeleccionado}"
            )
        )
    }

    fun desactivarDesplegable() {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                expandedDia = false,
                expandedMes = false,
                expandedAnio = false
            )
        )
    }

    fun activarDesplegableDia() {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                expandedDia = true
            )
        )
    }

    fun activarDesplegableMes() {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                expandedMes = true
            )
        )
    }

    fun activarDesplegableAnio() {
        registroOstViewModel.actualizarUiState(
            uiState.value.copy(
                expandedAnio = true
            )
        )
    }
}