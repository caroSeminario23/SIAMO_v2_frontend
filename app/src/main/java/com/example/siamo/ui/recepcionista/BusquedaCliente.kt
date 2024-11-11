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
fun BusquedaCliente(
    modifier: Modifier = Modifier,
) {
    Scaffold (
        topBar = { TopBar(tituloPagina = stringResource(R.string.topbar_opcion2), modo = "Normal") },
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
                value =  stringResource(id = R.string.ejemplo),
                onValueChange = {},
                label = {
                    Text(text = stringResource(id = R.string.campo_doc_identidad))
                },
                modifier = Modifier.fillMaxWidth().padding(15.dp)
            )

            Spacer(modifier = Modifier.padding(20.dp))

            Button(
                onClick = { },
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
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun BusquedaClienteLightPreview() {
    SIAMOTheme (darkTheme = false) { BusquedaCliente() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun BusquedaClienteDarkPreview() {
    SIAMOTheme (darkTheme = true) { BusquedaCliente() }
}