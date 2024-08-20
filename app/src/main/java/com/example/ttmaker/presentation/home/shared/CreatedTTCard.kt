package com.example.ttmaker.presentation.home.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ttmaker.domain.models.TimeTableCard
import com.example.ttmaker.ManropeFontFamily
import com.ntech.ttmaker.R

@Composable
fun Createttcard(
    ttcard: TimeTableCard
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)


        ,

    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(56.dp)
                    .height(56.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(ttcard.bgColor), contentAlignment = Alignment.Center

            ) {
                Text(
                    text = ttcard.className,
                    fontFamily = ManropeFontFamily,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = ttcard.color
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Class " + ttcard.className,
                    fontFamily = ManropeFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
                Row {
                    Text(
                        text = ttcard.session,
                        fontFamily = ManropeFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF464646)
                    )
                    Text(text = "·")
                    Text(
                        text = "created on : " + ttcard.createdOn,
                        fontFamily = ManropeFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF464646)
                    )
                }
            }

        }


        IconButton(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.menu), contentDescription = "",

                modifier = Modifier


            )
        }

    }
}

@Preview(
    showBackground = true, showSystemUi = true
)
@Composable
fun Previews() {
    Createttcard(
        ttcard = TimeTableCard(
            bgColor = Color(0xFFF2F2F2),
            className = "IV",
            color = Color(0xFF464646),
            session = "Session 1",
            createdOn = "12/04/2023"
        )
    )
}