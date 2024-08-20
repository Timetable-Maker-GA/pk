package com.example.ttmaker.presentation.home.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun TabControls(
    state: Int, setState: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(16.dp)
    ) {
        Button(
            onClick = {
                setState(
                    (state - 1).coerceAtLeast(0)
                )
            },
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .weight(0.5f)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White, contentColor = Color.Black
            ),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            Text(
                text = "Previous" , style = MaterialTheme.typography.bodySmall

            )

        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                setState(
                    (state + 1).coerceAtMost(2)
                )
            },
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .weight(0.5f)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White, contentColor = Color.Black
            ),
            contentPadding = PaddingValues(vertical = 8.dp)

        ) {

            Text(
                text = "Next", style = MaterialTheme.typography.bodySmall

            )
        }
    }
}
