package com.example.siamo.ui.tecnico.inspecciones_pendientes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.ui.utils.InspectionCard
import com.example.siamo.ui.utils.NavigationBarTecnico
import com.example.siamo.ui.utils.TopBar
import com.example.siamo.ui.utils.TabBarTecnico
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue


@Composable
fun InspeccionPendiente(
    idTecnico: Int,
    cargarInspecciones: (Int) -> Unit,
    inspeccionViewModel: InspeccionPendienteViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val consultaUiState by inspeccionViewModel.uiState.collectAsState()

    LaunchedEffect(idTecnico) {
        cargarInspecciones(idTecnico)
    }

    Scaffold(
        topBar = { TopBar(tituloPagina = stringResource(R.string.navbar_opcion5), modo = "Retroceder") },
        bottomBar = { NavigationBarTecnico(opcionSeleccionada = 2) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(12.dp))
            TabBarTecnico(
                opcionSeleccionada = 0
            )

            Spacer(modifier = Modifier.padding(12.dp))

            // Manejo de estados
            when {
                consultaUiState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }
                consultaUiState.inspeccionPendientes.isNullOrEmpty() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.no_inspecciones_pendientes),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                else -> {
                    LazyColumn {
                        items(consultaUiState.inspeccionPendientes!!) { consulta ->
                            Box(
                                modifier = Modifier.padding(start = 18.dp, end = 18.dp, bottom = 12.dp)
                            ) {
                                InspectionCard(
                                    cliente = consulta.persona?.nombres ?: "Nombre desconocido",
                                    vehiculo = "${consulta.automovil?.marca ?: "Marca desconocida"} (${consulta.automovil?.modelo ?: "Modelo desconocido"})",
                                    placa = consulta.automovil?.placa ?: "Placa desconocida",
                                    problema = consulta.prob_declarado ?: "Problema no especificado",
                                    consulta = consulta.id_consulta?.toString() ?: "Sin ID",
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


