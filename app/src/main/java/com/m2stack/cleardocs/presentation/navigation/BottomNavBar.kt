package com.m2stack.cleardocs.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        Screen.Home,
        Screen.ScannedList,
        Screen.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    when (screen) {
                        Screen.Home -> Icon(Icons.Default.Home, contentDescription = "Home")
                        Screen.ScannedList -> Icon(Icons.Default.Description, contentDescription = "Scans")
                        Screen.Settings -> Icon(Icons.Default.Settings, contentDescription = "Settings")
                        else -> {}
                    }
                },
                label = { Text(screen.route.substringBefore("/").replaceFirstChar { it.uppercase() }) },
                selected = currentRoute?.startsWith(screen.route.substringBefore("/")) == true,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(Screen.Home.route)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}
