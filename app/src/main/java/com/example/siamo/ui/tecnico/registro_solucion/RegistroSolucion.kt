package com.example.siamo.ui.tecnico.registro_solucion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.ui.utils.NavigationBarTecnico
import com.example.siamo.ui.utils.TopBar
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.siamo.navigation.NavRoutes
import com.example.siamo.ui.tecnico.registro_ost.RegistroOstViewModel
import com.example.siamo.ui.theme.SIAMOTheme

@Composable
fun RegistroSolucion(
    navController: NavHostController,
    viewModel: RegistroSolucionViewModel,
    nombreProblema: String = "Problema no se ha guardado",
    modifier: Modifier = Modifier
    ) {
    val uiState by viewModel.uiState.collectAsState()
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

            Text(
                text = stringResource(id = R.string.inspeccion_registro_solucion),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            Text(
                text = stringResource(
                    id = R.string.inspeccion_registro,
                    uiState.problemaAGuardar?.problema?.descripcion ?: nombreProblema
                ),
                //stringResource(id = R.string.inspeccion_registro) + " " + (uiState.problemaAGuardar?.problema?.descripcion
                //    ?: nombreProblema ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Start,
                modifier = modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 40.dp, top = 20.dp)
            )

            OutlinedTextField(
                value =  uiState.detalleProblema,
                onValueChange = {
                    viewModel.actualizarDetalleProblema(it)
                },
                label = {
                    Text(text = stringResource(id = R.string.campo_detalle_problema))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .heightIn(min = 120.dp)
            )

            OutlinedTextField(
                value = uiState.solucionPropuesta,
                onValueChange = {
                    viewModel.actualizarSolucionPropuesta(it)
                },
                label = {
                    Text(text = stringResource(id = R.string.campo_solucion_propuesta))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .heightIn(min = 120.dp)
            )
            Spacer(modifier = Modifier.padding(12.dp))

            Button(
                onClick = {
                    viewModel.registrarSolucion()
                    navController.navigate(NavRoutes.InspeccionSolucinoes.route)
                },
                enabled = uiState.detalleProblema.isNotEmpty() && uiState.solucionPropuesta.isNotEmpty(),
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
fun RegistroSolucionLightPreview() {
    val navController = rememberNavController()
    val registroOstViewModel = RegistroOstViewModel()
    val viewModel = RegistroSolucionViewModel(registroOstViewModel)
    SIAMOTheme (darkTheme = false) {
        RegistroSolucion(
            viewModel = viewModel,
            navController = navController
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegistroSolucionDarkPreview() {
    val navController = rememberNavController()
    val registroOstViewModel = RegistroOstViewModel()
    val viewModel = RegistroSolucionViewModel(registroOstViewModel)
    SIAMOTheme (darkTheme = true) {
        RegistroSolucion(
            viewModel = viewModel,
            navController = navController
        )
    }
}
