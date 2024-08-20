package com.example.ttmaker.presentation.shared


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(24.dp)
            .background(
                color = if (checked) Color(0xFF3165FF) else Color(0xFFF1F1F5),
                shape = CircleShape
            )
            .clickable { onCheckedChange(!checked) }
            .padding(4.dp)
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Checked",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomCheckbox() {
    var isChecked by remember { mutableStateOf(true) }
    CustomCheckbox(
        checked = isChecked,
        onCheckedChange = { isChecked = it }
    )
}
