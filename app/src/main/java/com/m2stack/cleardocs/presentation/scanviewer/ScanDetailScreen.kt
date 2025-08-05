package com.m2stack.cleardocs.presentation.scanviewer

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
import com.m2stack.cleardocs.presentation.navigation.BottomNavBar
import com.m2stack.cleardocs.presentation.scannedlist.ActionButton

@Composable
fun ScanDetailScreen(
    navController: NavHostController,
    docId: Int,
    viewModel: ScanDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scanDoc by viewModel.scanDocument.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadDocument(docId)
    }

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            // Top bar
            Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                    ) {
                // Back button aligned to the start (left)
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp)
                        .size(24.dp)
                        .clickable { navController.popBackStack() }
                )

                // Centered title
                Text(
                    text = scanDoc?.title ?: "Title N/A",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // Image Preview
            scanDoc?.let { doc ->
                Image(
                    painter = rememberAsyncImagePainter(doc.filePath),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 16.dp)
                )

                // Buttons: Edit, Save
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ActionButton("Edit") {
                        Toast.makeText(context, "Edit coming soon", Toast.LENGTH_SHORT).show()
                    }

                    // Delete button
                    ActionButton("Delete") {
                        viewModel.delete() {
                            navController.popBackStack() // Return to list
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                        }
                    }

                    ActionButton("Save") {
                        viewModel.export(context) { success ->
                            Toast.makeText(
                                context,
                                if (success) "Saved to Downloads" else "Failed to save",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

            } ?: Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
