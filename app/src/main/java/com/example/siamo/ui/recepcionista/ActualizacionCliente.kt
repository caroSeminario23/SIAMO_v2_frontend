package com.example.siamo.ui.recepcionista

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.model.Cliente
import com.example.siamo.model.Persona
import com.example.siamo.ui.consulta.ConsultaUiState
import com.example.siamo.ui.navigation.NavigationDestination
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.AlertDialogError
import com.example.siamo.ui.utils.AlertDialogOK
import com.example.siamo.ui.utils.NavigationBarRecepcionista
import com.example.siamo.ui.utils.TopBar

object ActulizacionClienteDestination : NavigationDestination {
    override val route = "actualizacion_cliente"
    override val titleRes = R.string.topbar_opcion3
}

@Composable
fun ActualizacionCliente(
    consultaUiState: ConsultaUiState,
    onAccept: () -> Unit,
    onCancel: () -> Unit,
    onUpdate: (Cliente) -> Unit,
    onCorrect: () -> Unit,
    onRetry: () -> Unit,
    buttomUp : () -> Unit,
    onHomeNav: () -> Unit = {},
    onSearchNav: () -> Unit = {},
    onSettingsNav: () -> Unit = {},
    modifier: Modifier = Modifier,

) {
    var cliente = consultaUiState.cliente
    var nombres by rememberSaveable { mutableStateOf(cliente?.persona?.nombres ?: "") }
    var apellidos by rememberSaveable { mutableStateOf(cliente?.persona?.apellidos ?: "") }
    var tipo_documento by rememberSaveable { mutableStateOf(cliente?.persona?.tipo_doc ?: 0) }
    var documento by rememberSaveable { mutableStateOf(cliente?.persona?.num_doc ?: "") }
    var direccion by rememberSaveable { mutableStateOf(cliente?.persona?.direccion ?: "") }
    var email by rememberSaveable { mutableStateOf(cliente?.persona?.correo ?: "") }
    var telefono by rememberSaveable { mutableStateOf(cliente?.persona?.telefono ?: "") }
    var sexo by rememberSaveable { mutableStateOf(cliente?.persona?.sexo ?: "") }

    Scaffold(
        topBar = {
            TopBar(
                tituloPagina = stringResource(R.string.topbar_opcion3),
                modo = "Retroceder",
                modifier = Modifier.padding(bottom = 40.dp),
                onLeftIcon = buttomUp
            )
        },
        bottomBar = {
            NavigationBarRecepcionista(
                opcionSeleccionada = 2,
                onHome = onHomeNav,
                onSearch = onSearchNav,
                onSettings = onSettingsNav
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.actualizacion_cliente_titulo),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 40.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = nombres,
                    onValueChange = { nombres = it },
                    label = {
                        Text(text = stringResource(id = R.string.campo_nombres))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = apellidos,
                    onValueChange = { apellidos = it },
                    label = {
                        Text(text = stringResource(id = R.string.campo_apellidos))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                )
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                ) {
                    ListItem(
                        headlineContent = {
                            Text(
                                text = if(tipo_documento == 1) "DNI" else "Carnet de extranjería",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                            )
                        },
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
                            )
                        },
                        trailingContent = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
                            )
                        },
                        modifier = Modifier
                    )
                }
            }

            item {
                OutlinedTextField(
                    value = documento,
                    onValueChange = { documento = it },
                    label = {
                        Text(
                            text = stringResource(id = R.string.campo_doc_identidad)
                        )
                    },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                        .graphicsLayer(alpha = 0.38f)
                )
            }

            item {
                OutlinedTextField(
                    value = direccion,
                    onValueChange = { direccion = it },
                    label = {
                        Text(text = stringResource(id = R.string.campo_direccion))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = {
                        Text(text = stringResource(id = R.string.campo_email))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = {
                        Text(text = stringResource(id = R.string.campo_telefono))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                )
            }

            item {
                Box {
                    ListItem(
                        headlineContent = {
                            Text(
                                text = if(sexo == "M") {"Masculino" }else {if(sexo == "F") "Femenino" else "Sexo"
                                },
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                            )
                        },
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Default.Stars,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
                            )
                        },
                        trailingContent = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.padding(2.dp))
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 40.dp)
                ) {
                    Button(
                        onClick = onCorrect,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = stringResource(id = R.string.correcto_boton))
                    }

                    Spacer(modifier = Modifier.weight(0.2f))

                    Button(
                        onClick = {
                            onUpdate(
                                Cliente(
                                    id_cliente = cliente?.id_cliente ?: 0,
                                    id_persona = cliente?.persona?.id_persona ?: 0,
                                    persona = Persona(
                                        nombres = nombres,
                                        apellidos = apellidos,
                                        tipo_doc = tipo_documento,
                                        num_doc = documento,
                                        direccion = direccion,
                                        correo = email,
                                        telefono = telefono,
                                        sexo = sexo
                                    )
                                )
                            )
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = stringResource(id = R.string.actualizar_boton))
                    }
                }
            }
        }

        if(consultaUiState.flag_ok_actualizar_cliente) {
            AlertDialogOK(
                titulo = stringResource(id = R.string.alerta_actualizacion_clienteok_title),
                contenido = stringResource(id = R.string.alerta_actualizacion_clienteok_text),
                onAccept = onAccept
            )
        }

        if(consultaUiState.flag_error_actualizar_cliente) {
            AlertDialogError(
                titulo = stringResource(id = R.string.alerta_actualizacion_clienteok_title),
                contenido = stringResource(id = R.string.alerta_actualizacion_clienteerror_text),
                onConfirm = onRetry,
                onCancel = onCancel
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ActualizacionClienteLightPreview() {
    SIAMOTheme(darkTheme = false) { ActualizacionCliente(
        consultaUiState = ConsultaUiState( flag_ok_actualizar_cliente = true ),
        onAccept = {},
        onCancel = {},
        onRetry = {},
        onUpdate = {},
        onCorrect = {},
        buttomUp = {}
    ) }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ActualizacionClienteDarkPreview() {
    SIAMOTheme(darkTheme = true) { ActualizacionCliente(
        consultaUiState = ConsultaUiState( flag_error_actualizar_cliente = true ),
        onAccept = {},
        onCancel = {},
        onRetry = {},
        onUpdate = {},
        onCorrect = {},
        buttomUp = {}
    ) }
}