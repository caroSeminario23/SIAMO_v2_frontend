package com.example.siamo

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.siamo.ui.navigation.BuscarOstNavHost
import com.example.siamo.ui.navigation.RegisterNavHost
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.NavigationBarRecepcionista

@Composable
fun SiamoApp(
) {
    var navController = rememberNavController()
    var navbarOption: Int by rememberSaveable { mutableStateOf(2) }
    val onHome = { navbarOption = 1 }
    val onRegister = { navbarOption = 2 }
    val onSearch = { navbarOption = 3 }
    val onSettings = { navbarOption = 4 }

    when (navbarOption) {
//            1 -> HomeNavHost(navController, Modifier.padding(paddingValues))
        2 -> RegisterNavHost(
            navController,
            onClose = { navbarOption = 3 },
            onHome = onHome,
            onSearch = onSearch,
            onSettings = onSettings)
        3 -> BuscarOstNavHost(
            navController,
            onClose = { navbarOption = 2 },
            onHome = onHome,
            onRegister = onRegister,
            onSettings = onSettings
        )
//            4 -> SettingsNavHost(navController, Modifier.padding(paddingValues))
    }
}

@Preview
@Composable
fun PreviewSiamoApp() {
    SIAMOTheme() { SiamoApp() }
}