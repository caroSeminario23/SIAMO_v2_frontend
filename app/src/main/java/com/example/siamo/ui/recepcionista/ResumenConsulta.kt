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
import com.example.siamo.data.consulta_cliente
import com.example.siamo.data.consulta_tecnico
import com.example.siamo.data.consulta_vehiculo
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.NavigationBarRecepcionista
import com.example.siamo.ui.utils.TopBar

@Composable
fun ResumenConsulta(
    modifier: Modifier = Modifier
) {
    val cliente = consulta_cliente(
        nombres = "Juan Álvaro",
        apellidos = "Perez Bustamante",
        tipoDocumento = 1,
        numDocumento = "12345678"
    )

    val vehiculo = consulta_vehiculo(
        placa = "ABC123",
        marca = "Toyota",
        modelo = "Corolla",
    )

    val tecnico = consulta_tecnico(
        nombres = "Pedro",
        apellidos = "Gonzales",
        codEmpleado = 123
    )

    val probDeclarado = "No enciende la luz del tablero"

    var nombreDocumento = "DNI"

    when (cliente.tipoDocumento) {
        1 -> nombreDocumento = "DNI"
        2 -> nombreDocumento = "Carnet de Extranjeria"
    }

    Scaffold (
        topBar = { TopBar(tituloPagina = stringResource(R.string.topbar_opcion9), modo = "Retroceder", modifier = Modifier.padding(bottom = 40.dp)) },
        bottomBar = { NavigationBarRecepcionista(opcionSeleccionada = 2) }
    ) { paddingValues ->
        Column (
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
                text = cliente.nombres + " " + cliente.apellidos,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )

            Text(
                text = nombreDocumento + ": " + cliente.numDocumento,
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
                text = "Placa: " + vehiculo.placa,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )

            Text(
                text = "Placa: " + vehiculo.marca,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )

            Text(
                text = "Placa: " + vehiculo.modelo,
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
                text = tecnico.nombres + " " + tecnico.apellidos,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )

            Text(
                text = "Codigo de empleado: " + tecnico.codEmpleado,
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
                text = probDeclarado,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
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
fun ResumenConsultaLightPreview() {
    SIAMOTheme (darkTheme = false) { ResumenConsulta() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ResumenConsultaDarkPreview() {
    SIAMOTheme (darkTheme = true) { ResumenConsulta() }
}