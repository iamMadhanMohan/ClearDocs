package com.m2stack.cleardocs.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object ScannedList : Screen("scanned_list")
    object ScanDetail : Screen("scan_detail/{docId}") {
        fun withId(docId: Int) = "scan_detail/$docId"
    }
    object Settings : Screen("settings")
}
