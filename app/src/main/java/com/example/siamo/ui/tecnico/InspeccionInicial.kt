package com.example.siamo.ui.tecnico


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
import com.example.siamo.data.bitacora
import com.example.siamo.ui.utils.ListItemProblem

@Composable
fun InspeccionInicial(
    modifier: Modifier = Modifier
) {
    val listaBitacora = listOf(
        bitacora("Problema 1", "Solucion 1"),
        bitacora("Problema 2", "Solucion 2"),
        bitacora("Problema  3", "Solucion 3"),
        bitacora("Problema  4", "Solucion 4"),
        bitacora("Problema 5", "Solucion 5"),
        bitacora("Problema 6", "Solucion 6"),
        bitacora("Problema 7", "Solucion 7"),
        bitacora("Problema 8", null),
        bitacora("Problema  9", "Solucion 9"),
        bitacora("Problema 10", "Solucion 10")
    )

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

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                items(listaBitacora) { repuesto ->
                    ListItemProblem(
                        textoPrincipal = repuesto.problema,
                        solucion = repuesto.solucion
                    )
                    
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

