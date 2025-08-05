package com.m2stack.cleardocs.presentation.home

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.m2stack.cleardocs.presentation.navigation.BottomNavBar
import com.m2stack.cleardocs.presentation.navigation.Screen
import com.m2stack.cleardocs.scanner.ScannerResultHandler.handleScannerResult
import com.m2stack.cleardocs.scanner.ScannerLauncher.launchScanner

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: ScanDocumentViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = context as? Activity

    val scannerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        handleScannerResult(context, result, viewModel)
    }

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "ClearDocs",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    activity?.let {
                        launchScanner(it, scannerLauncher)
                    } ?: run {
                        Toast.makeText(context, "Activity not available", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB0CBEF)),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "Scan New Document",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(Screen.ScannedList.route) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEFEFF1)),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "Recent Scans",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}
