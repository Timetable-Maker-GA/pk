package com.example.ttmaker.presentation.addTimeTable
import com.itextpdf.kernel.pdf.PdfDocument

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ttmaker.classes.Timetable
import com.example.ttmaker.model.SchoolEntity
import com.example.ttmaker.data.SchoolRepository
import com.example.ttmaker.model.SchoolBasicInfo
import kotlinx.coroutines.launch
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.UnitValue
//import com.example.ttmaker.R

import kotlinx.coroutines.delay
import java.io.OutputStream

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import kotlin.random.Random

class AddTimeTableViewModel(private val repository: SchoolRepository) : ViewModel() {

    // Access the application context
    var expanded = mutableStateOf(false)
        private set
    var className = mutableStateOf("")
        private set
    var section = mutableStateOf("")
        private set
    var gen = mutableStateOf(0)
        private set
    var isCreating = mutableStateOf(false)
        private set
    var selectedSchool = mutableStateOf<SchoolEntity?>(null)
        private set
    var schoolList = mutableStateOf<List<SchoolBasicInfo>>(emptyList())
        private set

    var subPeriodsPerWeek = mutableStateOf(mapOf<String, Int>())
        private set
    var level = mutableStateOf(1f )
        private set
    private fun updateGen(g: Int){
        gen.value  = gen.value+1
    }

    fun updateExpanded(value: Boolean) {
        expanded.value = value
    }

    fun updateClassName(value: String) {
        className.value = value
    }

    fun updateSection(value: String) {
        section.value = value
    }

    fun updateIsCreating(value: Boolean) {
        isCreating.value = value
    }

    fun updateSubPeriodsPerWeek(key: String, value: Int) {
        subPeriodsPerWeek.value = subPeriodsPerWeek.value.toMutableMap().also { it[key] = value }
    }
    fun clearSubPeriodsPerWeek() {
        subPeriodsPerWeek.value = emptyMap()
    }

    fun updateSelectedSchool(id: Int) {
        viewModelScope.launch {
            selectedSchool.value = repository.getSchoolById(id) // Ensure this method is implemented in your repository
        }
    }

    fun updateLevel(value: Float) {
        level.value = value
    }
    init {
        fetchSchools()
    }
    private fun fetchSchools() {
        viewModelScope.launch {
            val schools = repository.getAllSchoolsBasicInfo()
            schoolList.value = schools // Update state
        }
    }

    fun saveAsExcel(context: Context,  uri: Uri) {
        viewModelScope.launch {
            if (selectedSchool.value == null) {
                Toast.makeText(context, "No School Selected", Toast.LENGTH_SHORT).show()
                return@launch
            }
            Log.d("ExcelCreation", "Starting saveAsExcel function")
            try {
                val outputStream: OutputStream? = context.contentResolver.openOutputStream(uri)
                val workbook: Workbook = XSSFWorkbook() // Create a new workbook
                val sheet: Sheet = workbook.createSheet("School Report") // Create a sheet

                Log.d("ExcelCreation", "Adding title to Excel")
                // Add title
                val titleRow: Row = sheet.createRow(0)
                val titleCell: org.apache.poi.ss.usermodel.Cell? = titleRow.createCell(0)

                if (titleCell != null) {
                    titleCell.setCellValue("School Report: ${selectedSchool.value!!.name}")
                }
                if (titleCell != null) {
                    titleCell.cellStyle = workbook.createCellStyle().apply {
                        setAlignment(HorizontalAlignment.CENTER)
                    }
                }

                // Add spacing after the title
                sheet.createRow(1) // Empty row for spacing

                Log.d("ExcelCreation", "Adding School details to Excel")
                // Add School details
                var rowIndex = 3 // Start from the fourth row
                sheet.createRow(rowIndex++).createCell(0).setCellValue("School Details")

                // Add some spacing after the heading
                sheet.createRow(rowIndex++)

                sheet.createRow(rowIndex++).createCell(0)
                    .setCellValue("Name: ${selectedSchool.value!!.name}")
                sheet.createRow(rowIndex++).createCell(0)
                    .setCellValue("Number of Teachers: ${selectedSchool.value!!.teachers.size}")
                sheet.createRow(rowIndex++).createCell(0)
                    .setCellValue("Number of Subjects: ${selectedSchool.value!!.subjects.size}")
                sheet.createRow(rowIndex++).createCell(0)
                    .setCellValue("Days per Week: ${selectedSchool.value!!.DAYS}")
                sheet.createRow(rowIndex++).createCell(0)
                    .setCellValue("Hours per Day: ${selectedSchool.value!!.HOURS}")

                Log.d("ExcelCreation", "Adding chosen subjects to Excel")
                // Add chosen subjects
                sheet.createRow(rowIndex++).createCell(0)
                    .setCellValue("Subjects: ${selectedSchool.value!!.subjects.joinToString(", ")}")

                // Add spacing before Timetables heading
                sheet.createRow(rowIndex++)

                Log.d("ExcelCreation", "Adding timetables to Excel")
                // Add all timetables
                sheet.createRow(rowIndex++).createCell(0).setCellValue("Timetables")

                // Add spacing after Timetables heading
                sheet.createRow(rowIndex++)

                selectedSchool.value!!.allTimetables.forEachIndexed { index, timetable ->
                    Log.d("ExcelCreation", "Adding Timetable ${index + 1} to Excel")

                    sheet.createRow(rowIndex++).createCell(0)
                        .setCellValue("Timetable ${index + 1}- ${timetable.className} ${timetable.section}")

                    // Add spacing before timetable details
                    sheet.createRow(rowIndex++)

                    // Add timetable details in a matrix form
                    // Header row (Days/Hours)
                    val timetableHeaderRow: Row = sheet.createRow(rowIndex++)
                    timetableHeaderRow.createCell(0).setCellValue("Day/Hour")
                    for (hour in 1..timetable.HOURS) {
                        timetableHeaderRow.createCell(hour).setCellValue("Hour $hour")
                    }

                    // Add rows for each day
                    for (day in 0 until timetable.DAYS) {
                        val timetableRow: Row = sheet.createRow(rowIndex++)
                        timetableRow.createCell(0).setCellValue("Day ${day + 1}")
                        for (hour in 0 until timetable.HOURS) {
                            val subject = timetable.classTT[day][hour].first
                            val teacher = timetable.classTT[day][hour].second
                            timetableRow.createCell(hour + 1).setCellValue("$subject")
                        }
                    }

                    Log.d("ExcelCreation", "Adding Timetable Statistics to Excel")
                    // Adding Timetable Statistics
                    // Add spacing before timetable details
                    sheet.createRow(rowIndex++)

                    sheet.createRow(rowIndex++).createCell(0).setCellValue("Timetable Statistics")

                    // Add spacing before the statistics
                    sheet.createRow(rowIndex++)

                    val subjectCount = mutableMapOf<String, Int>()

                    // Count subjects
                    timetable.classTT.flatten().forEach { (subject, _) ->
                        subjectCount[subject] = subjectCount.getOrDefault(subject, 0) + 1
                    }

                    // Create a table for subjects and their counts
                    val subjectTableHeaderRow: Row = sheet.createRow(rowIndex++)
                    subjectTableHeaderRow.createCell(0).setCellValue("Subject")
                    subjectTableHeaderRow.createCell(1).setCellValue("Lectures: Allocated / Target")
                    subjectTableHeaderRow.createCell(2).setCellValue("Teacher")

                    // Add rows for each subject
                    subjectCount.forEach { (subject, count) ->
                        val subjectRow: Row = sheet.createRow(rowIndex++)
                        subjectRow.createCell(0).setCellValue(subject)
                        subjectRow.createCell(1)
                            .setCellValue("${timetable.subPeriodsPerWeek[subject]} / $count")
                        subjectRow.createCell(2)
                            .setCellValue(timetable.chosenTeachers[subject] ?: "N/A")
                    }

                    // Optional: Add some spacing between timetables
                    rowIndex += 3 // Adding spacing before the next timetable
                }

                Log.d("ExcelCreation", "Closing the workbook")
                // Write the workbook to the output stream
                workbook.write(outputStream)
                outputStream?.close()
                workbook.close()

                Log.d("ExcelCreation", "Excel saved successfully at $uri")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ExcelCreation", "Error while saving Excel: ${e.message}")
            }
        }
    }

    fun saveAsPDF(context: Context, uri: Uri) {
        viewModelScope.launch {
            if (selectedSchool.value == null) {
                Toast.makeText(context, "No School Selected", Toast.LENGTH_SHORT).show()
                return@launch
            }
            Log.d("PDFCreation", "Starting saveAsPDF function")
            try {
                val outputStream: OutputStream? = context.contentResolver.openOutputStream(uri)

                val writer = PdfWriter(outputStream)
                val pdfDoc = PdfDocument(writer)
                val document = Document(pdfDoc)

                Log.d("PDFCreation", "Adding title to PDF")
                // Add title
                document.add(
                    Paragraph("Institute Report: ${selectedSchool.value!!.name}")
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBold()
                        .setFontSize(24f)
                )

                Log.d("PDFCreation", "Adding Institute details to PDF")
                // Add Institute details
                document.add(
                    Paragraph("Institute Details")
                        .setBold()
                        .setFontSize(18f)
                        .setMarginTop(20f)
                )

                document.add(Paragraph("Name: ${selectedSchool.value!!.name}"))
                document.add(Paragraph("Number of Teachers: ${selectedSchool.value!!.teachers.size}"))
                document.add(Paragraph("Number of Subjects: ${selectedSchool.value!!.subjects.size}"))
                document.add(Paragraph("Days per Week: ${selectedSchool.value!!.DAYS}"))
                document.add(Paragraph("Hours per Day: ${selectedSchool.value!!.HOURS}"))

                Log.d("PDFCreation", "Adding chosen subjects to PDF")
                // Add chosen subjects
                document.add(
                    Paragraph("Subjects: ${selectedSchool.value!!.subjects.joinToString(", ")}")
                        .setMarginTop(10f)
                )

                Log.d("PDFCreation", "Adding timetables to PDF")
                // Add all timetables
                document.add(
                    Paragraph("Timetables")
                        .setBold()
                        .setFontSize(18f)
                        .setMarginTop(20f)
                )

                selectedSchool.value!!.allTimetables.forEachIndexed { index, timetable ->
                    Log.d("PDFCreation", "Adding Timetable ${index + 1} to PDF")

                    document.add(
                        Paragraph("Timetable ${index + 1}- ${timetable.className} ${timetable.section}")
                            .setBold()
                            .setFontSize(16f)
                            .setMarginTop(10f)
                    )

                    // Add timetable details in a matrix form
                    val table =
                        Table(UnitValue.createPercentArray(timetable.HOURS + 1)).useAllAvailableWidth()

                    // Add header row (Days/Hours)
                    table.addHeaderCell("Day/Hour")
                    for (hour in 1..timetable.HOURS) {
                        table.addHeaderCell("Hour $hour")
                    }

                    val subjectCount = mutableMapOf<String, Int>()

                    // Add rows for each day
                    for (day in 0 until timetable.DAYS) {
                        table.addCell("Day ${day + 1}")
                        for (hour in 0 until timetable.HOURS) {
                            val subject = timetable.classTT[day][hour].first
                            val teacher = timetable.classTT[day][hour].second
                            table.addCell(subject)
                            subjectCount[subject] = subjectCount.getOrDefault(subject, 0) + 1
                        }
                    }
                    document.add(table)

                    Log.d("PDFCreation", "Adding Timetable Statistics to PDF")
                    // Add timetable statistics header
                    document.add(
                        Paragraph("Timetable Statistics")
                            .setBold()
                            .setFontSize(18f)
                            .setMarginTop(20f)
                    )

                    // Create a table for subjects and their counts
                    val subjectTable = Table(UnitValue.createPercentArray(3)).useAllAvailableWidth()

                    // Add header row
                    subjectTable.addHeaderCell("Subject")
                    subjectTable.addHeaderCell("Lectures: Allocated / Target")
                    subjectTable.addHeaderCell("Teacher")

                    // Add rows for each subject
                    subjectCount.forEach { (subject, count) ->
                        subjectTable.addCell(subject)
                        subjectTable.addCell(
                            "${timetable.subPeriodsPerWeek[subject]} / $count"
                        )
                        subjectTable.addCell(
                            timetable.chosenTeachers[subject] ?: "N/A"
                        ) // Use "N/A" if no teacher is chosen
                    }

                    // Add the subject table to the document
                    document.add(subjectTable)

                    // Optional: Add some spacing between timetables
                    document.add(Paragraph("\n\n"))
                }


                Log.d("PDFCreation", "Closing the document")
                // Close the document
                document.close()

                Log.d("PDFCreation", "PDF saved successfully at")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("PDFCreation", "Error while saving PDF: ${e.message}")
            }
        }
    }


    private fun crossOver1(parent1: Timetable, parent2: Timetable): Timetable {
        val random = Random.Default
        val total = selectedSchool.value!!.DAYS * selectedSchool.value!!.HOURS
        val crossPoint = random.nextInt(total)
        val child = parent2.copy()

        val sameFirstPeriod = Random.nextBoolean() // 50% probability

        for (i in 0 until selectedSchool.value!!.DAYS) {
            for (j in 1 until selectedSchool.value!!.HOURS) {
                if (sameFirstPeriod && j == 0) continue
                if (i * selectedSchool.value!!.HOURS + j < crossPoint) {
                    child.classTT[i][j] = parent1.classTT[i][j]
                    child.chosenTeachers[child.classTT[i][j].first]?.let {
                        child.classTT[i][j] = child.classTT[i][j].copy(second = it)
                    }
                }
            }
        }
        return child
    }

    private fun mutate(child: Timetable, maxMutations: Int, subjects: List<String>) {
        val random = Random.Default
        val numMutations = random.nextInt(maxMutations + 1)

        repeat(numMutations) {
            val day = random.nextInt(selectedSchool.value!!.DAYS)
            val hour = 1 + random.nextInt(selectedSchool.value!!.HOURS-1)

            val newSubjectIndex = random.nextInt(subjects.size)
            val newSubject = subjects[newSubjectIndex]

            // Ensure newSubject has an associated teacher in chosenTeachers map
            val newTeacher = child.chosenTeachers[newSubject]

            // Update the timetable with the new subject and teacher
            child.classTT[day][hour] = newSubject to (newTeacher ?: "")
        }
    }

    private fun checkTTInput(context: Context): Boolean{
        if (selectedSchool.value == null) {
            Toast.makeText(context, "No School Selected", Toast.LENGTH_SHORT).show()
            return false
        }
        val totalPeriods = subPeriodsPerWeek.value.values.sum()
        val expectedPeriods = ((selectedSchool.value?.HOURS ?: 0) * (selectedSchool.value?.DAYS
            ?: 0))

        if (totalPeriods != expectedPeriods) {
            Toast.makeText(context, "Fill all lectures of week", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    fun createTT(context: Context) {
        viewModelScope.launch {
            if (!checkTTInput(context)) return@launch
            updateIsCreating(!isCreating.value)
            Toast.makeText(context, "Creating Timetable....", Toast.LENGTH_SHORT).show()
            delay(1500L)  // 2 seconds delay
            val population = mutableListOf<Timetable>()
            val random = Random.Default
            var pop_count = selectedSchool.value!!.POPULATION_SIZE * level.value
            var gen_count = selectedSchool.value!!.GENERATIONS * level.value
            Log.d("kxxk", level.toString())
            repeat(pop_count.toInt()*10) {
                // Initialize mutable variables
                val tt: Timetable = Timetable(
                    className.value, section.value,
                    selectedSchool.value!!.teachers,
                    selectedSchool.value!!.subjects,
                    subPeriodsPerWeek.value,
                    selectedSchool.value!!.DAYS, selectedSchool.value!!.HOURS,
                    selectedSchool.value!!.createdAt
                )

                tt.calcFitness(selectedSchool.value!!.allTimetables)
                population.add(tt)
            }
            repeat(gen_count.toInt()) { generation ->
                updateGen(generation)
                Log.d("GENERATION", generation.toString())
                val newPopulation = mutableListOf<Timetable>()

                repeat(pop_count.toInt()) {
                    val a = population.random(random)
                    val b = population.random(random)
                    val parent1 = if (a.fitness > b.fitness) a else b
//                val parent1 = population.maxByOrNull { it.fitness } ?: population.first()

                    val c = population.random(random)
                    val d = population.random(random)
                    val parent2 = if (c.fitness > d.fitness) c else d

                    val child = crossOver1(parent1, parent2)
                    mutate(child, 3, subPeriodsPerWeek.value.keys.toList())

                    child.calcFitness(selectedSchool.value!!.allTimetables)
                    newPopulation.add(child)
                }

                population.clear()
                population.addAll(newPopulation)
            }

            println("One timetable created!")
            val bestTimetable = population.maxByOrNull { it.fitness }
                ?: throw IllegalStateException("Population is empty")

            val updatedTimetables = selectedSchool.value!!.allTimetables.toMutableList()
            updatedTimetables.add(bestTimetable)
            selectedSchool.value = selectedSchool.value!!.copy(allTimetables = updatedTimetables)

            repository.updateTimetableAndCount(selectedSchool.value!!.id, selectedSchool.value!!.allTimetables, selectedSchool.value!!.timetableCount+1)
            Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
            gen.value = 0
            updateIsCreating(!isCreating.value)
            clearSubPeriodsPerWeek()
        }
    }

    private fun crossOver2(parent1: Timetable, parent2: Timetable): Timetable {
        val random = Random.Default
        val periodCrossOverPoint = random.nextInt(selectedSchool.value!!.HOURS)
        val child = parent2.copy()

        for (i in 0 until selectedSchool.value!!.DAYS) {
            for (j in 0 until periodCrossOverPoint) {
                child.classTT[i][j] = parent1.classTT[i][j]
                child.chosenTeachers[child.classTT[i][j].first]?.let {
                    child.classTT[i][j] = child.classTT[i][j].copy(second = it)
                }
            }
        }
        return child
    }


}

class AddTimeTableViewModelFactory(private val repository: SchoolRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddTimeTableViewModel::class.java)) {
            return AddTimeTableViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class in AddTimeTableViewModel")
    }
}
