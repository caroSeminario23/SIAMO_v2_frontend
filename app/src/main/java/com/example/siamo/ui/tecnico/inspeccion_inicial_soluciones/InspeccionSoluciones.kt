package com.example.siamo.ui.tecnico.inspeccion_inicial_soluciones


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.ui.utils.NavigationBarTecnico
import com.example.siamo.ui.utils.TopBar
import com.example.siamo.ui.theme.SIAMOTheme
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.material3.CircularProgressIndicator
import com.example.siamo.ui.utils.ListItemProblem

@Composable
fun InspeccionInicial(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    inspeccionInicialViewModel: InspeccionInicialViewModel = viewModel()
) {
    // Observamos el estado del ViewModel
    val uiState by inspeccionInicialViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                tituloPagina = stringResource(R.string.topbar_opcion10),
                modo = "Retroceder"
            )
        },
        bottomBar = { NavigationBarTecnico(opcionSeleccionada = 2) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.ispeccion_problema_solocion),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Verificar si estamos cargando o si hay un error
            if (uiState.isLoading) {

                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            } else if (uiState.errorMessage != null) {
                // Mostrar mensaje de error si no se pudo obtener los datos
                Text(
                    text = uiState.errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                // Mostrar los problemas y soluciones
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    items(uiState.problemasConSoluciones ?: emptyList()) { problemaConSolucion ->
                        ListItemProblem(
                            textoPrincipal = problemaConSolucion.descripcionProblema,
                            solucion = problemaConSolucion.descripcionSolucion,
                            idProblema = problemaConSolucion.idProblema, // Pasar el idProblema
                            onAddClick = { id ->

                                navController.navigate("registroSolucion/$id")
                            }
                        )
                    }
                }
            }
        }
    }
}


    @Preview(showBackground = true, showSystemUi = false)
    @Composable
    fun InspeccionInicialLightPreview() {
        SIAMOTheme(darkTheme = false) { InspeccionInicial() }
    }

    @Preview(showBackground = true, showSystemUi = false)
    @Composable
    fun InspeccionInicialPreview() {
        SIAMOTheme(darkTheme = true) { InspeccionInicial() }
    }

