package com.example.siamo.ui.navigation
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.siamo.ui.tecnico.inspeccion_pendiente.InspeccionPendienteViewModel
import com.example.siamo.ui.tecnico.inspeccion_pendiente.InspeccionPendiente
//import com.example.siamo.ui.tecnico.inspeccion_inicial_problemas.IdentificacionProblemas
import com.example.siamo.ui.tecnico.inspeccion_inicial_soluciones.InspeccionInicial
//import com.example.siamo.ui.tecnico.registro_solucion.RegistroSolucion

enum class NavRoutes(val route: String) {
    InspeccionPendiente("Inspeccion"),
    //IdentificacionProblemas("elistar_problemas/{idConsulta}"),
    InspecccionInicial("inspeccion_inicial/{idConsulta}"),
    //RegistroSolucion("registroSolucion/{id}")


}
@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {

    val inspeccionViewModel: InspeccionPendienteViewModel = viewModel()

    NavHost(navController = navController, startDestination = NavRoutes.InspeccionPendiente.route, modifier = modifier) {
        composable(NavRoutes.InspeccionPendiente.route) {
            InspeccionPendiente(
                idTecnico = 1,
                cargarInspecciones = { tecnicoId -> inspeccionViewModel.consultasPorIdTecnico(tecnicoId) },
                inspeccionViewModel = inspeccionViewModel,
                navController = navController
            )

        }
//        composable(NavRoutes.IdentificacionProblemas.route) { backStackEntry ->
//            val idConsulta = backStackEntry.arguments?.getString("idConsulta")?.toInt() ?: 0
//            IdentificacionProblemas(idConsulta = idConsulta, navController = navController) }

        composable(NavRoutes.InspecccionInicial.route) {
            //InspeccionInicial(navController = navController)
        }
//        composable(NavRoutes.RegistroSolucion.route){ backStackEntry ->
//            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
//            registroSolucion(problemaID= id, navController = navController) }
//
    }
}