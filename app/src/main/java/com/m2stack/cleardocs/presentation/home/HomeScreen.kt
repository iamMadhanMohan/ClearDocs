package com.m2stack.cleardocs.presentation.home

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
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
import com.m2stack.cleardocs.presentation.theme.ClearDocsBlue
import com.m2stack.cleardocs.presentation.theme.ClearDocsLightGray
import com.m2stack.cleardocs.presentation.theme.ClearDocsTextOnPrimary
import com.m2stack.cleardocs.presentation.theme.ClearDocsTextPrimary
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
                fontSize = 32.sp,
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
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ClearDocsBlue),
                shape = RoundedCornerShape(32.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Description,
                    contentDescription = "Scan Icon",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Scan Doc",
                    fontWeight = FontWeight.Bold,
                    color = ClearDocsTextOnPrimary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(Screen.ScannedList.route) },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ClearDocsLightGray),
                shape = RoundedCornerShape(32.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = "Recent Scans Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Recent Scans",
                    fontWeight = FontWeight.Bold,
                    color = ClearDocsTextPrimary
                )
            }
        }
    }
}
