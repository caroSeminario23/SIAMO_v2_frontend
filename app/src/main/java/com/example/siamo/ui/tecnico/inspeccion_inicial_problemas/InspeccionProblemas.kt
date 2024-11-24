package com.example.siamo.ui.tecnico.inspeccion_inicial_problemas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.siamo.R
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstViewModel
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.ListItemCustome
import com.example.siamo.ui.utils.NavigationBarTecnico
import com.example.siamo.ui.utils.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InspeccionProblemas(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: InspeccionProblemasViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                tituloPagina = stringResource(R.string.topbar_opcion10),
                modo = "Retroceder"
            )
        },
        bottomBar = { NavigationBarTecnico(opcionSeleccionada = 2) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = stringResource(R.string.ispeccion_identificar_problema),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 40.dp, bottom = 15.dp)
                        .fillMaxWidth()
                )
            }

            item {
                Box {
                    androidx.compose.material3.SearchBar(
                        query = uiState.problemaBuscado,
                        onQueryChange = { viewModel.actualizarQuery(it) },
                        onSearch = { viewModel.onSearch() },
                        active = uiState.searchBarActivaProblema,
                        onActiveChange = { },
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.busqueda_indicacion_repuesto),
                                modifier = Modifier.wrapContentWidth()
                            )
                        },
                        colors = SearchBarDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                        ),
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 0.dp),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        content = {}
                    )

                    DropdownMenu(
                        expanded = uiState.mostrarResultadosBusquedaProblemas,
                        onDismissRequest = {
                            viewModel.onSearch()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        uiState.resultadosBusquedaProblemas.forEach { problema ->
                            DropdownMenuItem(
                                text = { Text(text = problema.descripcion) },
                                onClick = {
                                    viewModel.seleccionarProblema(problema)
                                }
                            )
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { viewModel.registrarProblema() },
                        enabled = uiState.problemaSeleccionadoTemp != null,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = stringResource(id = R.string.registrar_boton))
                    }
                }
            }

            items(uiState.listaProblemasSeleccionados.size) { index ->
                val problemaSeleccionado = uiState.listaProblemasSeleccionados[index]
                ListItemCustome(
                    textoPrincipal = "${problemaSeleccionado.problema.descripcion}",
                    textoSecundario = " ",
                    seleccionado = problemaSeleccionado.seleccionado,
                    onCheckedChange = { viewModel.toogleMarcadoProblema(index) }
                )
            }

            item {
                Spacer(modifier = Modifier.padding(top = 4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { },
                        enabled = uiState.listaProblemasSeleccionados.isNotEmpty(),
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
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun IdentificacionProblemasLightPreview() {
    val navController = rememberNavController()
    val registroOstViewModel = RegistroOstViewModel()
    val viewModel = InspeccionProblemasViewModel(registroOstViewModel)
    SIAMOTheme (darkTheme = false) {
        InspeccionProblemas(
            viewModel = viewModel,
            navController = navController
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun IdentificacionProblemasPreview() {
    val navController = rememberNavController()
    val registroOstViewModel = RegistroOstViewModel()
    val viewModel = InspeccionProblemasViewModel(registroOstViewModel)
    SIAMOTheme (darkTheme = true) {
        InspeccionProblemas(
            viewModel = viewModel,
            navController = navController
        )
    }
}
