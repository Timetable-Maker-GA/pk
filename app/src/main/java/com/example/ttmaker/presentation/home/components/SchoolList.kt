package com.example.ttmaker.presentation.home.components

import android.content.Intent
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ttmaker.activity.SchoolActivity
import com.example.ttmaker.TTMakerApplication
import com.ntech.ttmaker.R
//import com.example.ttmaker.R

//import com.example.ttmaker.data.LocalSchoolContext
import com.example.ttmaker.model.SchoolBasicInfo
import com.example.ttmaker.presentation.home.HomeViewModel
import com.example.ttmaker.presentation.home.HomeViewModelFactory


@Composable
fun InfoCard(school: SchoolBasicInfo) {
        Column (
            modifier = Modifier
                .padding(start = 15.dp, top = 10.dp, end = 25.dp, bottom = 10.dp)
        ){

            Row(
                modifier= Modifier
                    .padding(bottom = 0.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier  = Modifier
                        .padding(start = 6.dp),
                    text = school.timetableCount.toString(),
                    color = colorResource(id = R.color.textLightGrayPale),
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    modifier  = Modifier
                        .padding(start = 3.dp),
                    text = " time tables",
                    color = colorResource(id = R.color.textLightGrayPale)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = school.name,
                
            )
        }

}
@Composable
fun SchoolCard(school: SchoolBasicInfo                  ) {
    val context = LocalContext.current // Get the context
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
                .clickable {

                    // Create an Intent to launch SchoolActivity and pass the school ID
                    val intent = Intent(context, SchoolActivity::class.java).apply {
                        putExtra("SCHOOL_ID", school.id) // Pass the school ID (ensure you have this field in SchoolBasicInfo)
                    }
                    context.startActivity(intent) // Start the activity
                           }
            ,
            horizontalArrangement = Arrangement.SpaceBetween,

        ) {
            InfoCard(school = school)

            Image(
                painter = painterResource(id = school.imageResId ?: R.drawable.school_pic),
                contentDescription = "Institute Image",
                modifier = Modifier
                    .size(85.dp) // Ensures both width and height are 85.dp
                    .clip(CircleShape), // Crops the image into a circular shape
                contentScale = ContentScale.Crop // Ensures the image is cropped to fit the circle
            )
        }
    }
}


@Composable
fun SchoolList(
) {
    val context = LocalContext.current
    val app = context.applicationContext as TTMakerApplication
    val vm: HomeViewModel = viewModel(factory = HomeViewModelFactory(app.schoolRepository))

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
                color = Color.Gray, text =  app.schoolOrInstitute,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    color = colorResource(id = R.color.headingLightBluePale),
                    text = "Swipe ",
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward, // Use an appropriate icon
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(12.dp),
                    tint = colorResource(id = R.color.headingLightBluePale),
                )
            }
        }
        LazyRow(
            modifier = Modifier.fillMaxSize(),
        ) {
            vm.schoolList.value?.let { schoolList ->
                val sortedList = schoolList.sortedByDescending { it.createdAt } // Sort by createdAt (desc)
                items(sortedList.size) { index ->
                    SchoolCard(
                        school = sortedList[index])
                                     }
            }

        }
    }
}