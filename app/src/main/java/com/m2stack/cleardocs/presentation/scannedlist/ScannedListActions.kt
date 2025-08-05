package com.m2stack.cleardocs.presentation.scannedlist

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ScannedListActions(
    modifier: Modifier = Modifier,
    onSelectAll: () -> Unit,
    onDelete: () -> Unit,
    onSave: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ActionButton("Select All", onClick = onSelectAll)
        ActionButton("Save", onClick = onSave)
        ActionButton("Delete", onClick = onDelete)
    }
}

@Composable
fun ActionButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEFEFF1))
    ) {
        Text(label, fontWeight = FontWeight.Medium, color = Color.Black)
    }
}
