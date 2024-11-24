package com.example.siamo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
import com.example.siamo.ui.tecnico.registro_ost.InspeccionProblemasViewModelFactory
import com.example.siamo.ui.tecnico.registro_ost.InspeccionSolucionesViewModelFactory
import com.example.siamo.ui.tecnico.registro_ost.PresupuestoViewModelFactory
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstViewModel
import com.example.siamo.ui.tecnico.registro_ost.ResumenOstViewModelFactory
import com.example.siamo.ui.tecnico.resumen_ost.ResumenOstViewModel

enum class NavRoutes(val route: String) {
    Principal("principal"),
    Login("login"),
    Presupuesto("presupuesto"),
    ResumenOST("resumenOST"),
    HomeTecnico("homeTecnico"),
    HomeRecepcionista("homeRecepcionista"),
    InspeccionProblemas("inspeccionProblemas"),
    InspeccionSolucinoes("inspeccionSoluciones")
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
    val presupuestoViewModel: PresupuestoViewModel = viewModel(
        factory = PresupuestoViewModelFactory(registroOstViewModel)
    )
    val resumenOstViewModel: ResumenOstViewModel = viewModel(
        factory = ResumenOstViewModelFactory(registroOstViewModel)
    )

    NavHost(navController = navController, startDestination = NavRoutes.InspeccionProblemas.route, modifier = modifier) {
        composable(NavRoutes.Principal.route) {
            SiamoApp(navController)
        }
        composable(NavRoutes.Login.route) {
            Login(navController)
        }
        composable(NavRoutes.Presupuesto.route) {
            Presupuesto(
                viewModel = presupuestoViewModel,
                navController = navController)
        }
        composable(NavRoutes.ResumenOST.route) {
            ResumenOST(
                viewModel = resumenOstViewModel,
                navController = navController)
        }
        composable(NavRoutes.HomeTecnico.route) {
            HomeTecnico(navController)
        }
        composable(NavRoutes.HomeRecepcionista.route) {
            HomeRecepcionista(navController)
        }
        composable(NavRoutes.InspeccionProblemas.route) {
            InspeccionProblemas(
                viewModel = inspeccionProblemasViewModel,
                navController = navController
            )
        }
        composable(NavRoutes.InspeccionSolucinoes.route) {
            InspeccionSoluciones(
                viewModel = inspeccionSolucionesViewModel,
                navController = navController
            )
        }
    }
}