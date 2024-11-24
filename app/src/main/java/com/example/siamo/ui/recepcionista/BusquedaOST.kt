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
import com.example.siamo.ui.ost.BuscarOstUiState
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.AlertDialogError
import com.example.siamo.ui.utils.AlertDialogOK
import com.example.siamo.ui.utils.NavigationBarRecepcionista
import com.example.siamo.ui.utils.TopBar

object BusquedaOSTNavigation : NavigationDestination {
    override val route = "busqueda_cliente"
    override val titleRes = R.string.topbar_opcion2
}

@Composable
fun BusquedaOST(
    buscarOstUiState: BuscarOstUiState,
    getOst: (Int) -> Unit,
    onAccept: () -> Unit,
    onDismiss: () -> Unit = {},
    onClose: () -> Unit = {},
    onCancel: () -> Unit = {},
    onConfirm: () -> Unit = {},
    onHomeNav: () -> Unit = {},
    onRegisterNav: () -> Unit = {},
    onSettingsNav: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var ostLabel by rememberSaveable { mutableStateOf("") }

    Scaffold (
        topBar = { TopBar(tituloPagina = stringResource(R.string.topbar_opcion2), modo = "Normal", onLeftIcon = onClose) },
        bottomBar = { NavigationBarRecepcionista(
            opcionSeleccionada = 3,
            onHome = onHomeNav,
            onRegister = onRegisterNav,
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
                text = stringResource(R.string.ingrese_nro_ost_label),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = modifier.padding(15.dp)
            )

            Spacer(modifier = Modifier.padding(6.dp))

            OutlinedTextField(
                value =  ostLabel,
                onValueChange = { ostLabel = it },
                label = {
                    Text(text = stringResource(R.string.codigo_ost_field))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )

            Spacer(modifier = Modifier.padding(20.dp))

            Button(
                onClick = { getOst(ostLabel.toInt()) },
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

        if(buscarOstUiState.flag_ok_buscar_ost) {
            AlertDialogOK(
                titulo = stringResource(id = R.string.alerta_busqueda_cliente_title),
                contenido = stringResource(id = R.string.alerta_busqueda_clienteok_text),
                onAccept = onAccept
            )
        }

        if(buscarOstUiState.flag_error_buscar_ost) {
            AlertDialogError(
                titulo = stringResource(id = R.string.alerta_busqueda_cliente_title),
                contenido = stringResource(id = R.string.alerta_busqueda_clienteerror_text),
                buttomMessage = stringResource(id = R.string.alerta_aceptar),
                onConfirm = onConfirm,
                onCancel = onCancel
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun BusquedaOSTLightPreview() {
    SIAMOTheme (darkTheme = false) { BusquedaOST(
        buscarOstUiState = BuscarOstUiState(),
        getOst = {},
        onAccept = {}
    ) }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun BusquedaOSTDarkPreview() {
    SIAMOTheme (darkTheme = true) { BusquedaOST(
        buscarOstUiState = BuscarOstUiState(),
        getOst = {},
        onAccept = {}
    ) }
}