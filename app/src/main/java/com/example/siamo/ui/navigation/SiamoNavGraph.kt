package com.example.siamo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.siamo.ui.inspeccion.InspeccionInicialViewModel
import com.example.siamo.ui.inspeccion.InspeccionPendienteViewModel
import com.example.siamo.ui.inspeccion.ProblemasViewModel
import com.example.siamo.ui.inspeccion.RegistroSolucionViewModel
import com.example.siamo.ui.tecnico.IdentificacionProblemas
import com.example.siamo.ui.tecnico.InspeccionInicial
import com.example.siamo.ui.tecnico.InspeccionPendiente
import com.example.siamo.ui.tecnico.registroSolucion

enum class NavRoutes(val route: String) {
    InspeccionPendiente("Inspeccion"),
    IdentificacionProblemas("elistar_problemas/{idConsulta}"),
    InspecccionInicial("inspeccion_inicial/{idConsulta}"),
    RegistroSolucion("registroSolucion/{id}")


}
@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {

    val inspeccionViewModel: InspeccionPendienteViewModel = viewModel()
    val registroSolucionviewModel: RegistroSolucionViewModel = viewModel()
    val inspeccionInicialViewModel: InspeccionInicialViewModel = viewModel()
    val problemasViewModel: ProblemasViewModel = viewModel()

    NavHost(navController = navController, startDestination = NavRoutes.InspeccionPendiente.route, modifier = modifier) {
        composable(NavRoutes.InspeccionPendiente.route) {
            InspeccionPendiente(
                idTecnico = 1,
                cargarInspecciones = { tecnicoId -> inspeccionViewModel.consultasPorIdTecnico(tecnicoId) },
                inspeccionViewModel = inspeccionViewModel,
                navController = navController
            )

        }
        composable(NavRoutes.IdentificacionProblemas.route) { backStackEntry ->
            val idConsulta = backStackEntry.arguments?.getString("idConsulta")?.toInt() ?: 0
            IdentificacionProblemas(idConsulta = idConsulta, navController = navController,problemasViewModel=problemasViewModel) }

        composable(NavRoutes.InspecccionInicial.route) { backStackEntry ->
            val idConsulta = backStackEntry.arguments?.getString("idConsulta")?.toInt() ?: 0
            InspeccionInicial(idConsulta = idConsulta, navController = navController, inspeccionInicialViewModel = inspeccionInicialViewModel)
        }
        composable(NavRoutes.RegistroSolucion.route){ backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
            registroSolucion(navController = navController, problemaID = id,registroSolucionviewModel=registroSolucionviewModel) }

        }
    }
