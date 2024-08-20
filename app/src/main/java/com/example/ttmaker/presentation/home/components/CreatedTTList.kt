package com.example.ttmaker.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ttmaker.domain.models.TimeTableCard
import com.example.ttmaker.ManropeFontFamily
import com.example.ttmaker.presentation.home.shared.Createttcard

@Composable
fun CreatedTTList() {
    ListHeader()
    LazyColumn {
        items(sampleCards) { card ->
            Createttcard(ttcard = card)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ListHeader() {
    Row(
        modifier = Modifier.run {
            fillMaxWidth().clip(CircleShape)

        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Your classes",
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp),
            fontFamily = ManropeFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )

        Row(
            modifier = Modifier
                .padding(end = 16.dp)
                .padding(vertical = 16.dp)
                .clip(CircleShape)
                .background(Color(0xFFE9F2FF))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Center


        ) {
            Text(
                text = "St. Mary's Con...",
                color = Color(0xFF3386FE),
                fontFamily = ManropeFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "", tint = Color(0xFF3386FE))

        }


    }
}

val sampleCards: List<TimeTableCard> = listOf(
    TimeTableCard(
        className = "IV",
        session = "2024-25",
        createdOn = "12 Aug 2023",
        bgColor = Color(0xFFDAD9FF), // Light violet background
        color = Color(0xFF2A288D)    // Dark violet text
    ),
    TimeTableCard(
        className = "III",
        session = "2024-25",
        createdOn = "13 Aug 2023",
        bgColor = Color(0xFFD9FEFF), // Light red background
        color = Color(0xFF28858D)    // Dark red text
    ),
    TimeTableCard(
        className = "II",
        session = "2024-25",
        createdOn = "14 Aug 2023",
        bgColor = Color(0xFFE3FFD9), // Light green background
        color = Color(0xFF418D28)    // Dark green text
    ),
    TimeTableCard(
        className = "I",
        session = "2024-25",
        createdOn = "15 Aug 2023",
        bgColor = Color(0xFFFFD9FE), // Light yellow background
        color = Color(0xFF8D2881)    // Dark yellow text
    ),

    )