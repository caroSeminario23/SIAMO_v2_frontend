package com.example.siamo.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.ui.theme.SIAMOTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    tituloPagina: String = "<NOMBRE DE PÁGINA>",
    modo: String = "Normal"
) {
    val close = Icons.Default.Close
    val retroceder = Icons.Default.ArrowBack
    var iconoAdecuado: ImageVector = close

    when (modo) {
        "Normal" -> iconoAdecuado = close
        "Retroceder" -> iconoAdecuado = retroceder
    }

    Row (
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(16.dp)
    ) {
        // Imagen izquierda
        Icon(
            imageVector = iconoAdecuado,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = tituloPagina,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        // Imagen derecha
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun TopBarLightPreview() {
    SIAMOTheme (darkTheme = false) { TopBar() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun TopBarDarkPreview() {
    SIAMOTheme (darkTheme = true) { TopBar() }
}