//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.compose.rememberNavController
////import com.example.ttmaker.domain.enums.SchoolClass
//import com.example.ui.theme.manrope
//
//@Composable
//fun AddTeachersScreen() {
//    val navController = rememberNavController()
//
//    Scaffold(
//        containerColor = Color(0xFFF2F3F4),
//    ) { innerPadding ->
//        val subjectsList1 = listOf(101, 102)
//        val subjectsList2 = listOf(103, 104)
//        val classList1 = listOf("5A", "6B")
//        val classList2 = listOf("7A", "8C")
//        val schoolClass = SchoolClass.NURSERY
//        val teachers = remember {
//            mutableListOf(
//                Teacher(
//                    id = 1,
//                    name = "John Doe",
//                    subjects = subjectsList1,
//                    classes = classList1,
//                    classTeacher = schoolClass,
//                    isClassTeacher = true
//                ), Teacher(
//                    id = 2,
//                    name = "Jane Smith",
//                    subjects = subjectsList2,
//                    classes = classList2,
//                    classTeacher = null,
//                    isClassTeacher = false
//                ), Teacher(
//                    id = 3,
//                    name = "Emily Johnson",
//                    subjects = subjectsList1,
//                    classes = null,
//                    classTeacher = null,
//                    isClassTeacher = false
//                )
//            )
//        }
//
//        teachers.add(
//            Teacher(
//                id = 4,
//                name = "Mark Wilson",
//                subjects = listOf(105, 106),
//                classes = listOf("9A", "10B"),
//                classTeacher = null,
//                isClassTeacher = false
//            )
//        )
//
//        teachers.forEach { teacher ->
//            println("Teacher: ${teacher.name}, Subjects: ${teacher.subjects}, Is Class Teacher: ${teacher.isClassTeacher}")
//        }
//
//        Column {
//            Row(
//                modifier = Modifier
//                    .padding(innerPadding)
//                    .padding(16.dp)
//            ) {
//
//                Spacer(modifier = Modifier.height(8.dp))
//                TextField(
//                    value = "Mr. ",
//                    onValueChange = { },
//                    modifier = Modifier.weight(1f),
//                    shape = CircleShape,
//                    textStyle = TextStyle.Default.copy(
//                        fontSize = 20.sp, fontFamily = manrope, fontWeight = FontWeight.Normal
//                    ),
//                    colors = TextFieldDefaults.colors(
//                        unfocusedContainerColor = Color.White,
//                        focusedContainerColor = Color.White,
//                        focusedLabelColor = Color.Black,
//                        unfocusedLabelColor = Color.Black,
//                        errorContainerColor = Color.Red,
//                        errorLabelColor = Color.Red,
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent,
//                    ),
//                    maxLines = 1,
//
//
//                    )
//                Spacer(modifier = Modifier.width(16.dp))
//                Button(
//                    onClick = {
//                        navController.navigate("select_subject")
//                    },
//                    modifier = Modifier
//                        .clip(RoundedCornerShape(50.dp))
//                        .weight(0.5f)
//                        .height(56.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color.White, contentColor = Color.Black
//                    ),
//                    contentPadding = PaddingValues(vertical = 8.dp)
//                ) {
//                    Text(
//                        text = "Add", style = MaterialTheme.typography.bodySmall
//
//                    )
//
//                }
//            }
//
//            LazyColumn(
//                modifier = Modifier.padding(16.dp)
//            ) {
//                items(teachers) { teacher ->
//                    TeacherCard(teacher)
//                }
//            }
//        }
//    }
//}
//
//
//@Composable
//fun TeacherCard(teacher: Teacher) {
//    Card(
//        modifier = Modifier
//            .padding(vertical = 8.dp)
//            .fillMaxWidth(),
//        colors = CardDefaults.cardColors(
//            containerColor = Color.White
//        ),
//        shape = RoundedCornerShape(16.dp)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = "Name: ${teacher.name}", style = MaterialTheme.typography.bodyLarge)
//            Text(
//                text = "Subjects: ${teacher.subjects?.joinToString(", ")}",
//                style = MaterialTheme.typography.bodyMedium
//            )
//            Text(
//                text = "Classes: ${teacher.classes?.joinToString(", ")}",
//                style = MaterialTheme.typography.bodyMedium
//            )
//            Text(
//                text = "Is Class Teacher: ${teacher.isClassTeacher}",
//                style = MaterialTheme.typography.bodyMedium
//            )
//            teacher.classTeacher?.let {
//                Text(
//                    text = "Class Teacher of: ${it.name} ",
//                    style = MaterialTheme.typography.bodyMedium
//                )
//            }
//        }
//    }
//}
