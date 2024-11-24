package com.example.siamo.ui.recepcionista

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.model.Persona
import com.example.siamo.ui.consulta.ConsultaUiState
import com.example.siamo.ui.navigation.NavigationDestination
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.AlertDialogError
import com.example.siamo.ui.utils.AlertDialogOK
import com.example.siamo.ui.utils.NavigationBarRecepcionista
import com.example.siamo.ui.utils.TopBar

object RegistroClienteDestination : NavigationDestination {
    override val route = "registro_cliente"
    override val titleRes = R.string.topbar_opcion4
}

@Composable
fun RegistroCliente(
    consultaUiState: ConsultaUiState,
    modifier: Modifier = Modifier,
    onAccept: () -> Unit = {},
    onCancel: () -> Unit = {},
    onRetry: () -> Unit = {},
    onRegister: (Persona) -> Unit = {},
    onHomeNav: () -> Unit = {},
    onSearchNav: () -> Unit = {},
    onSettingsNav: () -> Unit = {},
    buttonUp : () -> Unit = {}
) {
    val expandedDocIdentidad = rememberSaveable { mutableStateOf(false) }
    val expandedSexo = rememberSaveable { mutableStateOf(false) }

    val nombres = rememberSaveable { mutableStateOf("") }
    val apellidos = rememberSaveable { mutableStateOf("") }
    val tipoDoc = rememberSaveable { mutableStateOf("Tipo de documento de identidad") }
    val docIdentidad = rememberSaveable { mutableStateOf("") }
    val direccion = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val telefono = rememberSaveable { mutableStateOf("") }
    val sexo = rememberSaveable { mutableStateOf("Sexo") }

    Scaffold(
        topBar = {
            TopBar(
                tituloPagina = stringResource(R.string.topbar_opcion4),
                modo = "Retroceder",
                modifier = Modifier.padding(bottom = 40.dp),
                onLeftIcon = buttonUp
            )
        },
        bottomBar = { NavigationBarRecepcionista(
            opcionSeleccionada = 2,
            onHome = onHomeNav,
            onSearch = onSearchNav,
            onSettings = onSettingsNav
        ) }
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
                    text = stringResource(id = R.string.topbar_opcion4),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 40.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = nombres.value,
                    onValueChange = { nombres.value = it },
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
                    value = apellidos.value,
                    onValueChange = { apellidos.value = it },
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
                                text = tipoDoc.value,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        },
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        trailingContent = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        modifier = Modifier
                            .clickable { expandedDocIdentidad.value = true }
                    )

                    DropdownMenu(
                        expanded = expandedDocIdentidad.value,
                        onDismissRequest = { expandedDocIdentidad.value = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(R.string.desplegable_tipo_doc_opcion1),
                                )
                            },
                            onClick = {
                                expandedDocIdentidad.value = false
                                tipoDoc.value = "DNI"
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(R.string.desplegable_tipo_doc_opcion2),
                                )
                            },
                            onClick = {
                                expandedDocIdentidad.value = false
                                tipoDoc.value = "Carnet de extranjería"
                            }
                        )
                    }
                }
            }

            item {
                OutlinedTextField(
                    value = docIdentidad.value,
                    onValueChange = { docIdentidad.value = it },
                    label = {
                        Text(text = stringResource(id = R.string.campo_doc_identidad))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = direccion.value,
                    onValueChange = { direccion.value = it },
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
                    value = email.value,
                    onValueChange = { email.value = it },
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
                    value = telefono.value,
                    onValueChange = { telefono.value = it },
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
                                text = sexo.value,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        },
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Default.Stars,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        trailingContent = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                            .clickable { expandedSexo.value = true }
                    )

                    DropdownMenu(
                        expanded = expandedSexo.value,
                        onDismissRequest = { expandedSexo.value = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(R.string.desplegable_sexo_opcion1),
                                )
                            },
                            onClick = {
                                expandedSexo.value = false
                                sexo.value = "Femenino"
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(R.string.desplegable_sexo_opcion2),
                                )
                            },
                            onClick = {
                                expandedSexo.value = false
                                sexo.value = "Masculino"
                            }
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.padding(2.dp))
            }

            item {
                Button(
                    onClick = {
                        onRegister(
                            Persona(
                                nombres = nombres.value,
                                apellidos = apellidos.value,
                                tipo_doc = if (tipoDoc.value == "DNI") {
                                    1
                                } else if (tipoDoc.value == "Carnet de extranjería") {
                                    2
                                } else {
                                    null
                                },
                                num_doc = docIdentidad.value,
                                direccion = direccion.value,
                                correo = email.value,
                                telefono = telefono.value,
                                sexo = sexo.value.substring(0, 1)
                            )
                        )
                    },
                    modifier = Modifier
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = stringResource(id = R.string.registrar_boton))
                }
            }

            item {
                Spacer(modifier = Modifier.padding(15.dp))
            }
        }

        if (consultaUiState.flag_error_registrar_cliente) {
            AlertDialogError(
                titulo = stringResource(id = R.string.alerta_registro_cliente_title),
                contenido = stringResource(id = R.string.alerta_registro_clienteerror_text),
                onConfirm = onRetry,
                onCancel = onCancel
            )

        }

        if (consultaUiState.flag_ok_registrar_cliente) {
            AlertDialogOK(
                titulo = stringResource(id = R.string.alerta_registro_cliente_title),
                contenido = stringResource(id = R.string.alerta_registro_clienteok_text),
                onAccept = onAccept
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegistroClienteLightPreview() {
    SIAMOTheme(darkTheme = false) {
        RegistroCliente(
            consultaUiState = ConsultaUiState()
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegistroClienteDarkPreview() {
    SIAMOTheme(darkTheme = true) {
        RegistroCliente(
            consultaUiState = ConsultaUiState()
        )
    }
}