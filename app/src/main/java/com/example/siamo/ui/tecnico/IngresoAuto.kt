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
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.NavigationBarTecnico
import com.example.siamo.ui.utils.TopBar
import com.example.siamo.ui.utils.TabBarTecnico
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import com.example.siamo.ui.utils.IngresoSalidaCard

@Composable
fun IngresoAuto(
    ingresoAutoUiState:IngresoAutoUiState,
    onCarga: () -> Unit = {},
    onNextIngreso: (Int) -> Unit = {},
    modifier: Modifier = Modifier,

) {
    val ingresoList = ingresoAutoUiState.ingresoList
    val salidaList =ingresoAutoUiState.ingresoList


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
            TabBarTecnico(opcionSeleccionada = 1, { onCarga() })

            Spacer(modifier = Modifier.padding(12.dp))


            if (ingresoList.isEmpty() && salidaList.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.no_ingreso_salida_pendiente),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {

                LazyColumn {
                    items(ingresoList) { vehiculo ->
                        Box(
                            modifier = Modifier.padding(start = 18.dp, end = 18.dp, bottom = 12.dp)
                        ) {
                            IngresoSalidaCard(
                                cliente = vehiculo.nombre_cliente,
                                placa = vehiculo.placa_vehiculo,
                                ost = vehiculo.id_ost.toString(),
                                cabezaCard = stringResource(R.string.ingreso_cabeza_card),
                                tituloCard = stringResource(R.string.ingreso_titulo_card),
                                onNext = { idOst ->
                                    onNextIngreso(idOst) },
                            )
                        }
                    }

                    // Luego mostrar la lista de "Salida"
                    items(salidaList) { vehiculo ->
                        Box(
                            modifier = Modifier.padding(start = 18.dp, end = 18.dp, bottom = 12.dp)
                        ) {
                            IngresoSalidaCard(
                                cliente = vehiculo.nombre_cliente,
                                placa = vehiculo.placa_vehiculo,
                                ost = vehiculo.id_ost.toString(),
                                cabezaCard = stringResource(R.string.salida_cabeza_card),
                                tituloCard = stringResource(R.string.salida_titulo_card),
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
fun IngresoAutoLightPreview() {
    SIAMOTheme (darkTheme = false) { IngresoAuto(ingresoAutoUiState=IngresoAutoUiState() ,onCarga={}) }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun IngresoAutoDarkPreview() {
    SIAMOTheme (darkTheme = true) { IngresoAuto(ingresoAutoUiState=IngresoAutoUiState() ,onCarga={}) }
}