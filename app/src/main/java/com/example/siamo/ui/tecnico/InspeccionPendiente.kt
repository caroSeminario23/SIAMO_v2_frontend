package com.example.siamo.ui.tecnico

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.InspectionCard
import com.example.siamo.ui.utils.NavigationBarTecnico
import com.example.siamo.ui.utils.TopBar
import com.example.siamo.ui.utils.TabBarTecnico
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
@Composable
fun InspeccionPendiente(viewModel: InspeccionPendienteViewModel = viewModel()) {

    val inspeccionPendientes by remember { mutableStateOf(viewModel.inspeccionPendientes) }


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
            TabBarTecnico(opcionSeleccionada = 0)

            Spacer(modifier = Modifier.padding(12.dp))

            if (inspeccionPendientes.isEmpty()) {
                // Mostrar mensaje cuando no hay inspecciones
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
            } else {
                // Mostrar lista de inspecciones pendientes
                LazyColumn {
                    items(inspeccionPendientes) { pendientes ->
                        Box(
                            modifier = Modifier.padding(start = 18.dp, end = 18.dp, bottom = 12.dp)
                        ) {
                            InspectionCard(
                                cliente = pendientes.cliente,
                                vehiculo = pendientes.vehiculo,
                                placa = pendientes.nroPlaca,
                                problema = pendientes.problema,
                                consulta = pendientes.idconsulta
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun InspeccionPendienteLightPreview() {
    SIAMOTheme (darkTheme = false) { InspeccionPendiente() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun InspeccionPendientePreview() {
    SIAMOTheme (darkTheme = true) { InspeccionPendiente() }
}