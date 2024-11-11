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
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.NavigationBarRecepcionista
import com.example.siamo.ui.utils.TopBar

@Composable
fun RegistroCliente(
    modifier: Modifier = Modifier,
) {
    val expandedDocIdentidad = rememberSaveable { mutableStateOf(false) }
    val expandedSexo = rememberSaveable { mutableStateOf(false) }

    Scaffold (
        topBar = { TopBar(tituloPagina = stringResource(R.string.topbar_opcion2), modo = "Normal", modifier = Modifier.padding(bottom = 40.dp)) },
        bottomBar = { NavigationBarRecepcionista(opcionSeleccionada = 2) }
    ) { paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.registro_cliente_titulo),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 40.dp)
                )
            }

            item {
                OutlinedTextField(
                    value =  stringResource(id = R.string.ejemplo),
                    onValueChange = {},
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
                    value =  stringResource(id = R.string.ejemplo),
                    onValueChange = {},
                    label = {
                        Text(text = stringResource(id = R.string.campo_apellidos))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                )
            }

            item {
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                ) {
                    ListItem(
                        headlineContent = {
                            Text(
                                text = stringResource(id = R.string.desplegable_tipo_doc_principal),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        },
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null
                            )
                        },
                        trailingContent = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null
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
                                ) },
                            onClick = {
                                expandedDocIdentidad.value = false
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(R.string.desplegable_tipo_doc_opcion2),
                                ) },
                            onClick = {
                                expandedDocIdentidad.value = false
                            }
                        )
                    }
                }
            }

            item {
                OutlinedTextField(
                    value =  stringResource(id = R.string.ejemplo),
                    onValueChange = {},
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
                    value =  stringResource(id = R.string.ejemplo),
                    onValueChange = {},
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
                    value =  stringResource(id = R.string.ejemplo),
                    onValueChange = {},
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
                    value =  stringResource(id = R.string.ejemplo),
                    onValueChange = {},
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
                                text = stringResource(id = R.string.desplegable_sexo_principal),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        },
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Default.Stars,
                                contentDescription = null
                            )
                        },
                        trailingContent = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null
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
                                ) },
                            onClick = {
                                expandedSexo.value = false
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(R.string.desplegable_sexo_opcion2),
                                ) },
                            onClick = {
                                expandedSexo.value = false
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
                    onClick = { },
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
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegistroClienteLightPreview() {
    SIAMOTheme (darkTheme = false) { RegistroCliente() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegistroClienteDarkPreview() {
    SIAMOTheme (darkTheme = true) { RegistroCliente() }
}