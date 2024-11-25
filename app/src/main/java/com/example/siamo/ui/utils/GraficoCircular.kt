package com.example.siamo.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

    Column {
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
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            datos.forEachIndexed { index, datos ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(slices[index].color)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    BasicText(text = datos.etiqueta)
                }
            }
        }
    }
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