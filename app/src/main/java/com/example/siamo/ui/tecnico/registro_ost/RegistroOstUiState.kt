package com.example.siamo.ui.tecnico.registro_ost

import com.example.siamo.data.consulta.ConsultaLectura
import com.example.siamo.data.ficha_ingreso.FichaIngresoRegistro
import com.example.siamo.data.ost.OstLectura
import com.example.siamo.data.ost.OstRegistro
import com.example.siamo.data.presupuesto.PresupuestoLectura
import com.example.siamo.data.presupuesto.PresupuestoRegistro
import com.example.siamo.data.presupuesto.PresupuestoRepuesto
import com.example.siamo.data.problema.ProblemaLectura
import com.example.siamo.data.problema.ProblemaRegistro
import com.example.siamo.data.problema.ProblemaSeleccionado
import com.example.siamo.data.solucion.SolucionLectura
import com.example.siamo.data.repuesto.Repuesto
import com.example.siamo.data.repuesto.RepuestoSeleccionado
import com.example.siamo.data.solucion.SolucionRegistro

data class RegistroOstUiState(
    // Busqueda y seleccion de problemas
    val listaProblemasBitacora: List<ProblemaLectura> = emptyList(),
    val problemaBuscado: String = "",
    val mostrarResultadosBusquedaProblemas: Boolean = false,
    val resultadosBusquedaProblemas: List<ProblemaLectura> = emptyList(),
    val listaProblemasSeleccionados: List<ProblemaSeleccionado> = emptyList(),
    val searchBarActivaProblema: Boolean = false,
    val repuestoRepuestoTemp: Repuesto? = null,

    // Mostrar lista de soluciones de los problemas registrados
    val listaSolucionesRegistradas: List<SolucionLectura> = emptyList(),

    // Registro de problema y solución no registrados
    val descripcionProblema: String = "",
    val detalleProblema: String = "",
    val solucionPropuesta: String = "",
    val problemaNuevo: ProblemaRegistro? = null,
    val solucionNueva: SolucionRegistro? = null,

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
    val fechaAproxIngreso: String = "",
    val ingresoFechaActivo: Boolean = false,

    // Registro de Ost
    val ost: OstRegistro? = null, // definir fechaRegistro y estado: 1

    // Registro de ficha de ingreso cuando el vehículo ingresa a taller en ese momento
    val fechaRecogidaAprox: String = "",
    val idOst: OstLectura? = null,
    val fichaIngreso: FichaIngresoRegistro? = null, // definir fechaIngreso como fechaAproxIngreso

    // Activación de botones de registro y cancelación
    val registroCancelacionActivo: Boolean = false,

    // Manejo de diálogos de éxito y error
    val errorRegistroPresupuestoOst: Int = 0, // 0 es el código de no haber intentado registrar
    val errorRegistroSolucion: Int = 0 // 0 es el código de no haber intentado registrar
)
