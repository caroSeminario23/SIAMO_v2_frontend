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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.siamo.R
import com.example.siamo.data.problema.ProblemaLectura
import com.example.siamo.navigation.NavRoutes
import com.example.siamo.ui.tecnico.registrar_presupuesto.RegistrarPresupuestoUiState
import com.example.siamo.ui.tecnico.registrar_presupuesto.RegistrarPresupuestoViewModel
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstViewModel
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.ListItemCustome
import com.example.siamo.ui.utils.NavigationBarTecnico
import com.example.siamo.ui.utils.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InspeccionProblemas(
    modifier: Modifier = Modifier,
    newUiState: RegistrarPresupuestoUiState = RegistrarPresupuestoUiState(),
    onQueryChange: (String) -> Unit = {},
    onSearch: () -> Unit = {},
    onProblemaSelected: (ProblemaLectura) -> Unit = {},
    onValueChange: (String) -> Unit = {},
    onRegistrarProblema: () -> Unit = {},
    onRegistrarNuevoProblema: () -> Unit = {},
    onCheckedChange: (Int) -> Unit = {},
    onNext: () -> Unit = {},
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
                        query = newUiState.problemaBuscado,
                        onQueryChange = { onQueryChange(it) },
                        onSearch = { onSearch },
                        active = newUiState.searchBarActivaProblema,
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
                        expanded = newUiState.mostrarResultadosBusquedaProblemas,
                        onDismissRequest = {
                            onSearch()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        newUiState.resultadosBusquedaProblemas.forEach { problema ->
                            DropdownMenuItem(
                                text = { Text(text = problema.descripcion?: "NA") },
                                onClick = {
                                    onProblemaSelected(problema)
                                }
                            )
                        }
                    }
                }
            }

            item {
                OutlinedTextField(
                    value = newUiState.descripcionProblema,
                    onValueChange = {
                        onValueChange(it)
                    },
                    label = {
                        Text(stringResource(id = R.string.campo_nuevo_problema))
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 4.dp)
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            if (newUiState.activoRegistroParaProblemaNoRegistrado) {
                                onRegistrarNuevoProblema()
                            } else {
                                onRegistrarProblema()
                            }
                                  },
                        enabled = newUiState.problemaSeleccionadoTemp != null || newUiState.activoRegistroParaProblemaNoRegistrado,
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

            items(newUiState.listaProblemasSeleccionados.size) { index ->
                val problemaSeleccionado = newUiState.listaProblemasSeleccionados[index]
                ListItemCustome(
                    textoPrincipal = "${problemaSeleccionado.problema.descripcion}",
                    textoSecundario = " ",
                    seleccionado = problemaSeleccionado.seleccionado,
                    onCheckedChange = { onCheckedChange(index) }
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
                        onClick = {
                           onNext()
                        },
                        enabled = newUiState.listaProblemasSeleccionados.isNotEmpty(),
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
//    val viewModel = RegistrarPresupuestoViewModel()
    SIAMOTheme (darkTheme = false) {
        InspeccionProblemas()
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun IdentificacionProblemasPreview() {
    val navController = rememberNavController()
    val registroOstViewModel = RegistroOstViewModel()
//    val viewModel = RegistrarPresupuestoViewModel()
    SIAMOTheme (darkTheme = true) {
        InspeccionProblemas()
    }
}
