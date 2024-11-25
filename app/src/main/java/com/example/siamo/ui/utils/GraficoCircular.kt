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
import com.github.tehras.charts.piechart.PieChart
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer

@Composable
fun GraficoCircular(
    modifier: Modifier = Modifier,
    datos: List<Datos> = listOf(
        Datos("label1", 10f),
        Datos("label2", 20f),
        Datos("label3", 30f)
    )
) {
    var colores = mutableListOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer
    )

    var slices = ArrayList<PieChartData.Slice>()
    datos.mapIndexed { index, datos ->
        slices.add(
            PieChartData.Slice(
            value = datos.valor,
            color = randomColor(colores)
            )
        )
    }

    PieChart(
        pieChartData = PieChartData(
            slices = slices
        ),
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 80.dp)
            .height(300.dp),
        sliceDrawer = SimpleSliceDrawer(
            sliceThickness = 50f
        )
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun GraficoCircularLightPreview() {
    SIAMOTheme (darkTheme = false) { GraficoCircular() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun GraficoCircularDarkPreview() {
    SIAMOTheme (darkTheme = true) { GraficoCircular() }
}