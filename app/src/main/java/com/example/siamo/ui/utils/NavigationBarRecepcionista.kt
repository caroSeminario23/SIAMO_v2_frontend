package com.example.siamo.ui.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.siamo.R
import com.example.siamo.ui.theme.SIAMOTheme

@Composable
fun NavigationBarRecepcionista(
    opcionSeleccionada: Int = 1,
    onHome: () -> Unit = {},
    onRegister : () -> Unit = {},
    onSearch : () -> Unit = {},
    onSettings : () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var opcion1 = false
    var opcion2 = false
    var opcion3 = false
    var opcion4 = false

    when (opcionSeleccionada) {
        1 -> opcion1 = true
        2 -> opcion2 = true
        3 -> opcion3 = true
        4 -> opcion4 = true
    }

    NavigationBar (modifier = modifier.fillMaxWidth()) {
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Principal") },
            label = {
                Text(text = stringResource(R.string.navbar_opcion1),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ) },
            selected = opcion1,
            onClick = { onHome() }
        )

        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.MailOutline, contentDescription = "Registrar OST") },
            label = {
                Text(text = stringResource(R.string.navbar_opcion2),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant) },
            selected = opcion2,
            onClick = { onRegister() }
        )

        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar OST") },
            label = {
                Text(text = stringResource(R.string.navbar_opcion3),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant) },
            selected = opcion3,
            onClick = { onSearch() }
        )

        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "Ajustes") },
            label = {
                Text(text = stringResource(R.string.navbar_opcion4),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant) },
            selected = opcion4,
            onClick = { onSettings() }
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun NavigationBarRecepcionistaLightPreview() {
    SIAMOTheme (darkTheme = false) { NavigationBarRecepcionista() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun NavigationBarRecepcionistaDarkPreview() {
    SIAMOTheme (darkTheme = true) { NavigationBarRecepcionista() }
}