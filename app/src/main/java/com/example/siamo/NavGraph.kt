package com.example.siamo

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
import com.example.siamo.ui.tecnico.resumenost.ResumenOST
import com.example.siamo.ui.tecnico.presupuesto.Presupuesto
import com.example.siamo.ui.tecnico.presupuesto.PresupuestoViewModel
import com.example.siamo.ui.tecnico.resumenost.ResumenOstViewModel

enum class NavRoutes(val route: String) {
    Principal("principal"),
    Login("login"),
    Presupuesto("presupuesto"),
    ResumenOST("resumenOST"),
    HomeTecnico("homeTecnico"),
    HomeRecepcionista("homeRecepcionista"),
}

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    val presupuestoViewModel: PresupuestoViewModel = viewModel()
    val resumenOstViewModel: ResumenOstViewModel = viewModel()

    NavHost(navController = navController, startDestination = NavRoutes.Presupuesto.route, modifier = modifier) {
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
                navController = navController) }
        composable(NavRoutes.HomeTecnico.route) { HomeTecnico(navController) }
        composable(NavRoutes.HomeRecepcionista.route) { HomeRecepcionista(navController) }
    }
}