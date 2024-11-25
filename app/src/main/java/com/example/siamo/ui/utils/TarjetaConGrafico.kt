package com.example.siamo.ui.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.data.graficos.Datos
import com.example.siamo.ui.theme.SIAMOTheme

@Composable
fun TarjetaConGrafico(
    modifier: Modifier = Modifier,
    titulo: String = stringResource(id = R.string.grafico_tecnico_ost_asignadas_vs_apoyo),
    nombre_mes: String = stringResource(id = R.string.mes_enero),
    tipoGrafico: Int = 1, //1: Barras, 2: Circular
    datos: List<Datos> = listOf(
        Datos("label1", 10f),
        Datos("label2", 20f),
        Datos("label3", 30f),
        Datos("label3", 30f)
    )
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp),
    ) {
        if (tipoGrafico == 1) {
            GraficoBarras(datos = datos)
        } else {
            GraficoCircular(datos = datos)
        }
        Text(
            text = titulo,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 4.dp)
        )
        Text(
            text = stringResource(id = R.string.grafico_mes) + " " + nombre_mes + " - " + stringResource(id = R.string.grafico_anio),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 2.dp, bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun TarjetaConGraficoLightPreview() {
    SIAMOTheme (darkTheme = false) { TarjetaConGrafico() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun TarjetaConGraficoDarkPreview() {
    SIAMOTheme (darkTheme = true) { TarjetaConGrafico() }
}