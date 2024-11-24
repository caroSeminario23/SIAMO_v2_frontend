package com.example.siamo.ui.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.ui.theme.SIAMOTheme

@Composable
fun ListItemCustome(
    modifier: Modifier = Modifier,
    textoPrincipal: String = "Texto Principal",
    textoSecundario: String = "TextoSec",
    seleccionado: Boolean = true,
    onCheckedChange: (Boolean) -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp, top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = textoPrincipal,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(8f)
        )

        Text(
            text = textoSecundario,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.weight(2f)
        )

        Checkbox(
            checked = seleccionado,
            onCheckedChange = { onCheckedChange(it) },
            colors = CheckboxDefaults.colors(
                checkmarkColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ListItemCustomeLightPreview() {
    SIAMOTheme (darkTheme = false) { ListItemCustome(onCheckedChange = {}) }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ListItemCustomeDarkPreview() {
    SIAMOTheme (darkTheme = true) { ListItemCustome(onCheckedChange = {}) }
}