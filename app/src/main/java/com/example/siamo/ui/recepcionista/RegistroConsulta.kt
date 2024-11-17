package com.example.siamo.ui.recepcionista

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
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
import com.example.siamo.ui.utils.NavigationBarRecepcionista
import com.example.siamo.ui.utils.TopBar

object RegistroConsultaDestination : NavigationDestination {
    override val route = "registro_consulta"
    override val titleRes = R.string.topbar_opcion7
}

@Composable
fun RegistroConsulta(
    consultaUiState: ConsultaUiState,
    onNext: (String) -> Unit,
    onPrevius: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var problemaDeclarado by rememberSaveable { mutableStateOf("") }
    Scaffold (
        topBar = { TopBar(tituloPagina = stringResource(R.string.topbar_opcion7), modo = "Retroceder") },
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
                text = stringResource(id = R.string.registro_consulta),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = modifier.padding(15.dp)
            )

            Spacer(modifier = Modifier.padding(6.dp))

            OutlinedTextField(
                value =  problemaDeclarado,
                onValueChange = { problemaDeclarado = it },
                label = {
                    Text(text = stringResource(id = R.string.campo_problema_declarado))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .height(180.dp)
            )

            Spacer(modifier = Modifier.padding(20.dp))

            Button(
                onClick = { onNext(problemaDeclarado) },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 15.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.NavigateNext,
                    contentDescription = null
                )
                Text(text = stringResource(id = R.string.boton_siguiente))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegistroConsultaLightPreview() {
    SIAMOTheme (darkTheme = false) { RegistroConsulta(
        consultaUiState = ConsultaUiState(),
        onNext = {},
        onPrevius = {}
    ) }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun RegistroConsultaDarkPreview() {
    SIAMOTheme (darkTheme = true) { RegistroConsulta(
        consultaUiState = ConsultaUiState(),
        onNext = {},
        onPrevius = {}
    ) }
}