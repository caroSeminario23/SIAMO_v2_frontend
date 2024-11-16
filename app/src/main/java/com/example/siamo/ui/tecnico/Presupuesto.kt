package com.example.siamo.ui.tecnico

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.data.consulta_repuesto
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.DividerSection
import com.example.siamo.ui.utils.ListItemCustome
import com.example.siamo.ui.utils.NavigationBarTecnico
import com.example.siamo.ui.utils.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Presupuesto(
    modifier: Modifier = Modifier
) {
    val listaRepuestos = listOf(
        consulta_repuesto("Repuesto 1", 100.00),
        consulta_repuesto("Repuesto 2", 200.00),
        consulta_repuesto("Repuesto 3", 300.00),
        consulta_repuesto("Repuesto 4", 400.00),
        consulta_repuesto("Repuesto 5", 500.00),
        consulta_repuesto("Repuesto 6", 600.00),
        consulta_repuesto("Repuesto 7", 700.00),
        consulta_repuesto("Repuesto 8", 800.00),
        consulta_repuesto("Repuesto 9", 900.00),
        consulta_repuesto("Repuesto 10", 1000.00)
    )
    val listaRepuestosSeleccionados = listOf(
        consulta_repuesto("Repuesto 1", 100.00),
        consulta_repuesto("Repuesto 2", 200.00),
        consulta_repuesto("Repuesto 3", 300.00),
        consulta_repuesto("Repuesto 4", 400.00),
        consulta_repuesto("Repuesto 5", 500.00)
    )

    var query by rememberSaveable { mutableStateOf("") }
    var resultadosBusqueda by rememberSaveable { mutableStateOf(listaRepuestos) }
    var desplegableRepuestos by rememberSaveable { mutableStateOf(false) }

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
                    value = stringResource(id = R.string.ejemplo),
                    onValueChange = {},
                    label = {
                        Text(stringResource(id = R.string.campo_n_tecnicos_necesarios))
                    },
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
                        query = query,
                        onQueryChange = {
                            query = it
                            resultadosBusqueda = listaRepuestos.filter { repuesto ->
                                repuesto.nombre.contains(query, ignoreCase = true)
                            }
                            desplegableRepuestos = resultadosBusqueda.isNotEmpty()
                        },
                        onSearch = {
                            desplegableRepuestos = false
                        },
                        active = false,
                        onActiveChange = {},
                        placeholder = { Text(text = stringResource(id = R.string.busqueda_indicacion_repuesto)) },
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
                        content = { }
                    )

                    DropdownMenu(
                        expanded = desplegableRepuestos,
                        onDismissRequest = { desplegableRepuestos = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        resultadosBusqueda.forEach { repuesto ->
                            DropdownMenuItem(
                                text = { Text(text = repuesto.nombre) },
                                onClick = {
                                    query = repuesto.nombre
                                    desplegableRepuestos = false
                                }
                            )
                        }
                    }
                }
            }

            item {
                OutlinedTextField(
                    value = stringResource(id = R.string.ejemplo),
                    onValueChange = {},
                    label = {
                        Text(stringResource(id = R.string.campo_n_repuestos))
                    },
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
                        onClick = { },
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

            items(listaRepuestosSeleccionados.size) { index ->
                val repuesto = listaRepuestosSeleccionados[index]
                ListItemCustome(
                    textoPrincipal = repuesto.nombre,
                    textoSecundario = stringResource(id = R.string.estilo_moneda) + String.format("%.2f", repuesto.monto)
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
                                        + "00.00",
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
                    value = stringResource(id = R.string.contenido_texto_precio),
                    onValueChange = {},
                    label = {
                        Text(stringResource(id = R.string.campo_descuento))
                    },
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
                                        + "00.00",
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurface
                            ) } },
                    leadingIcon = {},
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 0.dp)
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
                                        + "00.00",
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
                        onClick = { },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = stringResource(id = R.string.boton_denegar))
                    }

                    Spacer(modifier = Modifier.weight(0.2f))

                    Button(
                        onClick = { },
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
    SIAMOTheme (darkTheme = false) { Presupuesto() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PresupuestoDarkPreview() {
    SIAMOTheme (darkTheme = true) { Presupuesto() }
}