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
import androidx.lifecycle.ViewModel
import com.example.siamo.R
import com.example.siamo.model.Cliente
import com.example.siamo.model.Persona
import com.example.siamo.model.Response
import com.example.siamo.ui.consulta.ConsultaUiState
import com.example.siamo.ui.consulta.ConsultaViewModel
import com.example.siamo.ui.navigation.NavigationDestination
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.AlertDialogError
import com.example.siamo.ui.utils.AlertDialogOK
import com.example.siamo.ui.utils.NavigationBarRecepcionista
import com.example.siamo.ui.utils.TopBar

object BusquedaClienteDestination : NavigationDestination {
    override val route = "busqueda_cliente"
    override val titleRes = R.string.topbar_opcion2
}

@Composable
fun BusquedaCliente(
    consultaUiState: ConsultaUiState,
    getCliente: (String) -> Unit,
    onAccept: () -> Unit,
    onDismiss: () -> Unit = {},
    onCancel: () -> Unit = {},
    onRegistrar: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var documentLabel by rememberSaveable { mutableStateOf("") }

    Scaffold (
        topBar = { TopBar(tituloPagina = stringResource(R.string.topbar_opcion2), modo = "Retroceder") },
        bottomBar = { NavigationBarRecepcionista(opcionSeleccionada = 2) }
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.busqueda_cliente),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = modifier.padding(15.dp)
            )

            Spacer(modifier = Modifier.padding(6.dp))

            OutlinedTextField(
                value =  documentLabel,
                onValueChange = { documentLabel = it },
                label = {
                    Text(text = stringResource(id = R.string.campo_doc_identidad))
                },
                modifier = Modifier.fillMaxWidth().padding(15.dp)
            )

            Spacer(modifier = Modifier.padding(20.dp))

            Button(
                onClick = { getCliente(documentLabel) },
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

        if(consultaUiState.flag_ok_buscar_cliente) {
            AlertDialogOK(
                titulo = stringResource(id = R.string.alerta_busqueda_cliente_title),
                contenido = stringResource(id = R.string.alerta_busqueda_clienteok_text),
                onAccept = onAccept
            )
        }

        if(consultaUiState.flag_error_buscar_cliente) {
            AlertDialogError(
                titulo = stringResource(id = R.string.alerta_busqueda_cliente_title),
                contenido = stringResource(id = R.string.alerta_busqueda_clienteerror_text),
                buttomMessage = stringResource(id = R.string.registrar_boton),
                onConfirm = onRegistrar,
                onCancel = onCancel
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun BusquedaClienteLightPreview() {
    SIAMOTheme (darkTheme = false) { BusquedaCliente(
        consultaUiState = ConsultaUiState(),
        getCliente = {},
        onAccept = {}
    ) }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun BusquedaClienteDarkPreview() {
    SIAMOTheme (darkTheme = true) { BusquedaCliente(
        consultaUiState = ConsultaUiState(),
        getCliente = {},
        onAccept = {}
    ) }
}