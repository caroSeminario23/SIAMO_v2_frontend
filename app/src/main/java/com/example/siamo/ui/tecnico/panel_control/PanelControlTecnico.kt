package com.example.siamo.ui.tecnico.panel_control

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.data.graficos.Datos
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.NavigationBarTecnico
import com.example.siamo.ui.utils.ScrollBarSecundario
import com.example.siamo.ui.utils.TarjetaConGrafico
import com.example.siamo.ui.utils.TopBar

@Composable
fun PanelControlTecnico(
    modifier: Modifier = Modifier,
) {
    var mesSeleccionado by rememberSaveable { mutableStateOf(0) }
    var listaMeses = listOf(
        stringResource(R.string.mes_enero),
        stringResource(R.string.mes_febrero),
        stringResource(R.string.mes_marzo),
        stringResource(R.string.mes_abril),
        stringResource(R.string.mes_mayo),
        stringResource(R.string.mes_junio),
        stringResource(R.string.mes_julio),
        stringResource(R.string.mes_agosto),
        stringResource(R.string.mes_septiembre),
        stringResource(R.string.mes_octubre),
        stringResource(R.string.mes_noviembre),
        stringResource(R.string.mes_diciembre)
    )
    var nombreMes by rememberSaveable { mutableStateOf("") }
    Scaffold (
        topBar = {
            TopBar(
                tituloPagina = stringResource(id = R.string.topbar_panel_control),
                modo = "Retroceder" )
                 },
        bottomBar = { NavigationBarTecnico(opcionSeleccionada = 1) }
    ) {
        paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ScrollBarSecundario(
                    listaElementos = listaMeses,
                    indiceInicial = mesSeleccionado,
                    onElementoSeleccionado = { mesSeleccionado = it }
                )
            }

            item {
                when (mesSeleccionado) {
                    0 -> nombreMes = listaMeses[0]
                    1 -> nombreMes = listaMeses[1]
                    2 -> nombreMes = listaMeses[2]
                    3 -> nombreMes = listaMeses[3]
                    4 -> nombreMes = listaMeses[4]
                    5 -> nombreMes = listaMeses[5]
                    6 -> nombreMes = listaMeses[6]
                    7 -> nombreMes = listaMeses[7]
                    8 -> nombreMes = listaMeses[8]
                    9 -> nombreMes = listaMeses[9]
                    10 -> nombreMes = listaMeses[10]
                    11 -> nombreMes = listaMeses[11]
                }
                Column (
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Grafico N° OSTS ASIGNADAS VS DE APOYO
                    TarjetaConGrafico(
                        titulo = stringResource(R.string.grafico_tecnico_ost_asignadas_vs_apoyo),
                        nombre_mes = nombreMes,
                        tipoGrafico = 2,
                        datos = listOf(
                            Datos(stringResource(id = R.string.grafico_n_ost_asignadas), 10f),
                            Datos(stringResource(id = R.string.grafico_n_ost_apoyo), 20f)
                        )
                    )

                    Spacer(modifier = Modifier.padding(4.dp))

                    // Grafico ESTADO DE OSTS ASIGNADAS
                    TarjetaConGrafico(
                        titulo = stringResource(R.string.grafico_tecnico_estado_ost_asignadas),
                        nombre_mes = nombreMes,
                        tipoGrafico = 1,
                        datos = listOf(
                            Datos(stringResource(id = R.string.grafico_en_proceso), 10f),
                            Datos(stringResource(id = R.string.grafico_resueltas), 20f),
                            Datos(stringResource(id = R.string.grafico_canceladas), 30f),
                            Datos(stringResource(id = R.string.grafico_abandonadas), 40f)
                        )
                    )

                    Spacer(modifier = Modifier.padding(4.dp))

                    // Grafico ESTADO DE OSTS DE APOYO
                    TarjetaConGrafico(
                        titulo = stringResource(R.string.grafico_tecnico_estado_ost_apoyo),
                        nombre_mes = nombreMes,
                        tipoGrafico = 1,
                        datos = listOf(
                            Datos(stringResource(id = R.string.grafico_en_proceso), 10f),
                            Datos(stringResource(id = R.string.grafico_resueltas), 20f),
                            Datos(stringResource(id = R.string.grafico_canceladas), 30f),
                            Datos(stringResource(id = R.string.grafico_abandonadas), 40f)
                        )
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PanelControlTecnicoLightPreview() {
    SIAMOTheme (darkTheme = false) { PanelControlTecnico() }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PanelControlTecnicoDarkPreview() {
    SIAMOTheme (darkTheme = true) { PanelControlTecnico() }
}