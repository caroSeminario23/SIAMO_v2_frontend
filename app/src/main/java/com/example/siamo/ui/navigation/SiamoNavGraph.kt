package com.example.siamo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.siamo.model.Automovil
import com.example.siamo.model.Cliente
import com.example.siamo.model.Persona
import com.example.siamo.model.Tecnico
import com.example.siamo.ui.consulta.ConsultaViewModel
import com.example.siamo.ui.recepcionista.ActualizacionCliente
import com.example.siamo.ui.recepcionista.ActulizacionClienteDestination
import com.example.siamo.ui.recepcionista.AsignacionTecnicoDestination
import com.example.siamo.ui.recepcionista.AsignacionTenico
import com.example.siamo.ui.recepcionista.BusquedaCliente
import com.example.siamo.ui.recepcionista.BusquedaClienteDestination
import com.example.siamo.ui.recepcionista.BusquedaVehiculo
import com.example.siamo.ui.recepcionista.BusquedaVehiculoDestination
import com.example.siamo.ui.recepcionista.HomeRecepcionistaDestination
import com.example.siamo.ui.recepcionista.RegistroCliente
import com.example.siamo.ui.recepcionista.RegistroClienteDestination
import com.example.siamo.ui.recepcionista.RegistroConsulta
import com.example.siamo.ui.recepcionista.RegistroConsultaDestination
import com.example.siamo.ui.recepcionista.RegistroVehiculo
import com.example.siamo.ui.recepcionista.RegistroVehiculoDestination
import com.example.siamo.ui.recepcionista.ResumenConsulta
import com.example.siamo.ui.recepcionista.ResumenConsultaDestination

@Composable
fun SiamoNavHost(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val consultaViewModel : ConsultaViewModel = viewModel(factory = ConsultaViewModel.Factory)
    val consultaUiState by consultaViewModel.uiState.collectAsState()
    NavHost(
        navController = navController,
        startDestination = BusquedaClienteDestination.route,
        modifier = modifier
    ) {
        composable(BusquedaClienteDestination.route) {
            BusquedaCliente(
                consultaUiState = consultaUiState,
                getCliente = {dni -> consultaViewModel.buscarClientePorDni(dni)},
                onAccept = {navController.navigate(ActulizacionClienteDestination.route)},
                onCancel = consultaViewModel::resetFlags,
                onRegistrar = {navController.navigate(RegistroClienteDestination.route)}
            )
        }

        composable(ActulizacionClienteDestination.route) {
            ActualizacionCliente(
                consultaUiState = consultaUiState,
                onAccept = {
                    navController.navigate(BusquedaVehiculoDestination.route)
                    consultaViewModel.resetFlags()
                           },
                onCancel = {
                    navController.popBackStack()
                    consultaViewModel.resetUiState()
                },
                onRetry = {consultaViewModel.resetFlags()},
                onUpdate = {
                    cliente: Cliente -> consultaViewModel.actualizarCliente(cliente)
                    consultaViewModel.resetFlags()
                           },
                onCorrect = {
                    consultaViewModel.aceptarCliente()
                    consultaViewModel.resetFlags()
                    navController.navigate(BusquedaVehiculoDestination.route)
                }
            )
        }

        composable(RegistroClienteDestination.route) {
            RegistroCliente(
                consultaUiState = consultaUiState,
                onAccept = {
                    navController.navigate(BusquedaVehiculoDestination.route)
                    consultaViewModel.resetFlags()
                           },
                onCancel = {
                    navController.popBackStack()
                    consultaViewModel.resetFlags()
                },
                onRetry = {consultaViewModel.resetFlags()},
                onRegister = {
                    persona: Persona -> consultaViewModel.registrarCliente(persona)
                    consultaViewModel.resetFlags()
                             },
            )
        }

        composable(BusquedaVehiculoDestination.route) {
            BusquedaVehiculo(
                consultaUiState = consultaUiState,
                onAccept = {
                    consultaViewModel.resetFlags()
                    navController.navigate(RegistroConsultaDestination.route)
                           },
                onCancel = {
                    consultaViewModel.resetFlags()
                },
                onRegister = {
                    navController.navigate(RegistroVehiculoDestination.route)
                    consultaViewModel.resetFlags()
                             },
                onSearch = {
                        placa: String -> consultaViewModel.buscarAutomovilPorPlaca(placa)
                        consultaViewModel.resetFlags()
                }
            )
        }

        composable(RegistroVehiculoDestination.route) {
            RegistroVehiculo(
                consultaUiState = consultaUiState,
                onAccept = {
                    navController.navigate(RegistroConsultaDestination.route)
                    consultaViewModel.resetFlags()
                           },
                onCancel = {
                    navController.popBackStack()
                    consultaViewModel.resetFlags()
                },
                onRetry = {consultaViewModel.resetFlags()},
                onRegister = {
                    automovil: Automovil -> consultaViewModel.registrarAutomovil(automovil)
                    consultaViewModel.resetFlags()
                             },
            )
        }

        composable(RegistroConsultaDestination.route) {
            RegistroConsulta(
                consultaUiState = consultaUiState,
                onNext = {
                    problema -> consultaViewModel.registrarProblema(problema)
                    consultaViewModel.obtenerTecnicos()
                    consultaViewModel.resetFlags()
                    navController.navigate(AsignacionTecnicoDestination.route)
                           },
                onPrevius = {
                    consultaViewModel.resetFlags()
                    navController.popBackStack()
                            },
            )
        }

        composable(AsignacionTecnicoDestination.route) {
            AsignacionTenico(
                consultaUiState = consultaUiState,
                onNext = {
                        tecnico: Tecnico -> consultaViewModel.asignarTecnico(tecnico)
                    consultaViewModel.resetFlags()
                    navController.navigate(ResumenConsultaDestination.route)
                },
                onPrevius = {
                    consultaViewModel.resetFlags()
                    navController.popBackStack()
                },
            )
        }

        composable(ResumenConsultaDestination.route) {
            ResumenConsulta(
                consultaUiState = consultaUiState,
                onRegister = {
                    consultaViewModel.registrarConsulta()
                },
                onAccept = {
                    cancelOrderAndNavigateToStart(consultaViewModel, navController)
                },
                onRetry = {
                    consultaViewModel.resetFlags()
                },
                onCancel = {
                    consultaViewModel.resetFlags()
                    navController.popBackStack()
                },
            )
        }

    }
}

private fun cancelOrderAndNavigateToStart(
    viewModel: ConsultaViewModel,
    navController: NavHostController
) {
    viewModel.resetUiState()
    navController.popBackStack(BusquedaClienteDestination.route, inclusive = false)
}