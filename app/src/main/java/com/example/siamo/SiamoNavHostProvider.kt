package com.example.siamo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.siamo.ui.inicio.SiamoWelcomeScreen
import com.example.siamo.ui.navigation.BuscarOstNavHost
import com.example.siamo.ui.navigation.InicioNavHost
import com.example.siamo.ui.navigation.RegisterNavHost
import com.example.siamo.ui.theme.SIAMOTheme

@Composable
fun SiamoNavHostProvider(
    modifier: Modifier = Modifier
) {
    var navController = rememberNavController()
    var navbarOption: Int by rememberSaveable { mutableStateOf(0) }
    var id_tecnico = rememberSaveable { mutableStateOf(0) }
    val onWelcome = { navbarOption = 0 }
    val onHome = { navbarOption = 1 }
    val onRegister = { navbarOption = 2 }
    val onSearch = { navbarOption = 3 }
    val onSettings = { navbarOption = 4 }

    when (navbarOption) {
        0 -> InicioNavHost(
            navController,
            id_tecnico = id_tecnico,
            onAccept = { navbarOption = 2 },
        )
//        1 -> if (id_tecnico.value == 0) {
//            HomeNavHostEspecialista(navController, Modifier.padding(paddingValues))
//        } else {
//            HomNavHostTecnico(id_tecnico.value, navController, Modifier.padding(paddingValues))
//        }
        2 -> if (id_tecnico.value == 0) {
            RegisterNavHost(
                navController,
                onClose = { navbarOption = 0 },
                onHome = onHome,
                onSearch = onSearch,
                onSettings = onSettings
            )
//            else {
////                GestionarOstNavHost(
////                    navController,
////                    id_tecnico = id_tecnico,
////                    onHome = onHome,
////                    onSearch = onSearch,
////                    onSettings = onSettings
////                )
//            }
        }
        3 -> if (id_tecnico.value == 0) {
            BuscarOstNavHost(
                navController,
                onClose = { navbarOption = 0 },
                onHome = onHome,
                onRegister = onRegister,
                onSettings = onSettings
            )
//            else {
//                BitacoraNavHost(
//                    navController,
//                    id_tecnico = id_tecnico,
//                    onHome = onHome,
//                    onRegister = onRegister,
//                    onSettings = onSettings
//                )
//            }
        }
//        4 -> SettingsNavHost(navController, id_tecnico,Modifier.padding(paddingValues))
    }
}

@Preview
@Composable
fun PreviewSiamoApp() {
    SIAMOTheme() { SiamoNavHostProvider() }
}