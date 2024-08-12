package com.example.ttmaker.presentation.components

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ntech.ttmaker.R

@Composable
fun HomeControls(navController: NavHostController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(16.dp)
    ) {
        Button(
            onClick = {
                navController.navigate("add_tt_screen")
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
            Icon(painter = painterResource(id = R.drawable.time_table), contentDescription = "")
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Add Time Table", style = MaterialTheme.typography.bodySmall

            )

        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                navController.navigate("add_school_screen")
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
            Icon(painter = painterResource(id = R.drawable.school), contentDescription = "")
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Add School", style = MaterialTheme.typography.bodySmall

            )
        }
    }
}

