package com.example.siamo.ui.recepcionista

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
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
import com.example.siamo.model.Automovil
import com.example.siamo.model.Cliente
import com.example.siamo.model.Consulta
import com.example.siamo.model.Empleado
import com.example.siamo.model.EstadoVehiculo
import com.example.siamo.model.FichaIngreso
import com.example.siamo.model.FichaSalida
import com.example.siamo.model.InformeTecnico
import com.example.siamo.model.OrderServicioTecnico
import com.example.siamo.model.Persona
import com.example.siamo.model.Tecnico
import com.example.siamo.ui.consulta.ConsultaUiState
import com.example.siamo.ui.navigation.NavigationDestination
import com.example.siamo.ui.ost.BuscarOstUiState
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.AlertDialogError
import com.example.siamo.ui.utils.AlertDialogOK
import com.example.siamo.ui.utils.NavigationBarRecepcionista
import com.example.siamo.ui.utils.TopBar

object DetalleOSTDestination : NavigationDestination {
    override val route = "actualizacion_cliente"
    override val titleRes = R.string.topbar_opcion3
}

@Composable
fun DetalleOST(
    buscarOstUiState: BuscarOstUiState,
    buttonUp: () -> Unit,
    onHomeNav: () -> Unit,
    onRegisterNav: () -> Unit,
    onSettingsNav: () -> Unit,
    modifier: Modifier = Modifier,

    ) {

    var ost_expanded by rememberSaveable { mutableStateOf(false) }
    var ingreso_expanded by rememberSaveable { mutableStateOf(false) }
    val ost = buscarOstUiState.ost

//    val ost = OrderServicioTecnico(
//        id_ost = 123,
//        fecha_registro = "2021-10-10",
//        fecha_aprox_ingreso = "2021-10-10",
//        estado = 2,
//        consulta = Consulta(
//            cliente = Cliente(
//                id_persona = 1,
//                persona = Persona(
//                    num_doc = "12345678",
//                    nombres = "Juan",
//                    apellidos = "Perez"
//                )
//            ),
//            automovil = Automovil(
//                placa = "ABC-123",
//                marca = "Toyota",
//                modelo = "Yaris",
//            ),
//            prob_declarado = "No enciende",
//            estado = 3,
//            tecnico = Tecnico(
//                id_empleado = 1,
//                empleado = Empleado(
//                    cod_empleado = 123,
//                    persona = Persona(
//                        nombres = "Carlos",
//                        apellidos = "Perez"
//                    )
//                )
//            )
//        ),
//        informe_tecnico = InformeTecnico(
//            id_informe_tecnico = 1,
//            fecha_inicio_reparacion = "2021-10-10",
//            fecha_fin_reparacion = "2021-10-10",
//            detalle_reparacion = "Se cambio la bateria",
//            observaciones = "Se recomienda cambiar el aceite",
//            saldo_final = 100.0,
//        ),
//        ficha_ingreso = FichaIngreso(
//            id_ficha_ingreso = 1,
//            fecha_ingreso = "2021-10-10",
//            fecha_aprox_recojo = "2021-10-10",
//            estado_vehiculo = EstadoVehiculo(
//                id_estado_vehiculo = 1,
//                estado_carroceria = "Bueno",
//                estado_neumaticos = "Bueno",
//                estado_motor = "Bueno",
//                estado_frenos = "Bueno"
//            )
//        ),
//        ficha_salida = FichaSalida(
//            id_ficha_salida = 1,
//            fecha_recojo = "2021-10-10"
//        )
//    )

    Scaffold(
        topBar = {
            TopBar(
                tituloPagina = stringResource(R.string.topbar_opcion10),
                modo = "Retroceder",
                onLeftIcon = buttonUp,
                modifier = Modifier.padding(bottom = 40.dp)
            )
        },
        bottomBar = { NavigationBarRecepcionista(
            opcionSeleccionada = 3,
            onHome = onHomeNav,
            onRegister = onRegisterNav,
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
                    text = stringResource(R.string.titulo_detalle_ost) + " " + ost?.id_ost,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = ost?.fecha_registro ?: "NA",
                    onValueChange = { },
                    label = {
                        Text(stringResource(R.string.fecha_de_registro_label))
                    },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = when (ost?.estado) {
                        1 -> "En proceso"
                        2 -> "Resuelto"
                        3 -> "Cancelado"
                        4 -> "Abandonado"
                        else -> "NA"
                    },
                    onValueChange = { },
                    label = {
                        Text(stringResource(R.string.estado_label))
                    },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = ost?.fecha_aprox_ingreso ?: "NA",
                    onValueChange = { },
                    label = {
                        Text(stringResource(R.string.fecha_de_ingreso_aproximada_label))
                    },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                )
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                ) {
                    Button(
                        onClick = { ost_expanded = !ost_expanded },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                        Text(text = stringResource(id = R.string.mas_detalle_button))
                    }
                }
            }

            if (ost_expanded) {
                item {
                    Column {
                        HorizontalDivider(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                            thickness = 1.dp
                        )
                        Text(
                            text = stringResource(R.string.sobre_el_cliente_header_divider),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Start,
                            modifier = modifier.padding(
                                start = 20.dp,
                                end = 20.dp,
                                top = 8.dp,
                                bottom = 20.dp
                            )
                        )
                    }
                }

                item {
                    OutlinedTextField(
                        value = (ost?.consulta?.cliente?.persona?.nombres + " " + ost?.consulta?.cliente?.persona?.apellidos)
                            ?: "NA",
                        onValueChange = { },
                        label = {
                            Text(stringResource(R.string.nombres_y_apellidos_label))
                        },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    )
                }

                item {
                    OutlinedTextField(
                        value = ost?.consulta?.cliente?.persona?.num_doc ?: "NA",
                        onValueChange = { },
                        label = {
                            Text(stringResource(R.string.campo_doc_identidad))
                        },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    )
                }

                item {
                    Column {
                        HorizontalDivider(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                            thickness = 1.dp
                        )
                        Text(
                            text = stringResource(R.string.sobre_el_vehiculo_divider),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Start,
                            modifier = modifier.padding(
                                start = 20.dp,
                                end = 20.dp,
                                top = 8.dp,
                                bottom = 20.dp
                            )
                        )
                    }
                }

                item {
                    OutlinedTextField(
                        value = ost?.consulta?.automovil?.placa ?: "NA",
                        onValueChange = { },
                        label = {
                            Text(stringResource(R.string.campo_n_placa))
                        },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    )
                }

                item {
                    OutlinedTextField(
                        value = (ost?.consulta?.automovil?.marca + " " + ost?.consulta?.automovil?.modelo)
                            ?: "NA",
                        onValueChange = { },
                        label = {
                            Text(stringResource(R.string.marca_y_modelo_label))
                        },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    )
                }

                item {
                    Column {
                        HorizontalDivider(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                            thickness = 1.dp
                        )
                        Text(
                            text = stringResource(R.string.sobre_el_tecnico_asignado_divider),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Start,
                            modifier = modifier.padding(
                                start = 20.dp,
                                end = 20.dp,
                                top = 8.dp,
                                bottom = 20.dp
                            )
                        )
                    }
                }

                item {
                    OutlinedTextField(
                        value = ost?.consulta?.tecnico?.empleado?.cod_empleado.toString() ?: "NA",
                        onValueChange = { },
                        label = {
                            Text(stringResource(R.string.codigo_label))
                        },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    )
                }

                item {
                    OutlinedTextField(
                        value = (ost?.consulta?.tecnico?.empleado?.persona?.nombres + " " + ost?.consulta?.tecnico?.empleado?.persona?.apellidos)
                            ?: "NA",
                        onValueChange = { },
                        label = {
                            Text(stringResource(R.string.nombres_y_apellidos_label))
                        },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    )
                }

                if(ost?.informe_tecnico != null) {
                    item {
                        Column {
                            HorizontalDivider(
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                thickness = 1.dp
                            )
                            Text(
                                text = stringResource(R.string.sobre_el_informe_tecnico_divider),
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Start,
                                modifier = modifier.padding(
                                    start = 20.dp,
                                    end = 20.dp,
                                    top = 8.dp,
                                    bottom = 20.dp
                                )
                            )
                        }
                    }

                    item {
                        OutlinedTextField(
                            value = ost?.informe_tecnico?.fecha_inicio_reparacion ?: "NA",
                            onValueChange = { },
                            label = {
                                Text(stringResource(R.string.fecha_inicio_de_reparacion_label))
                            },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                        )
                    }

                    item {
                        OutlinedTextField(
                            value = ost?.informe_tecnico?.fecha_fin_reparacion ?: "NA",
                            onValueChange = { },
                            label = {
                                Text(stringResource(R.string.fecha_de_fin_de_reparacion_label))
                            },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                        )
                    }

                    item {
                        OutlinedTextField(
                            value = ost?.informe_tecnico?.detalle_reparacion ?: "NA",
                            onValueChange = { },
                            label = {
                                Text(stringResource(R.string.detalle_de_reparacion_label))
                            },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                        )
                    }

                    item {
                        OutlinedTextField(
                            value = ost?.informe_tecnico?.saldo_final.toString() ?: "NA",
                            onValueChange = { },
                            label = {
                                Text(stringResource(R.string.monto_final_label))
                            },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                        )
                    }

                    item {
                        OutlinedTextField(
                            value = ost?.informe_tecnico?.observaciones ?: "NA",
                            onValueChange = { },
                            label = {
                                Text(stringResource(R.string.observaciones_label))
                            },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                        )
                    }
                }

                if(ost?.ficha_ingreso != null) {
                    item {
                        Column {
                            HorizontalDivider(
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                thickness = 1.dp
                            )
                            Text(
                                text = stringResource(R.string.sobre_la_ficha_de_ingreso_divider),
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Start,
                                modifier = modifier.padding(
                                    start = 20.dp,
                                    end = 20.dp,
                                    top = 8.dp,
                                    bottom = 20.dp
                                )
                            )
                        }
                    }

                    item {
                        OutlinedTextField(
                            value = ost?.ficha_ingreso?.fecha_ingreso?.substring(0, 9) ?: "NA",
                            onValueChange = { },
                            label = {
                                Text(stringResource(R.string.fecha_de_ingreso_label))
                            },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                        )
                    }

                    item {
                        OutlinedTextField(
                            value = ost?.ficha_ingreso?.fecha_aprox_recojo ?: "NA",
                            onValueChange = { },
                            label = {
                                Text(stringResource(R.string.fecha_de_recojo_aproximada_label))
                            },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                        )
                    }

                    item {
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                        ) {
                            Button(
                                onClick = { ingreso_expanded = !ingreso_expanded },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null
                                )
                                Text(stringResource(R.string.mas_detalle_button))
                            }
                        }
                    }

                }
            }

            if (ost_expanded && ingreso_expanded) {
                item {
                    Column {
                        HorizontalDivider(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                            thickness = 1.dp
                        )
                        Text(
                            text = stringResource(R.string.sobre_el_estado_del_vehiculo_divider),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Start,
                            modifier = modifier.padding(
                                start = 20.dp,
                                end = 20.dp,
                                top = 8.dp,
                                bottom = 20.dp
                            )
                        )
                    }
                }

                item {
                    OutlinedTextField(
                        value = ost?.ficha_ingreso?.estado_vehiculo?.estado_carroceria ?: "NA",
                        onValueChange = { },
                        label = {
                            Text(stringResource(R.string.estado_de_carroceria_label))
                        },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    )
                }

                item {
                    OutlinedTextField(
                        value = ost?.ficha_ingreso?.estado_vehiculo?.estado_neumaticos ?: "NA",
                        onValueChange = { },
                        label = {
                            Text(stringResource(R.string.estado_de_neumaticos_label))
                        },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    )
                }

                item {
                    OutlinedTextField(
                        value = ost?.ficha_ingreso?.estado_vehiculo?.estado_motor ?: "NA",
                        onValueChange = { },
                        label = {
                            Text(stringResource(R.string.estado_de_motor_label))
                        },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    )
                }

                item {
                    OutlinedTextField(
                        value = ost?.ficha_ingreso?.estado_vehiculo?.estado_frenos ?: "NA",
                        onValueChange = { },
                        label = {
                            Text(stringResource(R.string.estado_de_frenos_label))
                        },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    )
                }

                item {
                    Column {
                        HorizontalDivider(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                            thickness = 1.dp
                        )
                        Text(
                            text = stringResource(R.string.sobre_ficha_salida_divider),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Start,
                            modifier = modifier.padding(
                                start = 20.dp,
                                end = 20.dp,
                                top = 8.dp,
                                bottom = 20.dp
                            )
                        )
                    }
                }

                if(ost?.ficha_salida != null) {
                    item {
                        OutlinedTextField(
                            value = ost?.ficha_salida?.fecha_recojo?.substring(0, 9) ?: "NA",
                            onValueChange = { },
                            label = {
                                Text(stringResource(R.string.fecha_de_recojo_label))
                            },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun DetalleOSTLightPreview() {
    SIAMOTheme(darkTheme = false) {
        DetalleOST(
            buscarOstUiState = BuscarOstUiState(),
            buttonUp = {},
            onHomeNav = {},
            onRegisterNav = {},
            onSettingsNav = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun DetalleOSTDarkPreview() {
    SIAMOTheme(darkTheme = true) {
        DetalleOST(
            buscarOstUiState = BuscarOstUiState(),
            buttonUp = {},
            onHomeNav = {},
            onRegisterNav = {},
            onSettingsNav = {}
        )
    }
}