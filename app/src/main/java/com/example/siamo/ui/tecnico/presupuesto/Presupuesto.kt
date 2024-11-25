package com.example.siamo.ui.tecnico.presupuesto

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
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.siamo.navigation.NavRoutes
import com.example.siamo.R
import com.example.siamo.data.repuesto.Repuesto
import com.example.siamo.ui.tecnico.registrar_presupuesto.RegistrarPresupuestoUiState
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstViewModel
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.AlertDialogError
import com.example.siamo.ui.utils.AlertDialogOK
import com.example.siamo.ui.utils.DividerSection
import com.example.siamo.ui.utils.ListItemCustome
import com.example.siamo.ui.utils.NavigationBarTecnico
import com.example.siamo.ui.utils.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Presupuesto(
    newUiState: RegistrarPresupuestoUiState,
    onNumTecnicosChange: (String) -> Unit = {},
    onQueryChangeRepuesto: (String) -> Unit = {},
    onSearchRepuesto: () -> Unit = {},
    onSelectRepuesto: (Repuesto) -> Unit = {},
    onActualizarCantidad: (String) -> Unit = {},
    onRegistrarRepuesto: () -> Unit = {},
    onMarcarRepuesto: (Int) -> Unit = {},
    onActualizarPorcentajeDescuento: (String) -> Unit = {},
    onDenegar: () -> Unit = {},
    onAceptar: () -> Unit = {},
    modifier: Modifier = Modifier,
) {

    Scaffold (
        topBar = { TopBar(tituloPagina = stringResource(id = R.string.topbar_presupuesto), modo = "Retroceder" ) },
        bottomBar = { NavigationBarTecnico(opcionSeleccionada = 2) }
    ) { paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.indicacion_presupuesto),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 30.dp)
                )
            }

            item {
                DividerSection(
                    nombreSeccion = stringResource(id = R.string.seccion_mano_obra)
                )
            }

            item {
                OutlinedTextField(
                    value = newUiState.numeroTecnicos,
                    onValueChange = {
                        onNumTecnicosChange(it)
                    },
                    label = {
                        Text(stringResource(id = R.string.campo_n_tecnicos_necesarios))
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 4.dp)
                )
            }

            item {
                DividerSection(
                    nombreSeccion = stringResource(id = R.string.seccion_repuestos)
                )
            }

            item {
                Box {
                    SearchBar(
                        query = newUiState.repuestoBuscado,
                        onQueryChange = { onQueryChangeRepuesto(it) },
                        onSearch = { onSearchRepuesto() },
                        active = newUiState.searchBarActivaRepuesto,
                        onActiveChange = {  },
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.busqueda_indicacion_repuesto),
                                modifier = Modifier.wrapContentWidth()) },
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
                        expanded = newUiState.mostrarResultadosBusquedaRepuestos,
                        onDismissRequest = {
                            onSearchRepuesto() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        newUiState.resultadosBusquedaRepuesto.forEach { repuesto ->
                            DropdownMenuItem(
                                text = { Text(text = repuesto.descripcion.toString()) },
                                onClick = {
                                    onSelectRepuesto(repuesto)
                                }
                            )
                        }
                    }

                }
            }

            item {
                OutlinedTextField(
                    value = newUiState.cantidadRepuesto,
                    onValueChange = { onActualizarCantidad(it) },
                    label = {
                        Text(stringResource(id = R.string.campo_n_repuestos))
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
                )
            }

            item {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { onRegistrarRepuesto() },
                        enabled = newUiState.repuestoSeleccionadoTemp != null &&
                                newUiState.cantidadRepuesto.isNotEmpty(),
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

            items(newUiState.listaRepuestosSeleccionados.size) { index ->
                val repuestoSeleccionado = newUiState.listaRepuestosSeleccionados[index]
                ListItemCustome(
                    textoPrincipal = "${repuestoSeleccionado.repuesto.descripcion} (${repuestoSeleccionado.cantidad})",
                    textoSecundario = stringResource(id = R.string.estilo_moneda) + String.format("%.2f", repuestoSeleccionado.subtotal),
                    seleccionado = repuestoSeleccionado.marcado,
                    onCheckedChange = { onMarcarRepuesto(index) }
                )
            }

            item {
                Spacer(
                    modifier = Modifier.padding(top = 8.dp)
                )

                DividerSection(
                    nombreSeccion = stringResource(id = R.string.seccion_monto_total)
                )
            }

            item {
                AssistChip(
                    onClick = {},
                    label = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(
                                    id = R.string.label_presupuesto_estimado)
                                        + String.format("%.2f", newUiState.presupuestoEstimado),
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    },
                    leadingIcon = {},
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 0.dp)
                        .fillMaxWidth()
                )


                OutlinedTextField(
                    value = newUiState.porcentajeDescuento,
                    onValueChange = { onActualizarPorcentajeDescuento(it) },
                    label = {
                        Text(stringResource(id = R.string.campo_descuento))
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 3.dp)
                )

                AssistChip(
                    onClick = {},
                    label = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(
                                    id = R.string.label_descuento)
                                        + String.format("%.2f", newUiState.descuentoEnSoles),
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurface
                            ) } },
                    leadingIcon = {},
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 0.dp)
                        .fillMaxWidth()
                )

                AssistChip(
                    onClick = {},
                    label = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(
                                    id = R.string.label_presupuesto_final)
                                        + String.format("%.2f", newUiState.presupuestoFinal),
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        } },
                    leadingIcon = {},
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 0.dp)
                        .fillMaxWidth()
                )
            }

            item {
                Spacer(
                    modifier = Modifier.padding(top = 12.dp)
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 25.dp)
                ) {
                    Button(
                        onClick = {
                            onDenegar()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Cancel,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = stringResource(id = R.string.boton_denegar))
                    }

                    Spacer(modifier = Modifier.weight(0.2f))

                    Button(
                        onClick = {
                            onAceptar()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = stringResource(id = R.string.boton_aprobar))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PresupuestoLightPreview() {
    val navController = rememberNavController()
    val registroOstViewModel = RegistroOstViewModel()
    val viewModel = PresupuestoViewModel(registroOstViewModel)
    SIAMOTheme (darkTheme = false) {
        Presupuesto(
            newUiState = RegistrarPresupuestoUiState(),
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PresupuestoDarkPreview() {
    val navController = rememberNavController()
    val registroOstViewModel = RegistroOstViewModel()
    val viewModel = PresupuestoViewModel(registroOstViewModel)
    SIAMOTheme (darkTheme = true) {
        Presupuesto(
            newUiState = RegistrarPresupuestoUiState(),
        )
    }
}