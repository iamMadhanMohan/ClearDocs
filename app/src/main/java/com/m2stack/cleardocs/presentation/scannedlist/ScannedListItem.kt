package com.m2stack.cleardocs.presentation.scannedlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.m2stack.cleardocs.domain.model.ScanDocument
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ScannedListItem(
    doc: ScanDocument,
    isSelected: Boolean,
    onClick: () -> Unit,
    onChecked: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(doc.filePath),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .padding(end = 12.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(text = doc.title, fontWeight = FontWeight.SemiBold)
            Text(
                text = formatTimestamp(doc.timestamp),
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        Checkbox(
            checked = isSelected,
            onCheckedChange = onChecked
        )
    }
}

private fun formatTimestamp(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
