package com.example.siamo.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.siamo.R
import com.example.siamo.ui.theme.SIAMOTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogOK (
    modifier: Modifier = Modifier,
    titulo: String = "<Título de la alerta>",
    contenido : String = "<Contenido de la alerta>",
    onDismiss: () -> Unit = {},
    onConfirmClick: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = titulo) },
        text = { Text(text = contenido) },
        confirmButton = {
            Text(
                text = stringResource(id = R.string.alerta_aceptar),
                modifier = Modifier.clickable { onConfirmClick() }
            )
        }
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun AlertDialogOKLightPreview() {
    SIAMOTheme (darkTheme = false) { AlertDialogOK() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun AlertDialogOKDarkPreview() {
    SIAMOTheme (darkTheme = true) { AlertDialogOK() }
}