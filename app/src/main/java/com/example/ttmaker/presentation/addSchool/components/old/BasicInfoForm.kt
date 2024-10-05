//package com.example.ttmaker.presentation.addSchool.components.old
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.wrapContentSize
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.DropdownMenu
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.ttmaker.ManropeFontFamily
//import com.example.ttmaker.domain.enums.SchoolClass
//import com.example.ttmaker.util.sessionList
//import com.example.ui.theme.manrope
//
//@Composable
//fun BasicInfoForm() {
//    val schoolData = remember {
//        mutableStateOf(
//            School(
//                id = 1,
//                name = "",
//                address = "",
//                session = sessionList[0],
//                classes = listOf<SchoolClass>(),
//                subjects = listOf<Subject>(),
//                teachers = listOf<Teacher>()
//            )
//        )
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//    ) {
//        Text(
//            text = "School Name",
//            style = MaterialTheme.typography.titleMedium,
//            color = Color.Black,
//            fontFamily = manrope
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        TextField(
//            value = schoolData.value.name,
//            onValueChange = { schoolData.value = schoolData.value.copy(name = it) },
//            modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(16.dp),
//            textStyle = TextStyle.Default.copy(
//                fontSize = 20.sp, fontFamily = manrope, fontWeight = FontWeight.Normal
//            ),
//            colors = TextFieldDefaults.colors(
//                unfocusedContainerColor = Color.White,
//                focusedContainerColor = Color.White,
//                focusedLabelColor = Color.Black,
//                unfocusedLabelColor = Color.Black,
//                errorContainerColor = Color.Red,
//                errorLabelColor = Color.Red,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//            ),
//            maxLines = 1,
//
//
//            )
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(
//            text = "School Address",
//            style = MaterialTheme.typography.titleMedium,
//            color = Color.Black,
//            fontFamily = manrope
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        TextField(
//            value = schoolData.value.address,
//            onValueChange = { schoolData.value = schoolData.value.copy(address = it) },
//            modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(16.dp),
//            textStyle = TextStyle.Default.copy(
//                fontSize = 20.sp, fontFamily = manrope, fontWeight = FontWeight.Normal
//            ),
//            colors = TextFieldDefaults.colors(
//                unfocusedContainerColor = Color.White,
//                focusedContainerColor = Color.White,
//                focusedLabelColor = Color.Black,
//                unfocusedLabelColor = Color.Black,
//                errorContainerColor = Color.Red,
//                errorLabelColor = Color.Red,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//            ),
//            maxLines = 1,
//
//
//            )
//        Spacer(modifier = Modifier.height(16.dp))
//        var expanded by remember { mutableStateOf(false) }
//        val scrollState = rememberScrollState()
//        Text(
//            text = "Academic Year",
//            style = MaterialTheme.typography.titleMedium,
//            color = Color.Black,
//            fontFamily = manrope
//        )
//
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .fillMaxWidth()
//                .wrapContentSize(Alignment.TopStart)
//                .clip(RoundedCornerShape(16.dp))
//
//        ) {
//
//            Text(text = schoolData.value.session,
//                modifier = Modifier
//                    .clickable { expanded = true }
//                    .clip(RoundedCornerShape(16.dp))
//                    .background(Color.White)
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                fontSize = 16.sp,
//                fontFamily = ManropeFontFamily,
//                color = Color.Black)
//            DropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false },
//                scrollState = scrollState,
//                modifier = Modifier
//                    .clip(
//                        RoundedCornerShape(16.dp)
//                    )
//                    .background(Color.White),
//
//                ) {
//                sessionList.forEach { it ->
//                    DropdownMenuItem(text = {
//                        Text(
//                            text = it,
//                            style = MaterialTheme.typography.titleMedium,
//                            color = Color.Black,
//                            fontFamily = manrope
//                        )
//                    }, onClick = {
//                        schoolData.value = schoolData.value.copy(session = it)
//                        expanded = false
//                    })
//                }
//
//            }
//            LaunchedEffect(expanded) {
//                if (expanded) {
//                    scrollState.scrollTo(scrollState.maxValue)
//                }
//            }
//        }
//
//
//    }
//}
