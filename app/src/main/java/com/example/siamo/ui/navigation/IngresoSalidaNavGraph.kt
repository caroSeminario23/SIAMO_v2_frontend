package com.example.siamo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.siamo.ui.tecnico.IngresoAuto
import com.example.siamo.ui.tecnico.IngresoSalidaViewModel
import com.example.siamo.ui.tecnico.RegistroEstadoVehiculo

@Composable
fun IngresoSalidaNavHost(
    navController: NavHostController ,
    modifier: Modifier = Modifier
) {
    val ingresoViewModel: IngresoSalidaViewModel = viewModel()
    val ingresoUiState by ingresoViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = "ingresoAuto",
        modifier = modifier
    ) {
        composable("ingresoAuto") {
            IngresoAuto(
                ingresoAutoUiState = ingresoUiState,
                onCarga = { ingresoViewModel.cargarIngresoSalida() },
                onNextIngreso= {idOst ->
                    navController.navigate("registroEstadoVehiculo/$idOst")}
            )
        }
        composable("registroEstadoVehiculo") {
            RegistroEstadoVehiculo(
                ingresoAutoUiState = ingresoUiState,
                onAccept = { navController.navigate("ingresoAuto") },
                onCancel = ingresoViewModel::resetFlags,
                onRetry = {ingresoViewModel.resetFlags()},


            )
        }



    }
}
