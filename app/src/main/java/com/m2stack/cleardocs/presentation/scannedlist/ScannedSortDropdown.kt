package com.m2stack.cleardocs.presentation.scannedlist

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ScannedSortDropdown(
    selectedSort: String,
    expanded: Boolean,
    options: List<String>,
    onClickExpand: () -> Unit,
    onOptionSelected: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF0F0F0))
                .clickable { onClickExpand() }
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Text("Sort by: ", fontSize = 14.sp, color = Color.DarkGray)
            Text(selectedSort, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onClickExpand() },
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            fontWeight = if (option == selectedSort) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    },
                    onClick = { onOptionSelected(option) }
                )
            }
        }
    }
}
