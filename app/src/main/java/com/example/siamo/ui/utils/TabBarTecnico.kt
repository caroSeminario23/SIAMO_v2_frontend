package com.example.siamo.ui.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.siamo.R
import com.example.siamo.ui.theme.SIAMOTheme

@Composable
fun TabBarTecnico(
    opcionSeleccionada: Int = 0,
    onCarga: () -> Unit = {},
    modifier: Modifier = Modifier

) {
    // El índice de la opción seleccionada
    val selectedIndex = remember { opcionSeleccionada }

    // El TabRow actúa como la barra de pestañas
    TabRow(
        selectedTabIndex = selectedIndex,
        modifier = modifier.fillMaxWidth()
    ) {
        Tab(
            selected = selectedIndex == 0,
            onClick = {  },
            text = {
                Text(text = stringResource(R.string.tabbar_opcion1), style = MaterialTheme.typography.titleSmall.copy(fontSize = 12.sp))
            }
        )

        Tab(
            selected = selectedIndex == 1,
            onClick = { onCarga()},
            text = {
                Text(text = stringResource(R.string.tabbar_opcion2), style = MaterialTheme.typography.titleSmall.copy(fontSize = 12.sp))
            }
        )

        Tab(
            selected = selectedIndex == 2,
            onClick = { /* TODO: Acción para la opción 3 */ },
            text = {
                Text(text = stringResource(R.string.tabbar_opcion3), style = MaterialTheme.typography.titleSmall.copy(fontSize = 12.sp))
            }
        )

    }
}

