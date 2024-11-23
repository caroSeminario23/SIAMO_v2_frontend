package com.example.siamo.ui.tecnico


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.NavigationBarTecnico
import com.example.siamo.ui.utils.TopBar
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.siamo.ui.inspeccion.RegistroSolucionViewModel


@Composable
fun registroSolucion(
    navController: NavHostController,
    problemaID: Int,
    modifier: Modifier = Modifier,
    registroSolucionviewModel: RegistroSolucionViewModel
) {

    // Estado del UI desde el ViewModel
    val uiState = registroSolucionviewModel.uiState.collectAsState().value

    Scaffold(
        topBar = { TopBar(tituloPagina = stringResource(R.string.topbar_opcion11), modo = "Retroceder") },
        bottomBar = { NavigationBarTecnico(opcionSeleccionada = 2) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Título
            Text(
                text = stringResource(id = R.string.inspeccion_registro_solucion),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            // Detalle del problema
            Text(
                text = stringResource(id = R.string.inspeccion_registro, problemaID),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .padding(start = 40.dp, end = 40.dp, bottom = 40.dp)
            )

            // Campo para el detalle del problema
            OutlinedTextField(
                value = uiState.detalleProblema,
                onValueChange = { registroSolucionviewModel.actualizarDetalleProblema(it) },
                label = {
                    Text(text = stringResource(id = R.string.campo_detalle_problema))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .heightIn(min = 120.dp)
            )

            // Campo para la solución propuesta
            OutlinedTextField(
                value = uiState.descripcionSolucion,
                onValueChange = { registroSolucionviewModel.actualizarDescripcionSolucion(it) },
                label = {
                    Text(text = stringResource(id = R.string.campo_solucion_propuesta))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .heightIn(min = 120.dp)
            )

            Spacer(modifier = Modifier.padding(12.dp))

            // Botón para registrar
            Button(
                onClick = { registroSolucionviewModel.registrarSolucionYActualizarProblema(problemaID)
                             navController.popBackStack() },
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

/*
@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegistroSolucionLightPreview() {
    SIAMOTheme (darkTheme = false) { registroSolucion() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegistroSolucionDarkPreview() {
    SIAMOTheme (darkTheme = true) { registroSolucion("problema 1") }
}
*/