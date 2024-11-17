package com.example.siamo

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.siamo.ui.navigation.SiamoNavHost
import com.example.siamo.ui.theme.SIAMOTheme

@Composable
fun SiamoApp() {
    SiamoNavHost()
}

@Preview
@Composable
fun PreviewSiamoApp() {
    SIAMOTheme(){ SiamoApp() }
}