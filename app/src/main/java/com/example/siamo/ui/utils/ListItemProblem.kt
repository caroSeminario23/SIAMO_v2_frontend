package com.example.siamo.ui.utils


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.ui.theme.SIAMOTheme
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.ui.graphics.Color

@Composable
fun ListItemProblem(
    modifier: Modifier = Modifier,
    textoPrincipal: String = "Texto Principal",
    solucion: String? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        // Texto principal (problema)
        Text(
            text = textoPrincipal,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.weight(1f))


            if (solucion.isNullOrEmpty()) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(2.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                        .background(Color.Transparent),
                    tint = MaterialTheme.colorScheme.primary

                )
            }
        }

        Text(
            text = solucion ?: "Solución no definida",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )

        // Divider debajo de la solución
        Divider(modifier = Modifier.padding(vertical = 8.dp))
    }
}



@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ListItemCustomeLightPreview() {
    SIAMOTheme (darkTheme = false) { ListItemProblem() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ListItemCustomeDarkPreview() {
    SIAMOTheme (darkTheme = true) { ListItemProblem() }
}