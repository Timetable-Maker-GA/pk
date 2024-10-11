//package com.example.ttmaker.pages
//
//import android.content.Context
//import android.net.Uri
//import android.util.Log
//import android.widget.Toast
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Divider
//import androidx.compose.material3.DropdownMenu
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Slider
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.zIndex
//import com.ntech.ttmaker.MainActivity
////import com.example.ttmakerpk.R
//import com.example.ttmaker.adsContainer.InterstitialAdContainerEXCEL
//import com.example.ttmaker.adsContainer.InterstitialAdContainerPDF
//import com.example.ttmaker.classes.Institute
//import com.example.ttmaker.presentation.addTimeTable.components.DisplayTimetables
//import com.example.ttmaker.components.updateInstitute
//import com.example.ttmaker.ui.theme.FontSizes
//import com.itextpdf.kernel.pdf.PdfDocument
//import com.itextpdf.kernel.pdf.PdfWriter
//import com.itextpdf.layout.Document
//import com.itextpdf.layout.element.Paragraph
//import com.itextpdf.layout.element.Table
//import com.itextpdf.layout.property.TextAlignment
//import com.itextpdf.layout.property.UnitValue
//import com.ntech.ttmaker.R
////import com.example.ttmaker.R
//
//import kotlinx.coroutines.DelicateCoroutinesApi
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import java.io.OutputStream
//import java.util.Objects.isNull
//
//import org.apache.poi.ss.usermodel.*
//import org.apache.poi.xssf.usermodel.XSSFWorkbook
//
//@Composable
//fun TimetableCreationPage(
//    instituteList: List<Institute>,
//    toLandingPage: () -> Unit,
//    givenInstitute: Institute? = null
//) {
//    var expanded by rememberSaveable { mutableStateOf(false) }
//    var className by rememberSaveable { mutableStateOf("") }
//    var section by rememberSaveable { mutableStateOf("") }
//    var isCreating by rememberSaveable { mutableStateOf(false) }
//
//    var subPeriodsPerWeek = rememberSaveable { mutableStateOf(mapOf<String, Int>()) }
//    val context = LocalContext.current
//    var selectedInstitute by remember { mutableStateOf<Institute?>(givenInstitute) }
//    val scrollState = rememberScrollState()
//
//    var level by rememberSaveable { mutableStateOf(100f) }
//
//    // Function to update the map in a way that works with state management
//    fun updateSubPeriods(subject: String, periods: Int) {
//        var temp = subPeriodsPerWeek.value.toMutableMap()
//        temp[subject] = periods
//        subPeriodsPerWeek.value = temp
//    }
//
//    val configuration = LocalConfiguration.current
//    val screenWidth = configuration.screenWidthDp.dp
//    val screenHeight = configuration.screenHeightDp.dp
//    var isUnresponsive by rememberSaveable { mutableStateOf(false) }
//    var gen by rememberSaveable { mutableStateOf(0) }
//    // Set up the launcher for creating a document
//    val createFileLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.CreateDocument("application/pdf")
//    ) { uri: Uri? ->
//        uri?.let {
//            InterstitialAdContainerPDF(context as MainActivity)
//            selectedInstitute?.let { institute ->
//                saveAsPDF(context, institute, it)
//            }
//        }
//    }
//    val createFileLauncherExcel = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.CreateDocument("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
//    ) { uri: Uri? ->
//        uri?.let {
//            InterstitialAdContainerEXCEL(context as MainActivity)
//            selectedInstitute?.let { institute ->
//                saveAsExcel(context, institute, it)
//            }
//        }
//    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(screenWidth * 0.02f)
//            .verticalScroll(scrollState)
//            .background(Color.White)
//
//    ) {
//        if (isCreating) {
//            LoadingScreen(gen, level.toInt())
//        } else {
//            Column(
//                modifier = Modifier
//                    .then(if (isUnresponsive) Modifier.pointerInput(Unit) { awaitPointerEventScope { while (true) awaitPointerEvent() } } else Modifier)
//            ) {
//
//                Text(
//                    text = "Select Institute",
//                    fontSize = FontSizes.body,
//                    modifier = Modifier.padding(top = 4.dp)
//                )
//                Box {
//                    Text(
//                        text = selectedInstitute?.name ?: "Choose an institute",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable { expanded = true }
//                            .background(Color.LightGray)
//                            .padding(16.dp),
//                        fontSize = FontSizes.body,
//                    )
//                    DropdownMenu(
//                        expanded = expanded,
//                        onDismissRequest = { expanded = false },
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        instituteList.forEach { institute ->
//                            DropdownMenuItem(
//                                text = {
//                                    Text(
//                                        institute.name,
//                                        fontSize = FontSizes.body
//                                    )
//                                },
//                                onClick = {
//                                    selectedInstitute = institute
//                                    expanded = false
//                                })
//                        }
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth() // Make sure the Row takes full width
//                        .padding(horizontal = 0.dp), // Add horizontal padding for spacing
//                    horizontalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between TextFields
//                ) {
//                    // Class Name Input
//
//                    OutlinedTextField(
//                        value = className,
//                        onValueChange = { className = it },
//                        label = {
//                            Text(
//                                "Class No",
//                                fontSize = FontSizes.body,
//                            )
//                        },
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                        modifier = Modifier
//                            .weight(1f) // Make each field take equal space
//                            .padding(vertical = 4.dp) // Add vertical padding for spacing
//                    )
//
//                    // Section Input
//                    OutlinedTextField(
//                        value = section,
//                        onValueChange = { section = it },
//                        label = {
//                            Text(
//                                "Section",
//                                fontSize = FontSizes.body,
//                            )
//                        },
//                        modifier = Modifier
//                            .weight(1f) // Make each field take equal space
//                            .padding(vertical = 4.dp) // Add vertical padding for spacing
//                    )
//
//                }
//
//
//                var periodsGiven = 0
//                for ((_, periods) in subPeriodsPerWeek.value) {
//                    // Accumulate the total number of periods
//                    periodsGiven += periods
//                }
//
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//
//                    ) {
//                    Column {
//
//                        Text(
//                            modifier = Modifier
//                                .padding(end = 8.dp),
//                            text =
//                            "Complexity Non Linear: ${level.toInt()}",
//                            fontSize = FontSizes.body,
//                            fontWeight = FontWeight.Bold
//                        )
//                        Text(
//                            modifier = Modifier
//                                .padding(end = 8.dp),
//                            text =
//                            "(200 = 2min & 600 = 18min)",
//                            fontSize = FontSizes.body
//                        )
//                    }
//                    Slider(
//                        value = level,
//                        onValueChange = { newValue ->
//                            level = newValue
//                        },
//                        valueRange = 10f..1000f,
//                        steps = 100,
//                        modifier = Modifier.padding(vertical = 4.dp)
//                    )
//
//                }
//
//                // Add Row for buttons
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 8.dp),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//
////                val context = LocalContext.current
//                    Button(
//                        onClick = {
//                            if (selectedInstitute != null) {
//                                createFileLauncher.launch(selectedInstitute!!.name + "_Report.pdf")
////                                InterstitialAdContainerPDF(context as MainActivity)
//                            }
//                        },
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = colorResource(id = R.color.buttonLightHeavy)
//                        )
//                    ) {
//                        Text("Save as PDF")
//                    }
//
//                    Button(
//                        onClick = {
//                            if (selectedInstitute != null) {
//                                createFileLauncherExcel.launch(selectedInstitute!!.name + "_Report.xlsx")
////                                InterstitialAdContainerEXCEL(context as MainActivity)
//                            }
//                        },
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = colorResource(id = R.color.buttonLightHeavy)
//                        )
//                    ) {
//                        Text("Save as Excel")
//                    }
//                }
//
//                Text(
//                    text = "Subjects - Periods per Week : $periodsGiven / ${((selectedInstitute?.HOURS ?: 0) * (selectedInstitute?.DAYS ?: 0)).toString()}",
//                    style = MaterialTheme.typography.bodyLarge.copy(
//                        fontSize = FontSizes.body,
//                        fontWeight = FontWeight.SemiBold,
//                        color = MaterialTheme.colorScheme.secondary,
//                        textAlign = TextAlign.Center
//                    ),
//                    modifier = Modifier.padding(vertical = 4.dp)
//                )
//
//                Divider(
//                    color = Color.Gray,
//                    thickness = 1.dp,
//                    modifier = Modifier.padding(vertical = 8.dp)
//                )
//
//                if (isNull(selectedInstitute)) Text(
//                    text = "Select Institute First!!!",
//                    fontSize = FontSizes.body,
//                )
//
//                selectedInstitute?.let {
//                    SubjectSelection(it, className, subPeriodsPerWeek.value,
//                        updateSubPeriods = { subject, periods ->
//                            updateSubPeriods(subject, periods)
//                        })
//                }
//                Spacer(modifier = Modifier.height(10.dp))
//
//                selectedInstitute?.let { DisplayTimetables(it.allTimetables) }
//                // Create Timetable Button
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Center
//
//                ) {
//                    Button(
//
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = colorResource(id = R.color.buttonLightHeavy) // Set the button's background color
//                        ),
//                        onClick = {
//                            if (isNull(selectedInstitute)) {
//                                Toast.makeText(
//                                    context,
//                                    "Select Insitute First!",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                return@Button
//                            }
//
//                            var p = 0
//                            for ((_, periods) in subPeriodsPerWeek.value) {
//                                // Accumulate the total number of periods
//                                p += periods
//                            }
//                            if (p != selectedInstitute!!.HOURS * selectedInstitute!!.DAYS) {
//                                Toast.makeText(
//                                    context,
//                                    "Match the periods numbers: ${p} / ${selectedInstitute!!.HOURS * selectedInstitute!!.DAYS}",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                return@Button
//                            }
//                            Toast.makeText(context, "Creating timetable wait...", Toast.LENGTH_LONG)
//                                .show()
//
////                    val currentTimestamp = System.currentTimeMillis()
////                    selectedInstitute?.createTimetable(
////                        className,
////                        section, subPeriodsPerWeek.value,
////                        currentTimestamp,  level.toInt())
////
////                    if (!isNull(selectedInstitute))     updateInstitute(context, selectedInstitute!!)
////                    subPeriodsPerWeek.value = mapOf()
//                            isCreating = true
//                            createTimetableAsync(
//                                selectedInstitute!!,
//                                className,
//                                section,
//                                subPeriodsPerWeek.value,
//                                level.toInt(),
//                                gen,
//                                updateGen = { it -> gen = it }
//                            ) {
//                                isCreating = false
//                                // Handle completion, e.g., update UI or navigate
//                                if (!isNull(selectedInstitute)) updateInstitute(
//                                    context,
//                                    selectedInstitute!!
//                                )
//                                subPeriodsPerWeek.value = mapOf()
//                                Toast.makeText(
//                                    context,
//                                    "Timetable created successfully!",
//                                    Toast.LENGTH_LONG
//                                ).show()
//                            }
//                        },
//                        modifier = Modifier.padding(vertical = 16.dp)
//                    ) {
//                        Text("Create Timetable")
//                    }
//                }
//            }
//        }
//
//    }
//}
//
//fun saveAsExcel(context: Context, institute: Institute, uri: Uri) {
//    Log.d("ExcelCreation", "Starting saveAsExcel function")
//    try {
//        val outputStream: OutputStream? = context.contentResolver.openOutputStream(uri)
//        val workbook: Workbook = XSSFWorkbook() // Create a new workbook
//        val sheet: Sheet = workbook.createSheet("Institute Report") // Create a sheet
//
//        Log.d("ExcelCreation", "Adding title to Excel")
//        // Add title
//        val titleRow: Row = sheet.createRow(0)
//        val titleCell: org.apache.poi.ss.usermodel.Cell? = titleRow.createCell(0)
//
//        if (titleCell != null) {
//            titleCell.setCellValue("Institute Report: ${institute.name}")
//        }
//        if (titleCell != null) {
//            titleCell.cellStyle = workbook.createCellStyle().apply {
//                setAlignment(HorizontalAlignment.CENTER)
//            }
//        }
//
//        // Add spacing after the title
//        sheet.createRow(1) // Empty row for spacing
//
//        Log.d("ExcelCreation", "Adding Institute details to Excel")
//        // Add Institute details
//        var rowIndex = 3 // Start from the fourth row
//        sheet.createRow(rowIndex++).createCell(0).setCellValue("Institute Details")
//
//        // Add some spacing after the heading
//        sheet.createRow(rowIndex++)
//
//        sheet.createRow(rowIndex++).createCell(0).setCellValue("Name: ${institute.name}")
//        sheet.createRow(rowIndex++).createCell(0)
//            .setCellValue("Number of Teachers: ${institute.teachers.size}")
//        sheet.createRow(rowIndex++).createCell(0)
//            .setCellValue("Number of Subjects: ${institute.subjects.size}")
//        sheet.createRow(rowIndex++).createCell(0).setCellValue("Days per Week: ${institute.DAYS}")
//        sheet.createRow(rowIndex++).createCell(0).setCellValue("Hours per Day: ${institute.HOURS}")
//
//        Log.d("ExcelCreation", "Adding chosen subjects to Excel")
//        // Add chosen subjects
//        sheet.createRow(rowIndex++).createCell(0)
//            .setCellValue("Subjects: ${institute.subjects.joinToString(", ")}")
//
//        // Add spacing before Timetables heading
//        sheet.createRow(rowIndex++)
//
//        Log.d("ExcelCreation", "Adding timetables to Excel")
//        // Add all timetables
//        sheet.createRow(rowIndex++).createCell(0).setCellValue("Timetables")
//
//        // Add spacing after Timetables heading
//        sheet.createRow(rowIndex++)
//
//        institute.allTimetables.forEachIndexed { index, timetable ->
//            Log.d("ExcelCreation", "Adding Timetable ${index + 1} to Excel")
//
//            sheet.createRow(rowIndex++).createCell(0)
//                .setCellValue("Timetable ${index + 1} - ${timetable.className} ${timetable.section}")
//
//            // Add spacing before timetable details
//            sheet.createRow(rowIndex++)
//
//            // Add timetable details in a matrix form
//            // Header row (Days/Hours)
//            val timetableHeaderRow: Row = sheet.createRow(rowIndex++)
//            timetableHeaderRow.createCell(0).setCellValue("Day/Hour")
//            for (hour in 1..timetable.HOURS) {
//                timetableHeaderRow.createCell(hour).setCellValue("Hour $hour")
//            }
//
//            // Add rows for each day
//            for (day in 0 until timetable.DAYS) {
//                val timetableRow: Row = sheet.createRow(rowIndex++)
//                timetableRow.createCell(0).setCellValue("Day ${day + 1}")
//                for (hour in 0 until timetable.HOURS) {
//                    val subject = timetable.classTT[day][hour].first
//                    val teacher = timetable.classTT[day][hour].second
//                    timetableRow.createCell(hour + 1).setCellValue("$subject")
//                }
//            }
//
//            Log.d("ExcelCreation", "Adding Timetable Statistics to Excel")
//            // Adding Timetable Statistics
//            // Add spacing before timetable details
//            sheet.createRow(rowIndex++)
//
//            sheet.createRow(rowIndex++).createCell(0).setCellValue("Timetable Statistics")
//
//            // Add spacing before the statistics
//            sheet.createRow(rowIndex++)
//
//            val subjectCount = mutableMapOf<String, Int>()
//
//            // Count subjects
//            timetable.classTT.flatten().forEach { (subject, _) ->
//                subjectCount[subject] = subjectCount.getOrDefault(subject, 0) + 1
//            }
//
//            // Create a table for subjects and their counts
//            val subjectTableHeaderRow: Row = sheet.createRow(rowIndex++)
//            subjectTableHeaderRow.createCell(0).setCellValue("Subject")
//            subjectTableHeaderRow.createCell(1).setCellValue("Lectures: Allocated / Target")
//            subjectTableHeaderRow.createCell(2).setCellValue("Teacher")
//
//            // Add rows for each subject
//            subjectCount.forEach { (subject, count) ->
//                val subjectRow: Row = sheet.createRow(rowIndex++)
//                subjectRow.createCell(0).setCellValue(subject)
//                subjectRow.createCell(1)
//                    .setCellValue("${timetable.subPeriodsPerWeek[subject]} / $count")
//                subjectRow.createCell(2).setCellValue(timetable.chosenTeachers[subject] ?: "N/A")
//            }
//
//            // Optional: Add some spacing between timetables
//            rowIndex += 3 // Adding spacing before the next timetable
//        }
//
//        Log.d("ExcelCreation", "Closing the workbook")
//        // Write the workbook to the output stream
//        workbook.write(outputStream)
//        outputStream?.close()
//        workbook.close()
//
//        Log.d("ExcelCreation", "Excel saved successfully at $uri")
//    } catch (e: Exception) {
//        e.printStackTrace()
//        Log.e("ExcelCreation", "Error while saving Excel: ${e.message}")
//    }
//}
//
//
//fun saveAsPDF(context: Context, institute: Institute, uri: Uri) {
//    Log.d("PDFCreation", "Starting saveAsPDF function")
//    try {
////        val file = File(filePath)
////        if (file.parentFile?.exists() == false) {
////            Log.d("PDFCreation", "Creating directories for file path")
////            file.parentFile.mkdirs()
////        }
//        val outputStream: OutputStream? = context.contentResolver.openOutputStream(uri)
//
//        val writer = PdfWriter(outputStream)
//        val pdfDoc = PdfDocument(writer)
//        val document = Document(pdfDoc)
//
//        Log.d("PDFCreation", "Adding title to PDF")
//        // Add title
//        document.add(
//            Paragraph("Institute Report: ${institute.name}")
//                .setTextAlignment(TextAlignment.CENTER)
//                .setBold()
//                .setFontSize(24f)
//        )
//
//        Log.d("PDFCreation", "Adding Institute details to PDF")
//        // Add Institute details
//        document.add(
//            Paragraph("Institute Details")
//                .setBold()
//                .setFontSize(18f)
//                .setMarginTop(20f)
//        )
//
//        document.add(Paragraph("Name: ${institute.name}"))
//        document.add(Paragraph("Number of Teachers: ${institute.teachers.size}"))
//        document.add(Paragraph("Number of Subjects: ${institute.subjects.size}"))
//        document.add(Paragraph("Days per Week: ${institute.DAYS}"))
//        document.add(Paragraph("Hours per Day: ${institute.HOURS}"))
//
//        Log.d("PDFCreation", "Adding chosen subjects to PDF")
//        // Add chosen subjects
//        document.add(
//            Paragraph("Subjects: ${institute.subjects.joinToString(", ")}")
//                .setMarginTop(10f)
//        )
//
//        Log.d("PDFCreation", "Adding timetables to PDF")
//        // Add all timetables
//        document.add(
//            Paragraph("Timetables")
//                .setBold()
//                .setFontSize(18f)
//                .setMarginTop(20f)
//        )
//
//        institute.allTimetables.forEachIndexed { index, timetable ->
//            Log.d("PDFCreation", "Adding Timetable ${index + 1} to PDF")
//
//            document.add(
//                Paragraph("Timetable ${index + 1} - ${timetable.className} ${timetable.section}")
//                    .setBold()
//                    .setFontSize(16f)
//                    .setMarginTop(10f)
//            )
//
//            // Add timetable details in a matrix form
//            val table =
//                Table(UnitValue.createPercentArray(timetable.HOURS + 1)).useAllAvailableWidth()
//
//            // Add header row (Days/Hours)
//            table.addHeaderCell("Day/Hour")
//            for (hour in 1..timetable.HOURS) {
//                table.addHeaderCell("Hour $hour")
//            }
//            val subjectCount = mutableMapOf<String, Int>()
//
//            // Add rows for each day
//            for (day in 0 until timetable.DAYS) {
//                table.addCell("Day ${day + 1}")
//                for (hour in 0 until timetable.HOURS) {
//                    val subject = timetable.classTT[day][hour].first
//                    val teacher = timetable.classTT[day][hour].second
//                    table.addCell(subject)
//                    subjectCount[subject] = subjectCount.getOrDefault(subject, 0) + 1
//                }
//            }
//            document.add(table)
//            document.add(
//                Paragraph("Timetable Statistics")
//                    .setBold()
//                    .setFontSize(18f)
//                    .setMarginTop(20f)
//            )
//            // Create a table for subjects and their counts
//            val subjectTable = Table(UnitValue.createPercentArray(3)).useAllAvailableWidth()
//
//// Add header row
//            subjectTable.addHeaderCell("Subject")
//            subjectTable.addHeaderCell("Lectures: Allocated / target")
//            subjectTable.addHeaderCell("Teacher")
//
//// Add rows for each subject
//            subjectCount.forEach { (subject, count) ->
//                subjectTable.addCell(subject)
//                subjectTable.addCell(
//                    timetable.subPeriodsPerWeek[subject].toString() +
//                            " / " + count.toString()
//                )
//                subjectTable.addCell(
//                    timetable.chosenTeachers[subject] ?: "N/A"
//                ) // Use "N/A" if no teacher is chosen
//            }
//
//// Add the subject table to the document
//            document.add(Paragraph("\n\n\n"))
//        }
//
//
//        Log.d("PDFCreation", "Closing the document")
//        // Close the document
//        document.close()
//
//        Log.d("PDFCreation", "PDF saved successfully at")
//    } catch (e: Exception) {
//        e.printStackTrace()
//        Log.e("PDFCreation", "Error while saving PDF: ${e.message}")
//    }
//}
//@Composable
//fun LoadingScreen(gen: Int, level: Int) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//            .padding(vertical = 100.dp)
//            .zIndex(10f),
//        contentAlignment = Alignment.Center // Center the content
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
//            modifier = Modifier.padding(16.dp)
//        ) {
//
//            CircularProgressIndicator(
//                modifier = Modifier
//                    .padding(bottom = 24.dp)
//                    .size(50.dp), // Size of the progress indicator
//                color = colorResource(id = R.color.buttonLightPale) // Progress indicator color
//            )
//
//            Text(
//                text = "Timetables: ${gen * level} / ${level * level * 10}",
//                modifier = Modifier
//                    .padding(bottom = 10.dp)
//            )
//            Text(
//                text = "Notes:",
//                fontSize = FontSizes.body,
//                textAlign = TextAlign.Center,
//                color = Color.Gray,
//                modifier = Modifier.padding(bottom = 4.dp)
//            )
//            Text(
//                text = "Algo complexity: Higher the value = More Time = Better Timetable",
//                textAlign = TextAlign.Center,
//                color = Color.Gray,
//                fontSize = FontSizes.body,
//                modifier = Modifier.padding(bottom = 4.dp),
//            )
//            Text(
//                text = "As your timetable number grows try increase the algo complexity to get better non-overlapping teachers timetable",
//                textAlign = TextAlign.Center,
//                color = Color.Gray,
//                fontSize = FontSizes.body,
//                modifier = Modifier.padding(bottom = 4.dp),
//            )
//            Text(
//                text = "Download and edit the Excel sheet for a Perfect Timetable",
//                textAlign = TextAlign.Center,
//                color = Color.Gray,
////                fontSize = FontSizes.body,
//                modifier = Modifier.padding(bottom = 4.dp),
//            )
//
//            Text(
//                text = "Keep a copy of Institue input text in whatsapp / notes to reuse it in future",
//                textAlign = TextAlign.Center,
//                color = Color.Gray,
////                fontSize = FontSizes.body,
//                modifier = Modifier.padding(bottom = 16.dp),
//            )
//        }
//    }
//}
//
//
//// Coroutine function to create timetable asynchronously
//@OptIn(DelicateCoroutinesApi::class)
//fun createTimetableAsync(
//    selectedInstitute: School,
//    className: String,
//    section: String,
//    subPeriodsPerWeek: Map<String, Int>,
//    level: Int,
//    gen: Int,
//    updateGen: (Int) -> Unit,
//    onComplete: () -> Unit
//) {
//    GlobalScope.launch(Dispatchers.IO) {
////        createTimetable(className, section, subPeriodsPerWeek, createdAt, level)
//        val currentTimestamp = System.currentTimeMillis()
//        selectedInstitute.createTimetable(
//            className,
//            section, subPeriodsPerWeek,
//            currentTimestamp, level, gen, updateGen
//        )
//
//        withContext(Dispatchers.Main) {
//            onComplete()
//        }
//    }
//}
//
//@Composable
//fun SubjectSelection(
//    selectedInstitute: School, className: String, subPeriodsPerWeek: Map<String, Int>,
//    updateSubPeriods: (subject: String, periods: Int) -> Unit
//) {
//    val visibleSubjects: MutableSet<String> = mutableSetOf()
//    for (teacher in selectedInstitute.teachers) {
//        teacher.subjects.zip(teacher.classLevels).forEach { (subject, classLevel) ->
//            if ((className.toIntOrNull() ?: -1) in classLevel.first..classLevel.second) {
//                visibleSubjects.add(subject)
//            }
//        }
//    }
//    if (isNull(className.toIntOrNull())) {
//        Column {
//            Text(
//                text = "No Valid Classname given",
//                fontSize = FontSizes.subtitle,
//            )
//        }
//    } else {
//        Column() {
//
//            visibleSubjects.forEach { subject ->
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 4.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(
//                        text = subject,
//                        fontSize = FontSizes.body,
//                        modifier = Modifier.weight(1f)
//                    )
//                    OutlinedTextField(
//                        value = if (isNull(subPeriodsPerWeek[subject])) {
//                            updateSubPeriods(subject, 0)
//                            "0"
//                        } else {
//                            subPeriodsPerWeek[subject].toString()
//                        },
//                        onValueChange = {
//                            val p = it.toIntOrNull() ?: 0
//                            updateSubPeriods(subject, p)
//                        },
//                        label = {
//                            Text(
//                                "Periods/Week",
//                                fontSize = FontSizes.body,
//                            )
//                        },
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                        modifier = Modifier
//                            .weight(1f)
//                            .height(60.dp), // Adjust height
////                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp) // Adjust padding
//
//                    )
//                }
//                Divider(color = Color.Gray, thickness = 0.5.dp)
//            }
//
//        }
//    }
//}