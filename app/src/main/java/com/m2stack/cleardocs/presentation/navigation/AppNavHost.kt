package com.m2stack.cleardocs.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.m2stack.cleardocs.presentation.home.HomeScreen
import com.m2stack.cleardocs.presentation.scannedlist.ScannedListScreen
import com.m2stack.cleardocs.presentation.scanviewer.ScanDetailScreen
import com.m2stack.cleardocs.presentation.settings.SettingsScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.ScannedList.route) { ScannedListScreen(navController) }
        composable(Screen.Settings.route) { SettingsScreen(navController) }
        composable(
            route = Screen.ScanDetail.route,
            arguments = listOf(navArgument("docId") { type = NavType.IntType })
        ) { backStackEntry ->
            val docId = backStackEntry.arguments?.getInt("docId") ?: -1
            ScanDetailScreen(navController, docId)
        }
    }
}
