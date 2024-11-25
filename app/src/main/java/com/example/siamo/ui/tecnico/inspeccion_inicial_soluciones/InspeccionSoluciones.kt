package com.example.siamo.ui.tecnico.inspeccion_inicial_soluciones

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.ui.utils.NavigationBarTecnico
import com.example.siamo.ui.utils.TopBar
import com.example.siamo.ui.theme.SIAMOTheme
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.siamo.data.problema.ProblemaSeleccionado
import com.example.siamo.navigation.NavRoutes
import com.example.siamo.ui.tecnico.registrar_presupuesto.RegistrarPresupuestoUiState
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstUiState
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstViewModel
import com.example.siamo.ui.utils.ListItemProblema

@Composable
fun InspeccionSoluciones(
    newUiState: RegistrarPresupuestoUiState,
    onAddClick: (ProblemaSeleccionado) -> Unit,
    onClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                tituloPagina = stringResource(R.string.topbar_opcion10),
                modo = "Retroceder"
            )
        },
        bottomBar = { NavigationBarTecnico(opcionSeleccionada = 2) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.ispeccion_problema_solocion),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                val problemasSeleccionados = newUiState.listaProblemasSeleccionados
                    .filter { it.seleccionado }
                items(problemasSeleccionados.size) { index ->
                    val problemaSeleccionado = problemasSeleccionados[index]
                    val solucionCorrespondiente =
                        newUiState.listaSolucionesRegistradas
                            .find {
                                it.id_problema == problemaSeleccionado.problema.id_problema
                            }

                    if (solucionCorrespondiente != null) {
                        ListItemProblema(
                            problema = problemaSeleccionado.problema.descripcion?:"",
                            solucion = solucionCorrespondiente.solucion.descripcion ?:"",
                            onAddClick = {}
                        )
                    } else {
                        ListItemProblema(
                            problema = problemaSeleccionado.problema.descripcion?:"",
                            solucion = stringResource(R.string.label_solucion_no_definida),
                            onAddClick = { onAddClick(problemaSeleccionado) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(top = 4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.End
            ) {
                val problemasSeleccionados = newUiState.listaProblemasSeleccionados
                    .filter { it.seleccionado }
                Log.d("ProblemSelected", problemasSeleccionados.size.toString())
                val solucionesRegistradas = newUiState.listaSolucionesRegistradas
                Log.d("SolucionRegistradas", solucionesRegistradas.size.toString())
                Button(
                    onClick = { onClick() },
                    enabled = problemasSeleccionados.size == solucionesRegistradas.size,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = stringResource(id = R.string.boton_siguiente))
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun InspeccionInicialLightPreview() {
    val navController = rememberNavController()
    val registroOstViewModel = RegistroOstViewModel()
    val viewModel = InspeccionSolucionesViewModel(registroOstViewModel)
    SIAMOTheme(darkTheme = false) {
        InspeccionSoluciones(
            RegistrarPresupuestoUiState(),
            onAddClick = {},
            onClick = {}
        ) }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun InspeccionInicialDarkPreview() {
    val navController = rememberNavController()
    val registroOstViewModel = RegistroOstViewModel()
    val viewModel = InspeccionSolucionesViewModel(registroOstViewModel)
    SIAMOTheme(darkTheme = true) {
        InspeccionSoluciones(
            RegistrarPresupuestoUiState(),
            onAddClick = {},
            onClick = {}
        )
    }
}

