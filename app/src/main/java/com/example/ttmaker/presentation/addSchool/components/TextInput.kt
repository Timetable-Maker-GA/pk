package com.example.ttmaker.presentation.addSchool.components

//import com.example.ttmaker.data.LocalSchoolContext
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ttmaker.TTMakerApplication
import com.example.ttmaker.model.TeacherInfo
import com.ntech.ttmaker.R
import java.io.File
import java.io.InputStream
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.ttmaker.activity.CreateTTActivity
import com.example.ttmaker.activity.MainActivity

@Composable
fun Format() {

    Column {

        Text(
            text = "Upload text input",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier
//                    .fillMaxWidth(0.6f)
//                    .width(200.dp)
            ) {
                Text(
                    text = """
                InstituteName
                
                Days in week, hours per day
                
                Subjects name separated by ,
                
                First teacher_name
                His/her subjects name separated by ,
                Subjects respective class range
                
                Second teacher2_name
                His/her subjects name separated by ,
                subjects respective class range
            """.trimIndent(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier
                        .background(colorResource(id = R.color.bgLight))
                        .padding(8.dp)
//                        .fillMaxWidth()
                )
            }
//            InstituteName
//
//            days,hours
//
//            Math,Science,English
//
//            teacher_name
//            sub1,sub2,sub3.....
//            1-2,4-7,8-9..
//
//            teacher2_name
//            subx,suby,subz.....
//            3-3,4-8,3-3....
            Column(
//                modifier = Modifier.fillMaxWidth(0.4f)

            ) {

                Text(
                    text = """
                Sunrise Elementary

                5,7
                
                Math,Science,Art
                
                Anderson
                Math,Science
                1-2,3-5
                
                Miller
                Art
                6-7
            """.trimIndent(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier
                        .background(colorResource(id = R.color.bgLight))
                        .padding(8.dp)
//                        .fillMaxWidth()
                )
            }
        }

    }
}

@SuppressLint("Recycle")
@Composable
fun TextInput(
//    navController: NavHostController
) {
    // Access the application context
    val context = LocalContext.current

    val app = context.applicationContext as TTMakerApplication
    val viewModel: TextInputViewModel =
        viewModel(factory = TextInputViewModelFactory(app.schoolRepository))


    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Format()

        if (viewModel.toastContent.value != "") {
            Toast.makeText(context, viewModel.toastContent.value, Toast.LENGTH_SHORT).show()
            viewModel.toastShown() // Reset the flag after showing the toast
        }

        OutlinedTextField(
            value = viewModel.txtInput.value,
            onValueChange = { viewModel.updateTxtInput(it) },
            label = { Text("Give your institute information in above format") },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(vertical = 8.dp)
        )
        Text(text = viewModel.temp.value)
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.buttonLightHeavy)
            ),

            onClick = {
                if (viewModel.txtInput.value != "") {
                    viewModel.insertSchool()
//                    navController.navigate("home_screen")
                    val intent = Intent(context, MainActivity::class.java).apply {
//                        putExtra("from", "textInput")
                    }
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "Wrong Input..", Toast.LENGTH_LONG)
                        .show()
                }
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Parse and Create Institute")
        }
    }
}


@Composable
fun TeacherList(teachers: List<TeacherInfo>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        teachers.forEach {
            TeacherCard(teacher = it)
        }
    }
}

@Composable
fun TeacherCard(teacher: TeacherInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp) // Adding elevation for better visual appeal
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Teacher: ${teacher.name}",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Subjects         Classes",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Divider(color = Color.Gray, thickness = 1.dp)
            teacher.subjects.zip(teacher.classLevels).forEach { (subject, classLevel) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = subject,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "${classLevel.start}-${classLevel.end}",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.End
                    )
                }
                Divider(color = Color.Gray, thickness = 0.5.dp)
            }
        }
    }
}