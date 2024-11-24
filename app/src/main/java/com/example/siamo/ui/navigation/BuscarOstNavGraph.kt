package com.example.siamo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.siamo.ui.ost.BuscarOstViewModel
import com.example.siamo.ui.recepcionista.BusquedaOST
import com.example.siamo.ui.recepcionista.BusquedaOSTNavigation
import com.example.siamo.ui.recepcionista.DetalleOST
import com.example.siamo.ui.recepcionista.DetalleOSTDestination

@Composable
fun BuscarOstNavHost(
    navController: NavHostController,
    onHome: () -> Unit,
    onRegister: () -> Unit,
    onSettings: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buscarOstViewModel: BuscarOstViewModel = viewModel(factory = BuscarOstViewModel.Factory)
    val buscarOstUiState by buscarOstViewModel.uiState.collectAsState()
    NavHost(
        navController = navController,
        startDestination = BusquedaOSTNavigation.route,
        modifier = modifier
    ) {
        composable(BusquedaOSTNavigation.route) {
            BusquedaOST(
                buscarOstUiState = buscarOstUiState,
                getOst = { id: Int -> buscarOstViewModel.getOstById(id) },
                onAccept = { navController.navigate(DetalleOSTDestination.route) },
                onCancel = buscarOstViewModel::resetFlags,
                onClose = {
                    onClose()
                    buscarOstViewModel.resetUiState()
                          },
                onConfirm = { buscarOstViewModel.resetFlags() },
                onHomeNav = { onHome()
                    buscarOstViewModel.resetUiState()
                            },
                onRegisterNav = { onRegister()
                    buscarOstViewModel.resetUiState()
                                },
                onSettingsNav = { onSettings()
                    buscarOstViewModel.resetUiState()
                }
            )
        }
        composable(DetalleOSTDestination.route) {
            DetalleOST(
                buscarOstUiState = buscarOstUiState,
                onHomeNav = { onHome()
                    buscarOstViewModel.resetUiState()
                            },
                onRegisterNav = { onRegister()
                    buscarOstViewModel.resetUiState()
                                },
                onSettingsNav = { onSettings()
                    buscarOstViewModel.resetUiState()
                                },
                buttonUp = {
                    navController.popBackStack()
                    buscarOstViewModel.resetUiState()
                }
            )
        }
    }
}