package com.example.ttmaker.components.landingpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ntech.ttmaker.R
//import com.example.ttmaker.R

import com.example.ttmaker.classes.Institute
import com.example.ttmaker.ui.theme.FontSizes


@Composable
fun InfoCard(institute: Institute) {
        Column (
            modifier = Modifier
                .padding(start = 15.dp, top = 10.dp, end = 25.dp, bottom = 10.dp)
        ){

            Row(
                modifier= Modifier
                    .padding(bottom = 0.dp),
                verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "${institute.allTimetables.size}",
                    fontSize = FontSizes.headline,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    modifier  = Modifier
                        .padding(start = 6.dp),
                    text = "time tables",
                    //                style = MaterialTheme.typography.body
                    fontSize = FontSizes.caption,
                    color = colorResource(id = R.color.textLightGrayPale)
                )
            }

//            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = institute.name,
                fontSize = FontSizes.title
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Subjects: ${institute.subjects.size} | " +
                        "Teachers: ${institute.teachers.size}",
                color = colorResource(id = R.color.textLightGrayPale),
                fontSize =  FontSizes.caption
            )
//            Text(
//                text = "Teachers: ${institute.teachers.size}",
//                fontSize =  FontSizes.caption,
//                        color = colorResource(id = R.color.textLightGrayPale)
//            )
        }

}
@Composable
fun InstituteCard(institute: Institute,
                  toTimetableCreationPageWithInstitute:
                      (givenInstitute: Institute?)-> Unit,

                  ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)
            ,
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White) // Set the background color here
    ) {
        Row(
            modifier = Modifier
//                .padding(46.dp)
                .clickable { toTimetableCreationPageWithInstitute(institute) }
            ,
            horizontalArrangement = Arrangement.SpaceBetween,

        ) {
            InfoCard(institute = institute)



            Image(
                painter = painterResource(id = R.drawable.schoolpic0), // Replace with your image name
                contentDescription = "Institute Image",
                modifier = Modifier
                    .width(85.dp)
                    .height(85.dp)
//                    .padding(start = 14.dp)
            ,contentScale = ContentScale.Crop // Adjust as needed
            )
        }
    }
}


@Composable
fun InstituteList(
    instituteList: List<Institute>,
    toTimetableCreationPageWithInstitute: (givenInstitute: Institute?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bgLight))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 8.dp, start = 10.dp, end = 10.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ){

            Text(
                color = Color.Gray, text = "Institutes", fontSize = FontSizes.caption
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    color = colorResource(id = R.color.textLightBluePale),
                    text = "Swipe ",
                    fontSize = FontSizes.caption
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward, // Use an appropriate icon
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                        .size(12.dp),
                    tint = colorResource(id = R.color.textLightBluePale),
                )
            }
        }
        val sortedInstituteList = remember(instituteList) {
            instituteList.sortedByDescending { it.createdAt }
        }
        LazyRow(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(sortedInstituteList.size) { index ->
                InstituteCard(sortedInstituteList[index],
                    toTimetableCreationPageWithInstitute)
            }
        }
    }
}