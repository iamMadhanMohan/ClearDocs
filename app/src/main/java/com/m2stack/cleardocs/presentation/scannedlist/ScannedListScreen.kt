package com.m2stack.cleardocs.presentation.scannedlist

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.m2stack.cleardocs.presentation.navigation.BottomNavBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.m2stack.cleardocs.presentation.navigation.Screen

@Composable
fun ScannedListScreen(
    navController: NavHostController,
    viewModel: ScannedListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val scannedDocs by viewModel.scannedDocuments.collectAsState()
    var selectedDocs by remember { mutableStateOf(setOf<Int>()) }
    var searchQuery by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedSort by remember { mutableStateOf("Date ↓") }

    val sortOptions = listOf("Date ↑", "Date ↓", "Title A-Z", "Title Z-A")

    val filteredDocs = remember(scannedDocs, searchQuery, selectedSort) {
        scannedDocs
            .filter { it.title.contains(searchQuery, ignoreCase = true) }
            .let { docs ->
                when (selectedSort) {
                    "Date ↑" -> docs.sortedBy { it.timestamp }
                    "Date ↓" -> docs.sortedByDescending { it.timestamp }
                    "Title A-Z" -> docs.sortedBy { it.title.lowercase() }
                    "Title Z-A" -> docs.sortedByDescending { it.title.lowercase() }
                    else -> docs
                }
            }
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

            // Fixed Top UI
            ScannedTopBar(navController)

            ScannedSearchBar(
                value = searchQuery,
                onQueryChange = { searchQuery = it },
                focusManager = focusManager
            )

            ScannedSortDropdown(
                selectedSort = selectedSort,
                expanded = expanded,
                options = sortOptions,
                onClickExpand = { expanded = true },
                onOptionSelected = {
                    selectedSort = it
                    expanded = false
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Scrollable List
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // makes only this scrollable
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredDocs) { doc ->
                    ScannedListItem(
                        doc = doc,
                        isSelected = selectedDocs.contains(doc.id),
                        onClick = {
                            navController.navigate(Screen.ScanDetail.withId(doc.id))
                        },
                        onChecked = { checked ->
                            selectedDocs = if (checked) selectedDocs + doc.id else selectedDocs - doc.id
                        }
                    )
                }
            }

            // Fixed Bottom Actions
            ScannedListActions(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                onSelectAll = { selectedDocs = scannedDocs.map { it.id }.toSet() },
                onDelete = {
                    val toDelete = scannedDocs.filter { selectedDocs.contains(it.id) }
                    viewModel.deleteDocuments(toDelete)
                    selectedDocs = emptySet()
                    Toast.makeText(context, "${toDelete.size} item(s) deleted", Toast.LENGTH_SHORT).show()
                },
                onSave = {
                    val toSave = scannedDocs.filter { selectedDocs.contains(it.id) }
                    viewModel.exportDocuments(context, toSave) { count ->
                        Toast.makeText(context, "$count file(s) saved to Downloads/ClearDocs", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}



