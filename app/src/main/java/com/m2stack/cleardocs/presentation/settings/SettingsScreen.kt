package com.m2stack.cleardocs.presentation.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.m2stack.cleardocs.presentation.navigation.BottomNavBar

// presentation/settings/SettingsScreen.kt
@Composable
fun SettingsScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text("Settings Coming Soon", fontSize = 20.sp, fontWeight = FontWeight.Medium)
        }
    }
}
