package com.example.siamo.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
    boton_error: String = stringResource(id = R.string.alerta_cancelar),
    onDismiss: () -> Unit = {},
    onConfirmClick: () -> Unit = {},
    onDismissClick: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = titulo) },
        text = { Text(
            text = contenido,
            color = MaterialTheme.colorScheme.onErrorContainer) },
        confirmButton = {
            Text(
                text = stringResource(id = R.string.alerta_volver_a_intentar),
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.clickable { onConfirmClick() }
            )
        },
        dismissButton = {
            Text(
                text = boton_error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.clickable { onDismissClick() }
            )
        },
        containerColor = MaterialTheme.colorScheme.errorContainer
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