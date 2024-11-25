package com.example.siamo.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        Datos("label3", 30f),
        Datos("label3", 30f)
    )
) {
    var colores = mutableListOf(
        MaterialTheme.colorScheme.onErrorContainer,
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.onSurfaceVariant,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer
    )

    var barras = ArrayList<BarChartData.Bar>()
    datos.mapIndexed { index, datos ->
//        barras.add(
//            BarChartData.Bar(
//                label = datos.etiqueta,
//                value = datos.valor,
//                color = randomColor(colores)
//            )
//        )
        datos.etiqueta?.let { etiqueta ->
            datos.valor?.let { valor ->
                barras.add(
                    BarChartData.Bar(
                        label = etiqueta,
                        value = valor,
                        color = randomColor(colores)
                    )
                )
            }
        }
    }

    Column {
        BarChart(
            barChartData = BarChartData(
                bars = barras
            ),
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 40.dp)
                .height(300.dp),
            labelDrawer = SimpleValueDrawer(
                drawLocation = SimpleValueDrawer.DrawLocation.XAxis
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            datos.forEachIndexed { index, datos ->
                datos.etiqueta?.let { etiqueta ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(3.dp)
                                //.fillMaxWidth()
                                .background(barras[index].color)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        BasicText(text = etiqueta)
                    }
                }
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Box(
//                        modifier = Modifier
//                            .size(16.dp)
//                            .background(barras[index].color)
//                    )
//                    Spacer(modifier = Modifier.width(4.dp))
//                    BasicText(text = datos.etiqueta)
//                }
            }
        }
    }
}

fun randomColor(colores: MutableList<Color>): Color {
    val randomIndex = (Math.random() * colores.size).toInt()
    val color = colores[randomIndex]
    colores.removeAt(randomIndex)
    return color
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