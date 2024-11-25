package com.example.siamo.ui.tecnico.panel_control

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.siamo.ui.tecnico.HomeTecnicoViewModel

@Composable
fun HomeTecnicoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val homeTecnicoViewModel: HomeTecnicoViewModel = viewModel(factory = HomeTecnicoViewModel.Factory)
    val homeTecnicoUiState by homeTecnicoViewModel.uiState.collectAsState()

//    NavHost(
//        navController = navController,
//        modifier = modifier
//    ) {
//        composable()
//    }
}