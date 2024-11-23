package com.example.siamo.ui.recepcionista

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.model.Tecnico
import com.example.siamo.ui.consulta.ConsultaUiState
import com.example.siamo.ui.navigation.NavigationDestination
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.NavigationBarRecepcionista
import com.example.siamo.ui.utils.TopBar

object AsignacionTecnicoDestination : NavigationDestination {
    override val route = "asignacion_tecnico"
    override val titleRes = R.string.topbar_opcion8
}

@Composable
fun AsignacionTenico(
    consultaUiState: ConsultaUiState,
    onNext: (Tecnico) -> Unit,
    onPrevius: () -> Unit = {},
    modifier: Modifier = Modifier,
) {

    var expandedListaTecnicos = rememberSaveable { mutableStateOf(false) }

    var tecnicoSeleccionado = Tecnico(id_tecnico = 0)

    Scaffold(
        topBar = {
            TopBar(
                tituloPagina = stringResource(R.string.topbar_opcion8),
                modo = "Retroceder"
            )
        },
        bottomBar = { NavigationBarRecepcionista(opcionSeleccionada = 2) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.asignacion_tecnico),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = modifier.padding(15.dp)
            )

            Spacer(modifier = Modifier.padding(6.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            ) {
                ListItem(
                    headlineContent = {
                        Text(
                            text = if (tecnicoSeleccionado.id_tecnico == 0)
                                stringResource(id = R.string.desplegable_lista_tecnico_principal)
                            else
                                (tecnicoSeleccionado.empleado?.persona?.nombres
                                    ?: "Nombre") + " " + (tecnicoSeleccionado.empleado?.persona?.apellidos),
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
                        .clickable { expandedListaTecnicos.value = true }
                )

                DropdownMenu(
                    expanded = expandedListaTecnicos.value,
                    onDismissRequest = { expandedListaTecnicos.value = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                ) {
                    consultaUiState.tecnicos?.forEach { tecnico ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = ((tecnico.empleado?.persona?.nombres
                                          + " " + tecnico.empleado?.persona?.apellidos)
                                            + " - OST pendientes: " + tecnico.ost_count),
                                )
                            },
                            onClick = {
                                expandedListaTecnicos.value = false
                                tecnicoSeleccionado = tecnico
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(20.dp))

            Button(
                onClick = {
                    if(tecnicoSeleccionado.id_tecnico != 0) onNext(tecnicoSeleccionado)
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.NavigateNext,
                    contentDescription = null
                )
                Text(text = stringResource(id = R.string.boton_siguiente))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun AsignacionTecnicoLightPreview() {
    SIAMOTheme(darkTheme = false) {
        AsignacionTenico(
            consultaUiState = ConsultaUiState(),
            onNext = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun AsignacionTecnicoDarkPreview() {
    SIAMOTheme(darkTheme = true) {
        AsignacionTenico(
            consultaUiState = ConsultaUiState(),
            onNext = {}
        )
    }
}