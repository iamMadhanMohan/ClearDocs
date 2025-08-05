package com.m2stack.cleardocs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.m2stack.cleardocs.presentation.navigation.AppNavHost
import com.m2stack.cleardocs.presentation.theme.ClearDocsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClearDocsTheme {
                val navController = rememberNavController()
                AppNavHost(navController)
            }
        }
    }
}
