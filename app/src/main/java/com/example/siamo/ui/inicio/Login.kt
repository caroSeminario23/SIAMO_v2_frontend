package com.example.siamo.ui.inicio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.ui.theme.SIAMOTheme

@Composable
fun Login(
    modifier: Modifier = Modifier
) {
    Scaffold { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.inicio_titulo),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = stringResource(id = R.string.inicio_indicacion),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Spacer(modifier = Modifier.padding(30.dp))

            OutlinedTextField(
                value =  stringResource(id = R.string.ejemplo),
                onValueChange = {},
                label = {
                    Text(text = stringResource(id = R.string.inicio_cod_empleado))
                }
            )

            Spacer(modifier = Modifier.padding(12.dp))

            OutlinedTextField(
                value = stringResource(id = R.string.ejemplo),
                onValueChange = {},
                label = { Text(text = stringResource(id = R.string.inicio_contrasenia)) }
            )

            Spacer(modifier = Modifier.padding(40.dp))

            Button(
                onClick = { },
                modifier = Modifier
            ) {
                Text(text = stringResource(id = R.string.inicio_boton))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun LoginLightPreview() {
    SIAMOTheme (darkTheme = false) { Login() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun LoginDarkPreview() {
    SIAMOTheme (darkTheme = true) { Login() }
}