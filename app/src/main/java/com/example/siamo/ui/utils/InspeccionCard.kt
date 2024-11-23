package com.example.siamo.ui.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.siamo.ui.theme.SIAMOTheme


@Composable
fun InspectionCard(
    cliente: String = "<Nombres> <Apellidos",
    vehiculo: String = "<Marca> <Modelo>",
    placa: String = "<Número de placa>",
    problema: String = "<Descripción del problema>",
    consulta: String = "<Código de consulta>",
    navController: NavHostController
) {
    val colorSinClick = MaterialTheme.colorScheme.surface
    val colorConClick = MaterialTheme.colorScheme.outlineVariant
    val cardColor = rememberSaveable { mutableStateOf(colorSinClick) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                cardColor.value = if (cardColor.value == colorSinClick) {
                    colorConClick
                } else {
                    colorSinClick
                }
            },
        colors = CardDefaults.cardColors(containerColor = cardColor.value)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
           
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(MaterialTheme.colorScheme.primary, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "A",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Inspección pendiente",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = consulta,  // Aquí se pasa el valor de la consulta
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Título de la tarjeta
            Text(
                text = "Inspección de vehículo",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            // Subtítulo: Se solicita apoyo
            Text(
                text = "Se solicita su apoyo en recepción",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Supporting Text: Detalles del cliente y vehículo
            Column(
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    text = "- Cliente: $cliente",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "- Vehículo: $vehiculo",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "- N° Placa: $placa",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "- Problema descrito: $problema",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Espacio entre contenido y botón
            Spacer(modifier = Modifier.height(16.dp))

            // Botón "Resolver"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { navController.navigate("IdentificacionProblemas/$consulta") },
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Text(text = "Resolver")
                }
            }
        }
    }
}








