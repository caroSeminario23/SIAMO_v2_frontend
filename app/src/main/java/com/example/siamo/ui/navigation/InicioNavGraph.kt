package com.example.siamo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.siamo.model.Empleado
import com.example.siamo.ui.inicio.InicioViewModel
import com.example.siamo.ui.inicio.Login
import com.example.siamo.ui.inicio.LoginDestination
import com.example.siamo.ui.inicio.SiamoWelcomeDestination
import com.example.siamo.ui.inicio.SiamoWelcomeScreen

@Composable
fun InicioNavHost(
    navController: NavHostController,
    onAccept: () -> Unit = {},
    id_tecnico: MutableState<Int>,
    modifier: Modifier = Modifier
) {
    val inicioViewModel : InicioViewModel = viewModel(factory = InicioViewModel.Factory)
    val inicioUiState by inicioViewModel.uiState.collectAsState()
    NavHost(
        navController = navController,
        startDestination = SiamoWelcomeDestination.route,
        modifier = modifier
    ) {
        composable(SiamoWelcomeDestination.route) {
            SiamoWelcomeScreen(
                onStart = { navController.navigate(LoginDestination.route) }
            )
        }

        composable(LoginDestination.route) {
            Login(
                inicioUiState = inicioUiState,
                onLogin = { codEmpleado: Int, contrasenia: String -> inicioViewModel.loginEmpleado(codEmpleado, contrasenia) },
                onAccept = {
                    id_tecnico.value = inicioUiState.empleado?.id_tecnico ?: 0
                    inicioViewModel.resetFlags()
                    onAccept()
                },
                onCancel = { navController.popBackStack()
                           inicioViewModel.resetFlags() },
                onRetry = { inicioViewModel.resetFlags() }
            )
        }
    }
}