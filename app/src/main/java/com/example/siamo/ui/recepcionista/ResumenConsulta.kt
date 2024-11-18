package com.example.siamo.ui.recepcionista

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.model.Automovil
import com.example.siamo.model.Cliente
import com.example.siamo.model.Empleado
import com.example.siamo.model.Tecnico
import com.example.siamo.ui.consulta.ConsultaUiState
import com.example.siamo.ui.navigation.NavigationDestination
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.AlertDialogError
import com.example.siamo.ui.utils.AlertDialogOK
import com.example.siamo.ui.utils.NavigationBarRecepcionista
import com.example.siamo.ui.utils.TopBar

object ResumenConsultaDestination : NavigationDestination {
    override val route = "resumen_consulta"
    override val titleRes = R.string.topbar_opcion9
}

@Composable
fun ResumenConsulta(
    consultaUiState: ConsultaUiState,
    onRegister: () -> Unit,
    onAccept: () -> Unit = {},
    onRetry: () -> Unit = {},
    onCancel: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    val cliente = consultaUiState.cliente
    val automovil = consultaUiState.automovil
    val tecnico = consultaUiState.tecnico_asignado


    var nombreDocumento = if (cliente?.persona?.tipo_doc == 1) {
        "DNI"
    } else {
        "CE"
    }

    Scaffold(
        topBar = {
            TopBar(
                tituloPagina = stringResource(R.string.topbar_opcion9),
                modo = "Retroceder",
                modifier = Modifier.padding(bottom = 40.dp)
            )
        },
        bottomBar = { NavigationBarRecepcionista(opcionSeleccionada = 2) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            //horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.titulo_resumen_consulta1),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                text = stringResource(id = R.string.titulo_resumen_consulta2),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.padding(20.dp))

            Text(
                text = stringResource(id = R.string.label_cliente),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 15.dp)

            )

            Text(
                text = (cliente?.persona?.nombres ?: "NA") + " " + (cliente?.persona?.apellidos
                    ?: "NA"),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )

            Text(
                text = (nombreDocumento ?: "DOC") + ": " + (cliente?.persona?.num_doc ?: "NA"),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            Text(
                text = stringResource(id = R.string.label_vehiculo),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 15.dp)
            )

            Text(
                text = "Placa: " + (automovil?.placa ?: "NA"),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )

            Text(
                text = "Marca: " + (automovil?.marca ?: "NA"),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )

            Text(
                text = "Modelo: " + (automovil?.modelo ?: "NA"),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            Text(
                text = stringResource(id = R.string.label_tecnico_asignado),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 15.dp)
            )

            Text(
                text = (tecnico?.empleado?.persona?.nombres
                    ?: "NA") + " " + (tecnico?.empleado?.persona?.apellidos ?: "NA"),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )

            Text(
                text = "Codigo de empleado: " + (tecnico?.empleado?.id_empleado ?: "NA"),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            Text(
                text = stringResource(id = R.string.label_prob_declarado),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 15.dp)
            )

            Text(
                text = consultaUiState.problema ?: "NA",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            Spacer(modifier = Modifier.padding(12.dp))

            Button(
                onClick = { onRegister() },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = stringResource(id = R.string.registrar_boton))
            }

            Spacer(modifier = Modifier.padding(15.dp))
        }

        if (consultaUiState.flag_ok_registrar_consulta) {
            AlertDialogOK(
                titulo = stringResource(id = R.string.registro_consulta_title),
                contenido = stringResource(id = R.string.registro_consultaok_content),
                onAccept = onAccept
            )
        }

        if (consultaUiState.flag_error_registrar_consulta) {
            AlertDialogError(
                titulo = stringResource(id = R.string.registro_consulta_title),
                contenido = stringResource(id = R.string.registro_consultaerror_content),
                onCancel = onCancel,
                onConfirm = onRetry
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ResumenConsultaLightPreview() {
    SIAMOTheme(darkTheme = false) {
        ResumenConsulta(
            consultaUiState = ConsultaUiState(
                cliente = Cliente(
                    id_persona = 1,
                    persona = com.example.siamo.model.Persona(
                        nombres = "Juan",
                        apellidos = "Perez",
                        tipo_doc = 1,
                        num_doc = "12345678"
                    )
                ),
                automovil = Automovil(
                    placa = "ABC-123",
                    marca = "Toyota",
                    modelo = "Corolla"
                ),
                tecnico_asignado = Tecnico(
                    id_tecnico = 1,
                    empleado = Empleado(
                        id_empleado = 1,
                        persona = com.example.siamo.model.Persona(
                            nombres = "Juan",
                            apellidos = "Perez"
                        )
                    )
                )
            ),
            onRegister = {},
            onAccept = {},
            onRetry = {},
            onCancel = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ResumenConsultaDarkPreview() {
    SIAMOTheme(darkTheme = true) {
        ResumenConsulta(
            consultaUiState = ConsultaUiState(
                cliente = Cliente(
                    id_persona = 1,
                    persona = com.example.siamo.model.Persona(
                        nombres = "Juan",
                        apellidos = "Perez",
                        tipo_doc = 1,
                        num_doc = "12345678"
                    )
                ),
                automovil = Automovil(
                    placa = "ABC-123",
                    marca = "Toyota",
                    modelo = "Corolla"
                ),
                tecnico_asignado = Tecnico(
                    id_tecnico = 1,
                    empleado = Empleado(
                        id_empleado = 1,
                        persona = com.example.siamo.model.Persona(
                            nombres = "Juan",
                            apellidos = "Perez"
                        )
                    )
                )
            ),
            onRegister = {},
            onAccept = {},
            onRetry = {},
            onCancel = {}
        )
    }
}