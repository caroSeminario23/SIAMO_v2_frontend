package com.example.siamo.ui.inicio

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.ui.navigation.NavigationDestination
import com.example.siamo.ui.theme.SIAMOTheme

object SiamoWelcomeDestination: NavigationDestination {
    override val route = "welcome"
    override val titleRes = R.string.app_name
}

@Composable
fun SiamoWelcomeScreen(
    modifier: Modifier = Modifier,
    onStart: () -> Unit = {},
) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Fondo
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )
            }

            // Contenido: logo, texto y botón
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                // Columna central para imagen y texto
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.siamo_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(295.dp, 304.dp),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = stringResource(id = R.string.nombre_detallado),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.tertiary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(horizontal = 24.dp)
                    )
                }

                // Botón en la parte inferior
                Button(
                    onClick = { onStart() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 50.dp, start = 32.dp, end = 32.dp)
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.bienvenida),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun SiamoAppLightPreview() {
    SIAMOTheme (darkTheme = false) { SiamoWelcomeScreen() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun SiamoAppDarkPreview() {
    SIAMOTheme (darkTheme = true) { SiamoWelcomeScreen() }
}