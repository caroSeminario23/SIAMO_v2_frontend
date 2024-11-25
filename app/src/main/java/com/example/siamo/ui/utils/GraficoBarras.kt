package com.example.siamo.ui.utils

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.data.graficos.Datos
import com.example.siamo.ui.theme.SIAMOTheme
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer

@Composable
fun GraficoBarras(
    modifier: Modifier = Modifier,
    datos: List<Datos> = listOf(
        Datos("label1", 10f),
        Datos("label2", 20f),
        Datos("label3", 30f)
    )
) {
//    val datos: List<Datos> = listOf(
//        Datos("Enero", 10f),
//        Datos("Febrero", 20f),
//        Datos("Marzo", 30f)
//    )

    var barras = ArrayList<BarChartData.Bar>()
    datos.mapIndexed { index, datos ->
        barras.add(
            BarChartData.Bar(
                label = datos.etiqueta,
                value = datos.valor,
                color = MaterialTheme.colorScheme.primary
            )
        )
    }

    BarChart(
        barChartData = BarChartData(
            bars = barras
        ),
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 80.dp)
            .height(300.dp),
        labelDrawer = SimpleValueDrawer(
            drawLocation = SimpleValueDrawer.DrawLocation.XAxis
        )
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun GraficoBarrasLightPreview() {
    SIAMOTheme (darkTheme = false) { GraficoBarras() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun GraficoBarrasDarkPreview() {
    SIAMOTheme (darkTheme = true) { GraficoBarras() }
}