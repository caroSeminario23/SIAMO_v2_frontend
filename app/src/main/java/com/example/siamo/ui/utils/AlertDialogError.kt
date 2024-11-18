package com.example.siamo.ui.utils

import androidx.compose.foundation.background
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.siamo.R
import com.example.siamo.ui.theme.SIAMOTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogError (
    modifier: Modifier = Modifier,
    titulo: String = "<Título de la alerta>",
    contenido : String = "<Contenido de la alerta>",
    buttomMessage: String = stringResource(id = R.string.alerta_volver_a_intentar),
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.errorContainer,
        title = { Text(text = titulo, color = MaterialTheme.colorScheme.onSurface) },
        text = { Text(
            text = contenido,
            color = MaterialTheme.colorScheme.onErrorContainer) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(
                    text = buttomMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text(
                    text = stringResource(id = R.string.alerta_cancelar),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun AlertDialogErrorLightPreview() {
    SIAMOTheme (darkTheme = false) { AlertDialogError() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun AlertDialogErrorDarkPreview() {
    SIAMOTheme (darkTheme = true) { AlertDialogError() }
}