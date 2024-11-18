package com.example.siamo.ui.tecnico.resumenost

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Calendar
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.siamo.NavRoutes
import com.example.siamo.R
import com.example.siamo.data.bitacora_concreta
import com.example.siamo.ui.tecnico.presupuesto.PresupuestoViewModel
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.AlertDialogError
import com.example.siamo.ui.utils.AlertDialogOK
import com.example.siamo.ui.utils.DividerSection
import com.example.siamo.ui.utils.NavigationBarRecepcionista
import com.example.siamo.ui.utils.TopBar

@Composable
fun ResumenOST(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ResumenOstViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.errorRegistro == 1 -> {
            AlertDialogError(
                titulo = stringResource(id = R.string.dialog_registro_ost),
                contenido = stringResource(id = R.string.dialog_registro_ost_error),
                onConfirmClick = { viewModel.guardarOst() },
                onDismissClick = { viewModel.cerrarAlertar() }
            )
        }
        uiState.errorRegistro == 2 -> {
            AlertDialogOK(
                titulo = stringResource(id = R.string.dialog_registro_ost),
                contenido = stringResource(id = R.string.dialog_registro_ost_exitoso),
                onConfirmClick = { navController.navigate(NavRoutes.HomeTecnico.route) }
            )
        }
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
        LazyColumn (
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

            items(uiState.problemas.size) { index ->
                val problema = uiState.problemas[index]
                Text(
                    text = problema,
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
                    modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 15.dp, top = 15.dp)
                )
            }

            items(uiState.soluciones.size) { index ->
                val problema = uiState.soluciones[index]
                Text(
                    text = problema,
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
                    modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 15.dp, top = 15.dp)
                )

                Text(
                    text = stringResource(id = R.string.estilo_moneda) + String.format(
                        "%.2f",
                        uiState.presupuestoUiState.presupuestoFinal
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Start,
                    modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                )

                Text(
                    text = stringResource(id = R.string.label_descuento) + String.format(
                        "%.2f",
                        uiState.presupuestoUiState.descuentoEnSoles
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
                    modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 15.dp, top = 15.dp)
                )
            }

            items(uiState.presupuestoUiState.listaRepuestosSeleccionados.size) { index ->
                val repuesto = uiState.presupuestoUiState.listaRepuestosSeleccionados[index]
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
                    onClick = { viewModel.registrarFechaActual() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp, top = 5.dp)
                ) {
                    Text(text = stringResource(id = R.string.boton_ingreso_ahora))
                }

                Button(
                    onClick = { viewModel.activarRegistroManual() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp, top = 5.dp)
                ) {
                    Text(text = stringResource(id = R.string.boton_ingreso_luego))
                }

                OutlinedTextField(
                    value = uiState.fechaAproxIngreso,
                    onValueChange = {
                        viewModel.actualizarFechaIngreso(it)
                    },
                    label = {
                        Text(stringResource(id = R.string.campo_fecha_aprox_ingreso))
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    enabled = uiState.ingresoFechaActivo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp, bottom = 15.dp, top = 10.dp)
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
                            navController.navigate(NavRoutes.HomeTecnico.route)
                        },
                        enabled = uiState.registroCancelacionActivo,
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
                            viewModel.guardarOst()
                        },
                        enabled = uiState.registroCancelacionActivo,
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

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ResumenOSTLightPreview() {
    val navController = rememberNavController()
    val viewModel = ResumenOstViewModel()
    SIAMOTheme (darkTheme = false) { ResumenOST(
        viewModel = viewModel,
        navController = navController) }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ResumenOSTDarkPreview() {
    val navController = rememberNavController()
    val viewModel = ResumenOstViewModel()
    SIAMOTheme (darkTheme = true) { ResumenOST(
        viewModel = viewModel,
        navController = navController) }
}