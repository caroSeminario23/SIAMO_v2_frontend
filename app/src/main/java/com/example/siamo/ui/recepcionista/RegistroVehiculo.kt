package com.example.siamo.ui.recepcionista

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
import com.example.siamo.model.Automovil
import com.example.siamo.ui.consulta.ConsultaUiState
import com.example.siamo.ui.navigation.NavigationDestination
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.AlertDialogError
import com.example.siamo.ui.utils.AlertDialogOK
import com.example.siamo.ui.utils.NavigationBarRecepcionista
import com.example.siamo.ui.utils.TopBar

object RegistroVehiculoDestination : NavigationDestination {
    override val route = "registro_vehiculo"
    override val titleRes = R.string.topbar_opcion6
}

@Composable
fun RegistroVehiculo(
    consultaUiState: ConsultaUiState,
    onAccept: () -> Unit,
    onCancel: () -> Unit = {},
    onRegister: (Automovil) -> Unit = {},
    onRetry: () -> Unit = {},
    buttonUp: () -> Unit = {},
    onHomeNav: () -> Unit = {},
    onSearchNav: () -> Unit = {},
    onSettingsNav: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var nro_placa by rememberSaveable { mutableStateOf("") }
    var marca by rememberSaveable { mutableStateOf("") }
    var modelo by rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopBar(
                tituloPagina = stringResource(R.string.topbar_opcion6),
                modo = "Retroceder",
                onLeftIcon = buttonUp,
                modifier = Modifier.padding(bottom = 40.dp)
            )
        },
        bottomBar = { NavigationBarRecepcionista(
            opcionSeleccionada = 2,
            onHome = onHomeNav,
            onSearch = onSearchNav,
            onSettings = onSettingsNav
        ) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.topbar_opcion6),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            Text(
                text = stringResource(id = R.string.registro_vehiculo),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .padding(bottom = 40.dp)
            )

            OutlinedTextField(
                value = nro_placa,
                onValueChange = { nro_placa = it },
                label = {
                    Text(text = stringResource(id = R.string.campo_n_placa))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            OutlinedTextField(
                value = marca,
                onValueChange = { marca = it },
                label = {
                    Text(text = stringResource(id = R.string.campo_marca))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            OutlinedTextField(
                value = modelo,
                onValueChange = { modelo = it },
                label = {
                    Text(text = stringResource(id = R.string.campo_modelo))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            Spacer(modifier = Modifier.padding(12.dp))

            Button(
                onClick = {
                    onRegister(
                        Automovil(
                            id_cliente = consultaUiState.cliente?.id_cliente,
                            placa = nro_placa,
                            marca = marca,
                            modelo = modelo
                        )
                    )
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

        if (consultaUiState.flag_error_registrar_automovil) {
            AlertDialogError(
                titulo = stringResource(id = R.string.alerta_registro_vehiculo_titulo),
                contenido = stringResource(id = R.string.alerta_registro_vehiculoerror_mensaje),
                onConfirm = onRetry,
                onCancel = onCancel
            )
        }

        if (consultaUiState.flag_ok_registrar_automovil) {
            AlertDialogOK(
                titulo = stringResource(id = R.string.alerta_registro_vehiculo_titulo),
                contenido = stringResource(id = R.string.alerta_registro_vehiculook_mensaje),
                onAccept = onAccept
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegistroVehiculoLightPreview() {
    SIAMOTheme(darkTheme = false) {
        RegistroVehiculo(
            consultaUiState = ConsultaUiState(),
            onAccept = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegistroVehiculoDarkPreview() {
    SIAMOTheme(darkTheme = true) {
        RegistroVehiculo(
            consultaUiState = ConsultaUiState(),
            onAccept = {}
        )
    }
}