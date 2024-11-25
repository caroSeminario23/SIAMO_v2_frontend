package com.example.siamo.ui.tecnico.resumen_ost

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.siamo.navigation.NavRoutes
import com.example.siamo.R
import com.example.siamo.ui.tecnico.registrar_presupuesto.RegistrarPresupuestoUiState
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstUiState
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstViewModel
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.AlertDialogError
import com.example.siamo.ui.utils.AlertDialogOK
import com.example.siamo.ui.utils.DividerSection
import com.example.siamo.ui.utils.NavigationBarRecepcionista
import com.example.siamo.ui.utils.TopBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ResumenOST(
    newUiState: RegistrarPresupuestoUiState,
    onConfirmError: () -> Unit = {},
    onDismissError: () -> Unit = {},
    onAceptar: () -> Unit = {},
    onIngresoActual: () -> Unit = {},
    onIngresoPosterior: () -> Unit = {},
    onActivarDesplegableDia: () -> Unit = {},
    onActivarDesplegableMes: () -> Unit = {},
    onActivarDesplegableAnio: () -> Unit = {},
    onSeleccionarDia: (Int) -> Unit = {},
    onSeleccionarMes: (Int) -> Unit = {},
    onSeleccionarAnio: (Int) -> Unit = {},
    onDesactivarDesplegable: () -> Unit = {},
    onDenegar: () -> Unit = {},
    onRegistrar: () -> Unit = {},
    modifier: Modifier = Modifier,
) {

    if ( newUiState.flag_error_registro_ost ) {
        AlertDialogError(
            titulo = stringResource(id = R.string.dialog_registro_ost),
            contenido = stringResource(id = R.string.dialog_registro_ost_error),
            onConfirmClick = { onConfirmError() },
            onDismissClick = { onDismissError() }
        )
    }

    if(newUiState.flag_ok_registro_ost) {
        AlertDialogOK(
            titulo = stringResource(id = R.string.dialog_registro_ost),
            contenido = stringResource(id = R.string.dialog_registro_ost_exitoso),
            onConfirmClick = { onAceptar() }
        )
    }

    Scaffold(
        topBar = {
            TopBar(
                tituloPagina = stringResource(R.string.topbar_registro_ost),
                modo = "Retroceder"
            )
        },
        bottomBar = { NavigationBarRecepcionista(opcionSeleccionada = 2) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.titulo_resumen_consulta1),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        modifier = modifier.padding(top = 12.dp)
                    )

                    Text(
                        text = stringResource(id = R.string.titulo_resumen_ost),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        modifier = modifier
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))
            }

            item {
                Text(
                    text = stringResource(id = R.string.label_lista_problemas),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Start,
                    modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 15.dp)

                )
            }

            items(newUiState.listaProblemasSeleccionados.size) { index ->
                val problema = newUiState.listaProblemasSeleccionados[index]
                Text(
                    text = problema.problema.descripcion ?: "",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Start,
                    modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.label_lista_soluciones),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Start,
                    modifier = modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 15.dp,
                        top = 15.dp
                    )
                )
            }

            items(newUiState.listaSolucionesRegistradas.size) { index ->
                val solucion = newUiState.listaSolucionesRegistradas[index]
                Text(
                    text = solucion.solucion.descripcion ?: "",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Start,
                    modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.label_presupuesto_final_resumen),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Start,
                    modifier = modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 15.dp,
                        top = 15.dp
                    )
                )

                Text(
                    text = stringResource(id = R.string.estilo_moneda) + String.format(
                        "%.2f",
                        newUiState.presupuestoFinal
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Start,
                    modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                )

                Text(
                    text = stringResource(id = R.string.label_descuento) + String.format(
                        "%.2f",
                        newUiState.descuentoEnSoles
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Start,
                    modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.label_repuestos_necesarios),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Start,
                    modifier = modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 15.dp,
                        top = 15.dp
                    )
                )
            }

            items(newUiState.listaRepuestosSeleccionados.size) { index ->
                val repuesto = newUiState.listaRepuestosSeleccionados[index]
                Text(
                    text = repuesto.repuesto.descripcion + " (" + repuesto.cantidad + ")",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Start,
                    modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                )
            }

            item {
                Spacer(modifier = Modifier.padding(10.dp))

                DividerSection(nombreSeccion = stringResource(id = R.string.seccion_ingreso_vehiculo))

                Button(
                    onClick = { onIngresoActual() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp, top = 5.dp)
                ) {
                    Text(text = stringResource(id = R.string.boton_ingreso_ahora))
                }

                Button(
                    onClick = { onIngresoPosterior() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp, top = 5.dp)
                ) {
                    Text(text = stringResource(id = R.string.boton_ingreso_luego))
                }
            }

            item {
                Row {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
                            .weight(1f)
                    ) {
                        ListItem(
                            headlineContent = {
                                Text(
                                    text =
                                    if (newUiState.diaSeleccionado == 0) {
                                        stringResource(id = R.string.desplegable_dia_principal)
                                    } else {
                                        newUiState.diaSeleccionado.toString()
                                    },
                                    style = MaterialTheme.typography.bodyMedium,
                                    color =
                                    if (newUiState.desplegablesHabilitados) {
                                        MaterialTheme.colorScheme.onSurface
                                    } else {
                                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                                    }
                                )
                            },
                            trailingContent = {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = null,
                                    tint =
                                    if (newUiState.desplegablesHabilitados) {
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                    } else {
                                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
                                    }
                                )
                            },
                            modifier = Modifier
                                .then(
                                    if (newUiState.desplegablesHabilitados) {
                                        Modifier.clickable { onActivarDesplegableDia() }
                                    } else {
                                        Modifier
                                    }
                                )
                        )

                        DropdownMenu(
                            expanded = newUiState.expandedDia && newUiState.desplegablesHabilitados,
                            onDismissRequest = {
                                onDesactivarDesplegable()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                        ) {
                            (1..30).forEach { dia ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = dia.toString(),
                                        )
                                    },
                                    onClick = {
                                        onSeleccionarDia(dia)
                                    }
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
                            .weight(1f)
                    ) {
                        ListItem(
                            headlineContent = {
                                Text(
                                    text =
                                    if (newUiState.mesSeleccionado == 0) {
                                        stringResource(id = R.string.desplegable_mes_principal)
                                    } else {
                                        newUiState.mesSeleccionado.toString()
                                    },
                                    style = MaterialTheme.typography.bodyMedium,
                                    color =
                                    if (newUiState.desplegablesHabilitados) {
                                        MaterialTheme.colorScheme.onSurface
                                    } else {
                                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                                    }
                                )
                            },
                            trailingContent = {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = null,
                                    tint =
                                    if (newUiState.desplegablesHabilitados) {
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                    } else {
                                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
                                    }
                                )
                            },
                            modifier = Modifier
                                .then(
                                    if (newUiState.desplegablesHabilitados) {
                                        Modifier.clickable { onActivarDesplegableMes() }
                                    } else {
                                        Modifier
                                    }
                                )
                        )

                        DropdownMenu(
                            expanded = newUiState.expandedMes && newUiState.desplegablesHabilitados,
                            onDismissRequest = {
                                onDesactivarDesplegable()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                        ) {
                            (1..12).forEach { mes ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = mes.toString(),
                                        )
                                    },
                                    onClick = {
                                        onSeleccionarMes(mes)
                                    }
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
                            .weight(1f)
                    ) {
                        ListItem(
                            headlineContent = {
                                Text(
                                    text =
                                    if (newUiState.anioSeleccionado == 0) {
                                        stringResource(id = R.string.desplegable_anio_principal)
                                    } else {
                                        newUiState.anioSeleccionado.toString()
                                    },
                                    style = MaterialTheme.typography.bodyMedium,
                                    color =
                                    if (newUiState.desplegablesHabilitados) {
                                        MaterialTheme.colorScheme.onSurface
                                    } else {
                                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                                    }
                                )
                            },
                            trailingContent = {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = null,
                                    tint =
                                    if (newUiState.desplegablesHabilitados) {
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                    } else {
                                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
                                    }
                                )
                            },
                            modifier = Modifier
                                .then(
                                    if (newUiState.desplegablesHabilitados) {
                                        Modifier.clickable { onActivarDesplegableAnio() }
                                    } else {
                                        Modifier
                                    }
                                )
                        )

                        DropdownMenu(
                            expanded = newUiState.expandedAnio && newUiState.desplegablesHabilitados,
                            onDismissRequest = {
                                onDesactivarDesplegable()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                        ) {
                            intArrayOf(2023,2024).forEach { anio ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = anio.toString(),
                                        )
                                    },
                                    onClick = {
                                        onSeleccionarAnio(anio)
                                    }
                                )
                            }
                        }

                    }
                }
            }

            item {
                Spacer(
                    modifier = Modifier.padding(top = 12.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 25.dp)
                ) {
                    Button(
                        onClick = {
                            onDenegar()
                        },
                        enabled = newUiState.registroCancelacionActivo1 && newUiState.registroCancelacionActivo2 && newUiState.registroCancelacionActivo3,
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
                            onRegistrar()
                        },
                        enabled = newUiState.registroCancelacionActivo1 && newUiState.registroCancelacionActivo2 && newUiState.registroCancelacionActivo3,
                        modifier = Modifier.weight(1f)
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
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ResumenOSTLightPreview() {
    val navController = rememberNavController()
    val registroOstViewModel = RegistroOstViewModel()
    val viewModel = ResumenOstViewModel(registroOstViewModel)
    SIAMOTheme (darkTheme = false) {
        ResumenOST(
            newUiState = RegistrarPresupuestoUiState()
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ResumenOSTDarkPreview() {
    val navController = rememberNavController()
    val registroOstViewModel = RegistroOstViewModel()
    val viewModel = ResumenOstViewModel(registroOstViewModel)
    SIAMOTheme (darkTheme = true) {
        ResumenOST(
            newUiState = RegistrarPresupuestoUiState()
        )
    }
}