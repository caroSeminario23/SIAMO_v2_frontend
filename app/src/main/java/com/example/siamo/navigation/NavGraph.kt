package com.example.siamo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.siamo.data.problema.ProblemaLectura
import com.example.siamo.data.repuesto.Repuesto
import com.example.siamo.ui.inicio.SiamoApp
import com.example.siamo.ui.inicio.Login
import com.example.siamo.ui.recepcionista.HomeRecepcionista
import com.example.siamo.ui.tecnico.HomeTecnico
import com.example.siamo.ui.tecnico.inspeccion_inicial_problemas.InspeccionProblemas
import com.example.siamo.ui.tecnico.inspeccion_inicial_problemas.InspeccionProblemasViewModel
import com.example.siamo.ui.tecnico.inspeccion_inicial_soluciones.InspeccionSoluciones
import com.example.siamo.ui.tecnico.inspeccion_inicial_soluciones.InspeccionSolucionesViewModel
import com.example.siamo.ui.tecnico.resumen_ost.ResumenOST
import com.example.siamo.ui.tecnico.presupuesto.Presupuesto
import com.example.siamo.ui.tecnico.presupuesto.PresupuestoViewModel
import com.example.siamo.ui.tecnico.registrar_presupuesto.RegistrarPresupuestoUiState
import com.example.siamo.ui.tecnico.registrar_presupuesto.RegistrarPresupuestoViewModel
import com.example.siamo.ui.tecnico.registro_ost.InspeccionProblemasViewModelFactory
import com.example.siamo.ui.tecnico.registro_ost.InspeccionSolucionesViewModelFactory
import com.example.siamo.ui.tecnico.registro_ost.PresupuestoViewModelFactory
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstViewModel
import com.example.siamo.ui.tecnico.registro_ost.RegistroSolucionViewModelFactory
import com.example.siamo.ui.tecnico.registro_ost.ResumenOstViewModelFactory
import com.example.siamo.ui.tecnico.registro_solucion.RegistroSolucion
import com.example.siamo.ui.tecnico.registro_solucion.RegistroSolucionViewModel
import com.example.siamo.ui.tecnico.resumen_ost.ResumenOstViewModel

enum class NavRoutes(val route: String) {
    Principal("principal"),
    Login("login"),
    Presupuesto("presupuesto"),
    ResumenOST("resumenOST"),
    HomeTecnico("homeTecnico"),
    HomeRecepcionista("homeRecepcionista"),
    InspeccionProblemas("inspeccionProblemas"),
    InspeccionSolucinoes("inspeccionSoluciones"),
    RegistroSolucion("registroSolucion")
}

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    val registroOstViewModel: RegistroOstViewModel = viewModel()

    val inspeccionProblemasViewModel: InspeccionProblemasViewModel = viewModel(
        factory = InspeccionProblemasViewModelFactory(registroOstViewModel)
    )
    val inspeccionSolucionesViewModel: InspeccionSolucionesViewModel = viewModel(
        factory = InspeccionSolucionesViewModelFactory(registroOstViewModel)
    )
    val registroSolucionViewModel: RegistroSolucionViewModel = viewModel(
        factory = RegistroSolucionViewModelFactory(registroOstViewModel)
    )
    val presupuestoViewModel: PresupuestoViewModel = viewModel(
        factory = PresupuestoViewModelFactory(registroOstViewModel)
    )
    val resumenOstViewModel: ResumenOstViewModel = viewModel(
        factory = ResumenOstViewModelFactory(registroOstViewModel)
    )

    val newViewModel: RegistrarPresupuestoViewModel = viewModel(
        factory = RegistrarPresupuestoViewModel.Factory
    )

    val newUiState by newViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.InspeccionProblemas.route,
        modifier = modifier
    ) {
        composable(NavRoutes.Principal.route) {
            SiamoApp(navController)
        }
        composable(NavRoutes.Login.route) {
            Login(navController)
        }
        composable(NavRoutes.Presupuesto.route) {
            Presupuesto(
                newUiState = newUiState,
                onNumTecnicosChange = { numTecnicos: String -> newViewModel.actualizarNumeroTecnicos(numTecnicos) },
                onQueryChangeRepuesto = { query: String -> newViewModel.actualizarQueryRepuesto(query) },
                onSearchRepuesto = { newViewModel.onSearch() },
                onSelectRepuesto = { repuesto: Repuesto -> newViewModel.seleccionarRepuesto(repuesto) },
                onActualizarCantidad = { cantidad: String -> newViewModel.actualizarCantidadRepuesto(cantidad) },
                onRegistrarRepuesto = { newViewModel.registrarRepuesto() },
                onMarcarRepuesto = { index: Int -> newViewModel.toggleMarcadoRepuesto(index) },
                onActualizarPorcentajeDescuento = { porcentaje: String -> newViewModel.actualizarPorcentajeDescuento(porcentaje) },
                onDenegar = { newViewModel.rechazarPresupuesto()
                    newViewModel.resetPresupuesto()
                    navController.navigate(NavRoutes.InspeccionProblemas.route) },
                onAceptar = { newViewModel.aceptarPresupuesto()
                    navController.navigate(NavRoutes.ResumenOST.route)
                }
            )
        }
        composable(NavRoutes.ResumenOST.route) {
            ResumenOST(
                newUiState = newUiState,
                onConfirmError = { newViewModel.resetRegistroOst()
                                 newViewModel.resetPresupuesto()
                    navController.navigate(NavRoutes.InspeccionProblemas.route) },
                onDismissError = { newViewModel.resetRegistroOst()
                    newViewModel.resetPresupuesto()
                    navController.navigate(NavRoutes.InspeccionProblemas.route) },
                onAceptar = { navController.navigate(NavRoutes.InspeccionProblemas.route)
                    newViewModel.resetPresupuesto()
                            newViewModel.resetPresupuesto() },
                onIngresoActual = { newViewModel.registrarFechaActual() },
                onIngresoPosterior = { newViewModel.activarRegistroManual() },
                onActivarDesplegableDia = { newViewModel.activarDesplegableDia() },
                onDesactivarDesplegable = { newViewModel.desactivarDesplegable() },
                onSeleccionarDia = { dia: Int -> newViewModel.seleccionarDia(dia) },
                onActivarDesplegableMes = { newViewModel.activarDesplegableMes() },
                onSeleccionarMes = { mes: Int -> newViewModel.seleccionarMes(mes) },
                onActivarDesplegableAnio = { newViewModel.activarDesplegableAnio() },
                onSeleccionarAnio = { anio: Int -> newViewModel.seleccionarAnio(anio) },
                onDenegar = {
                    newViewModel.resetPresupuesto()
                    navController.navigate(NavRoutes.InspeccionProblemas.route)
                },
                onRegistrar = {
                    newViewModel.guardarOst()
                }
            )
        }
        composable(NavRoutes.HomeTecnico.route) {
            HomeTecnico(navController)
        }
        composable(NavRoutes.HomeRecepcionista.route) {
            HomeRecepcionista(navController)
        }
        composable(NavRoutes.InspeccionProblemas.route) {
            InspeccionProblemas(
                newUiState = newUiState,
                onQueryChange = { query: String -> newViewModel.actualizarQuery(query) },
                onSearch = { newViewModel.onSearch() },
                onProblemaSelected = { problema: ProblemaLectura ->
                    newViewModel.seleccionarProblema(
                        problema
                    )
                },
                onValueChange = { descripcion: String ->
                    newViewModel.actualizarDescripcionProblema(
                        descripcion
                    )
                },
                onRegistrarProblema = { newViewModel.registrarProblema() },
                onRegistrarNuevoProblema = { newViewModel.registrarNuevoProblema() },
                onCheckedChange = { index: Int -> newViewModel.toogleMarcadoProblema(index) },
                onNext = {
                    newViewModel.cargarSoluciones()
                    navController.navigate(NavRoutes.InspeccionSolucinoes.route)
                }
            )
        }
        composable(NavRoutes.InspeccionSolucinoes.route) {
            InspeccionSoluciones(
                newUiState = newUiState,
                onAddClick = { problema ->
                    newViewModel.guardarProblema(problema)
                    navController.navigate(NavRoutes.RegistroSolucion.route)
                },
                onClick = {
                    newViewModel.cargarRepuestos()
                    navController.navigate(NavRoutes.Presupuesto.route)
                }

            )
        }
        composable(NavRoutes.RegistroSolucion.route) {
            RegistroSolucion(
                newUiState = newUiState,
                onValueChangeDetalle = { detalle: String ->
                    newViewModel.actualizarDetalleProblema(
                        detalle
                    )
                },
                onValueChangeSolucion = { solucioin: String ->
                    newViewModel.actualizarSolucionPropuesta(
                        solucioin
                    )
                },
                onRegistrarSolucion = {
                    newViewModel.registrarSolucion()
                },
                onAccept = {
                    newViewModel.onResetRegitserProblema()
                    navController.popBackStack()
                },
                onConfirm = {
                    newViewModel.onResetRegitserProblema()
                },
                onDismiss = {
                    navController.popBackStack()
                    newViewModel.onResetRegitserProblema()
                }
            )
        }
    }
}