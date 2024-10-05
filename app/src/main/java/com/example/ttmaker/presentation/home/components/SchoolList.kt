package com.example.ttmaker.presentation.home.components

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ntech.ttmaker.R
//import com.example.ttmaker.R

import com.example.ttmaker.data.LocalSchoolContext
import com.example.ttmaker.data.SchoolEntity
import com.example.ttmaker.presentation.home.HomeViewModel
import com.example.ttmaker.presentation.home.HomeViewModelFactory


@Composable
fun InfoCard(school: SchoolEntity) {
        Column (
            modifier = Modifier
                .padding(start = 15.dp, top = 10.dp, end = 25.dp, bottom = 10.dp)
        ){

            Row(
                modifier= Modifier
                    .padding(bottom = 0.dp),
                verticalAlignment = Alignment.CenterVertically) {

//                Text(
//                    text = "${school.allTimetables.size}",
//                    fontWeight = FontWeight.ExtraBold
//                )
                Text(
                    modifier  = Modifier
                        .padding(start = 6.dp),
                    text = "time tables",
                    //                style = MaterialTheme.typography.body
                   
                    color = colorResource(id = R.color.textLightGrayPale)
                )
            }

//            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = school.name,
                
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = school.hours.toString(),
                )
            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = "Subjects: ${school.subjects.size} | " +
//                        "Teachers: ${school.teachers.size}",
//                color = colorResource(id = R.color.textLightGrayPale),
//            )
//            Text(
//                text = "Teachers: ${institute.teachers.size}",
//                fontSize =  FontSizes.caption,
//                        color = colorResource(id = R.color.textLightGrayPale)
//            )
        }

}
@Composable
fun SchoolCard(school: SchoolEntity
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
                .clickable {
//                    toTimetableCreationPageWithInstitute(school)
                           }
            ,
            horizontalArrangement = Arrangement.SpaceBetween,

        ) {
            InfoCard(school = school)

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
fun SchoolList(
) {
    // Access the current SchoolContext from the CompositionLocal
    val schoolContext = LocalSchoolContext.current
    val vm: HomeViewModel = viewModel(factory = HomeViewModelFactory(schoolContext.schoolRepository))


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bgLight))
    ) {
        Text(text = "YOOO: " + vm.schoolList.value.size)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 8.dp, start = 10.dp, end = 10.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ){

            Text(
                color = Color.Gray, text =  schoolContext.schoolOrInstitute,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    color = colorResource(id = R.color.textLightBluePale),
                    text = "Swipe ",
                    
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward, // Use an appropriate icon
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(12.dp),
                    tint = colorResource(id = R.color.textLightBluePale),
                )
            }
        }
//        val sortedSchoolList = remember(vm.schoolList) {
//            vm.schoolList.value.sortedByDescending { it.createdAt }
//        }
        LazyRow(
            modifier = Modifier.fillMaxSize(),
        ) {
//            items(sortedSchoolList.size) { index ->
//                SchoolCard(sortedSchoolList[index]
//                                )
//            }
            items(vm.schoolList.value.size) { index ->
                SchoolCard(vm.schoolList.value[index]
                )
            }
        }
    }
}