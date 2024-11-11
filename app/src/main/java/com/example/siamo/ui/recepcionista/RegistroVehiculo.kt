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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.NavigationBarRecepcionista
import com.example.siamo.ui.utils.TopBar

@Composable
fun RegistroVehiculo(
    modifier: Modifier = Modifier,
) {
    Scaffold (
        topBar = { TopBar(tituloPagina = stringResource(R.string.topbar_opcion6), modo = "Retroceder", modifier = Modifier.padding(bottom = 40.dp)) },
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
                value =  stringResource(id = R.string.ejemplo),
                onValueChange = {},
                label = {
                    Text(text = stringResource(id = R.string.campo_n_placa))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            OutlinedTextField(
                value =  stringResource(id = R.string.ejemplo),
                onValueChange = {},
                label = {
                    Text(text = stringResource(id = R.string.campo_marca))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            OutlinedTextField(
                value =  stringResource(id = R.string.ejemplo),
                onValueChange = {},
                label = {
                    Text(text = stringResource(id = R.string.campo_modelo))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            Spacer(modifier = Modifier.padding(12.dp))

            Button(
                onClick = { },
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
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegistroVehiculoLightPreview() {
    SIAMOTheme (darkTheme = false) { RegistroVehiculo() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegistroVehiculoDarkPreview() {
    SIAMOTheme (darkTheme = true) { RegistroVehiculo() }
}