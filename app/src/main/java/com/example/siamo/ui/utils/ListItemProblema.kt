package com.example.siamo.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.siamo.R
import com.example.siamo.ui.theme.SIAMOTheme

@Composable
fun ListItemProblema(
    modifier: Modifier = Modifier,
    problema: String = "Problema",
    solucion: String = "Solución",
    onAddClick: (Int) -> Unit
) {
    val solucionVacia = solucion.isBlank() || solucion == stringResource(id = R.string.label_solucion_no_definida)  
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Row (
            modifier = modifier.fillMaxWidth()
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    .weight(5f)
            ) {
                Text(
                    text = problema,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = solucion,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
            }

            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(2.dp)
                    .background(Color.Transparent)
                    .then(
                        if (solucionVacia) {
                            Modifier.clickable { onAddClick(0)}
                        } else {
                            Modifier
                        }
                    ),
                    //.clickable { onAddClick(idProblema) },
                tint = MaterialTheme.colorScheme.primary.copy(
                    alpha = if (solucionVacia) 1f else 0.3f
                )
            )
        }

        Divider(
            modifier = Modifier
                .padding(top = 10.dp, start = 8.dp, end = 8.dp, bottom = 5.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ListItemProblemaLightPreview() {
    SIAMOTheme (darkTheme = false) { ListItemProblema(onAddClick = {}) }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ListItemProblemaDarkPreview() {
    SIAMOTheme (darkTheme = true) { ListItemProblema(onAddClick = {}) }
}