package com.example.siamo.ui.recepcionista

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.data.home_notification
import com.example.siamo.ui.navigation.NavigationDestination
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.HomeNotification
import com.example.siamo.ui.utils.NavigationBarRecepcionista
import com.example.siamo.ui.utils.TopBar

object HomeRecepcionistaDestination: NavigationDestination {
    override val route = "home_recepcionista"
    override val titleRes = R.string.topbar_opcion1
}

@Composable
fun HomeRecepcionista(
    modifier: Modifier = Modifier,
    empleado: String = "<Nombres y apellidos del empleado>"
) {
    Scaffold (
        topBar = { TopBar(tituloPagina = stringResource(R.string.topbar_opcion1), modo = "Normal") },
        bottomBar = { NavigationBarRecepcionista(opcionSeleccionada = 1) }
    ) {
        paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(30.dp))

            Text(
                text = stringResource(id = R.string.home_saludo2),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = empleado,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Spacer(modifier = Modifier.padding(30.dp))

            LazyColumn {
                val notificaciones = listOf(
                    home_notification("Notificación 1", "Contenido de la notificación 1"),
                    home_notification("Notificación 2", "Contenido de la notificación 2"),
                    home_notification("Notificación 3", "Contenido de la notificación 3")
                )
                items(notificaciones.size) {index ->
                    val notificacion = notificaciones[index]
                    Box(
                        modifier = Modifier.padding(start = 18.dp, end = 18.dp, bottom = 12.dp)
                    ) {
                        HomeNotification(
                            titulo = notificacion.titulo,
                            contenido = notificacion.contenido
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(30.dp))

            Button(
                onClick = { },
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
                Text(text = stringResource(id = R.string.mas_detalle_boton))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun HomeRecepcionistaLightPreview() {
    SIAMOTheme (darkTheme = false) { HomeRecepcionista() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun HomeRecepcionistaDarkPreview() {
    SIAMOTheme (darkTheme = true) { HomeRecepcionista() }
}