package com.example.siamo.ui.tecnico

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.ui.utils.DividerSection
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.NavigationBarRecepcionista
import com.example.siamo.ui.utils.TopBar
import com.example.siamo.ui.utils.AlertDialogError
import com.example.siamo.ui.utils.AlertDialogOK



@Composable
fun RegistroEstadoVehiculo(
    ingresoAutoUiState:IngresoAutoUiState,
    onCancel: () -> Unit = {},
    onRetry: () -> Unit = {},
    onAccept: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val estadoCarroceria = rememberSaveable { mutableStateOf("") }
    val estadoNeumaticos = rememberSaveable { mutableStateOf("") }
    val estadoMotor = rememberSaveable { mutableStateOf("") }
    val estadoFrenos = rememberSaveable{ mutableStateOf("") }
    val fechaRecogida = rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBar(
                tituloPagina = stringResource(R.string.topbar_opcion12),
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.titulo_registro_estado_vehiculo),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            Text(
                text = stringResource(id = R.string.subtitulo_registro_estado_vehiculo),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(bottom = 40.dp)
            )

            // Campos de texto
            OutlinedTextField(
                value = estadoCarroceria.value ,
                onValueChange = { estadoCarroceria.value = it },
                label = { Text(text = stringResource(id = R.string.campo_estado_carrocería)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            OutlinedTextField(
                value = estadoNeumaticos.value,
                onValueChange = { estadoNeumaticos.value = it },
                label = { Text(text = stringResource(id = R.string.campo_estado_neumáticos)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            OutlinedTextField(
                value =estadoMotor.value,
                onValueChange = { estadoMotor.value = it },
                label = { Text(text = stringResource(id = R.string.campo_estado_motor)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            OutlinedTextField(
                value = estadoFrenos.value,
                onValueChange = { estadoFrenos.value = it },
                label = { Text(text = stringResource(id = R.string.campo_estado_frenos)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            DividerSection(
                nombreSeccion = stringResource(id = R.string.subtitulo_salida_vehiculo)
            )

            OutlinedTextField(
                value = fechaRecogida.value,
                onValueChange = { fechaRecogida.value = it },
                label = { Text(text = stringResource(id = R.string.campo_fecha_aprox_recogida)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            Spacer(modifier = Modifier.padding(1.dp))

            Button(
                onClick = {

                },
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

        if (ingresoAutoUiState.flag_error_registrar) {
            AlertDialogError(
                titulo = stringResource(id = R.string.alerta_registro_estado_vehiculo_title),
                contenido = stringResource(id = R.string.alerta_registro_estado_vehiculoerror_text),
                onConfirm = onRetry,
                onCancel = onCancel
            )

        }

        if (ingresoAutoUiState.flag_ok_registrar) {
            AlertDialogOK(
                titulo = stringResource(id = R.string.alerta_registro_estado_vehiculo_title),
                contenido = stringResource(id = R.string.alerta_registro_estado_vehiculook_text),
                onAccept = onAccept
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegistroEstadoVehiculoLightPreview() {
    SIAMOTheme (darkTheme = false) { RegistroEstadoVehiculo(ingresoAutoUiState=IngresoAutoUiState(), onAccept = {}) }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegistroEstadoVehiculoDarkPreview() {
    SIAMOTheme (darkTheme = true) { RegistroEstadoVehiculo(ingresoAutoUiState=IngresoAutoUiState(), onAccept = {}) }
}