package com.example.siamo.ui.recepcionista

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.ui.consulta.ConsultaUiState
import com.example.siamo.ui.navigation.NavigationDestination
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.AlertDialogError
import com.example.siamo.ui.utils.AlertDialogOK
import com.example.siamo.ui.utils.NavigationBarRecepcionista
import com.example.siamo.ui.utils.TopBar

object BusquedaVehiculoDestination: NavigationDestination {
    override val route = "busqueda_vehiculo"
    override val titleRes = R.string.topbar_opcion5
}

@Composable
fun BusquedaVehiculo(
    consultaUiState: ConsultaUiState,
    onAccept: () -> Unit,
    onCancel: () -> Unit = {},
    onRegister: () -> Unit = {},
    onSearch: (String) -> Unit = {},
    buttonUp: () -> Unit = {},
    onHomeNav: () -> Unit = {},
    onSearchNav: () -> Unit = {},
    onSettingsNav: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var documetLabel by rememberSaveable { mutableStateOf("") }
    Scaffold (
        topBar = { TopBar(tituloPagina = stringResource(R.string.topbar_opcion5), modo = "Retroceder", onLeftIcon = buttonUp) },
        bottomBar = { NavigationBarRecepcionista(
            opcionSeleccionada = 2,
            onHome = onHomeNav,
            onSearch = onSearchNav,
            onSettings = onSettingsNav
        ) }
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.busqueda_vehiculo),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = modifier.padding(15.dp)
            )

            Spacer(modifier = Modifier.padding(6.dp))

            OutlinedTextField(
                value =  documetLabel,
                onValueChange = {documetLabel = it},
                label = {
                    Text(text = stringResource(id = R.string.campo_n_placa))
                },
                modifier = Modifier.fillMaxWidth().padding(15.dp)
            )

            Spacer(modifier = Modifier.padding(20.dp))

            Button(
                onClick = {
                    onSearch(documetLabel)
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 15.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
                Text(text = stringResource(id = R.string.buscar_boton))
            }
        }

        if(consultaUiState.flag_error_buscar_automovil) {
            AlertDialogError(
                onDismiss = { onCancel() },
                titulo = stringResource(id = R.string.alerta_busqueda_vehiculo_titulo),
                contenido = stringResource(id = R.string.alerta_busqueda_vehiculoerror_mensaje),
                buttomMessage = stringResource(id = R.string.registrar_boton),
                onConfirm = { onRegister() },
                onCancel = { onCancel() }
            )
        }

        if(consultaUiState.flag_ok_buscar_automovil) {
            AlertDialogOK(
                onAccept = { onAccept() },
                titulo = stringResource(id = R.string.alerta_busqueda_vehiculo_titulo),
                contenido = stringResource(id = R.string.alerta_busqueda_vehiculook_mensaje)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun BusquedaVehiculoLightPreview() {
    SIAMOTheme (darkTheme = false) { BusquedaVehiculo(
        consultaUiState = ConsultaUiState(flag_error_buscar_automovil = true),
        onAccept = {},
        onCancel = {},
        onRegister = {},
        onSearch = {}
    ) }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun BusquedaVehiculoDarkPreview() {
    SIAMOTheme (darkTheme = true) { BusquedaVehiculo(
        consultaUiState = ConsultaUiState(flag_ok_buscar_automovil = true),
        onAccept = {},
        onCancel = {},
        onRegister = {},
        onSearch = {}
    ) }
}