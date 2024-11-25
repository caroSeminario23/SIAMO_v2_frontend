package com.example.siamo.ui.inicio

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.model.Empleado
import com.example.siamo.ui.navigation.NavigationDestination
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.AlertDialogError
import com.example.siamo.ui.utils.AlertDialogOK

object LoginDestination : NavigationDestination {
    override val route = "login"
    override val titleRes = R.string.app_name
}

@Composable
fun Login(
    inicioUiState: InicioUiState,
    modifier: Modifier = Modifier,
    onLogin: (Int, String) -> Unit = { _, _ -> },
    onAccept: () -> Unit = {},
    onCancel: () -> Unit = {},
    onRetry: () -> Unit = {}
) {
    var codigoEmpleado by rememberSaveable { mutableStateOf("") }
    var contrasenia by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false)}
    Scaffold { paddingValues ->
        Column(
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
                value = codigoEmpleado,
                onValueChange = { codigoEmpleado = it },
                label = {
                    Text(text = stringResource(id = R.string.inicio_cod_empleado))
                }
            )

            Spacer(modifier = Modifier.padding(12.dp))

            OutlinedTextField(
                value = contrasenia,
                onValueChange = { contrasenia = it },
                label = { Text(text = stringResource(id = R.string.inicio_contrasenia)) },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                }
            )

            Spacer(modifier = Modifier.padding(40.dp))

            Button(
                onClick = {
                    onLogin(codigoEmpleado.toInt(), contrasenia)
                },
                modifier = Modifier
            ) {
                Text(text = stringResource(id = R.string.inicio_boton))
            }
        }
    }

    if (inicioUiState.flag_ok_login) {
        AlertDialogOK(
            titulo = stringResource(R.string.inicio_de_sesion_emerge_title),
            contenido = stringResource(R.string.ha_iniciado_exitosamente_su_sesion_emerge_message),
            onAccept = onAccept
        )
    }

    if (inicioUiState.flag_error_login) {
        AlertDialogError(
            titulo = stringResource(R.string.inicio_de_sesion_emerge_title),
            contenido = "El usuario o contraseña ingresada es incorrecta",
            onCancel = onCancel,
            onConfirm = onRetry
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun LoginLightPreview() {
    SIAMOTheme(darkTheme = false) {
        Login(
            inicioUiState = InicioUiState(),
            onAccept = {},
            onCancel = {},
            onRetry = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun LoginDarkPreview() {
    SIAMOTheme(darkTheme = true) {
        Login(
            inicioUiState = InicioUiState(),
            onAccept = {},
            onCancel = {},
            onRetry = {}
        )
    }
}