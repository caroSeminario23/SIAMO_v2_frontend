package com.example.siamo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.siamo.ui.inicio.SiamoApp

enum class NavRoutes(val route: String) {
    Principal("principal"),
    Login("login"),
    Presupuesto("presupuesto"),
    ResumenOST("resumenOST")
}

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "principal", modifier = modifier) {
        composable(NavRoutes.Principal.route) { SiamoApp(navController) }
        //composable(NavRoutes.Login.route) { Login(navController) }
        //composable("presupuesto") { Presupuesto(navController)
        //composable("resumenOST") { ResumenOST(navController) }
    }
}