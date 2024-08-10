package com.example.ttmaker.classes

import com.example.ttmaker.classes.types.TeacherInfo
import kotlin.math.abs
import kotlin.math.pow
import kotlin.random.Random

fun filterTeachersByClass(
    teachers: List<TeacherInfo>,
    subPeriodsPerWeek: Map<String, Int>,
    className: String
): List<TeacherInfo> {
    val filterTeachers: MutableList<TeacherInfo> = mutableListOf()

    for (teacher in teachers) {
        // Initialize mutable lists for filtering
        val filterSubjects: MutableList<String> = mutableListOf()
        val filterClassLevels: MutableList<Pair<Int, Int>> = mutableListOf()

        teacher.subjects.zip(teacher.classLevels).forEach { (subject, classLevel) ->
            // Check if the subject is present and if class level is within the desired range
            if (subPeriodsPerWeek.contains(subject) &&
                subPeriodsPerWeek[subject] != 0 &&
                className.toInt() in classLevel.first..classLevel.second) {
                filterSubjects.add(subject)
                filterClassLevels.add(classLevel)
            }
        }

        // If there are any filtered subjects and class levels, create a filtered TeacherInfo
        if (filterSubjects.isNotEmpty() && filterClassLevels.isNotEmpty()) {
            val filterTeacher = TeacherInfo(
                teacher.name,
                filterSubjects,
                filterClassLevels
            )
            // Add the filtered teacher to the list
            filterTeachers.add(filterTeacher)
        }
    }

    return filterTeachers
}

    data class Timetable(
        val className: String,
        val section: String  = "",
        val teachers: List<TeacherInfo>,
        val subjects: List<String>,
        val subPeriodsPerWeek: Map<String, Int>,
        val DAYS: Int,
        val HOURS: Int,
        val createdAt: Long,
        var fitness : Int = 0,
        val chosenTeachers: MutableMap<String, String> = mutableMapOf(),

    ) {
    val classTT: Array<Array<Pair<String, String>>> = Array(DAYS) { Array(HOURS) { Pair("", "") } }

 // One class's Week Time Table
    // Initialize tempSubPeriodsPerWeek
    val tempSubPeriodsPerWeek: MutableMap<String, Int> = mutableMapOf()

    init {
        for ((sub, _) in subPeriodsPerWeek){
            tempSubPeriodsPerWeek[sub] = 0
        }
        val filterTeachers = filterTeachersByClass(teachers, subPeriodsPerWeek, className)
        // Create a map of subjects to a list of teacher names
        val subjectsTeachers: MutableMap<String, MutableSet<String>> = mutableMapOf()
        for (teacher in filterTeachers) {
            for (subject in teacher.subjects) {
                if (!subjectsTeachers.containsKey(subject)){
                    subjectsTeachers[subject] = mutableSetOf()
                }
                subjectsTeachers[subject]?.add(teacher.name)
            }
        }
        // Randomly select a teacher for each subject
        chosenTeachers.clear() // Ensure the map is empty before adding new values
        for ((subject, teacherNames) in subjectsTeachers) {
            if (teacherNames.size != 0)            chosenTeachers[subject] = teacherNames.random()
        }
        var br = false
        val sameFirstPeriod = Random.nextInt(1, 101)

        var firstPeriodSubject: String = ""
        var firstPeriodTeacher: String = ""

        if (sameFirstPeriod < 75) {
            val subject = chosenTeachers.keys.random()
            firstPeriodSubject = subject
            firstPeriodTeacher = chosenTeachers[subject] ?: ""
        }
        for (i in 0 until DAYS) {
            val oneDay = Array(HOURS) { Pair("", "") }
            for (j in 0 until HOURS) {
                if (chosenTeachers.size == 0 || chosenTeachers.keys.size == 0) {
                    br = true
                    break
                }
                if (j == 0 && sameFirstPeriod < 75) {
                    // Set the same first period for all days
                    oneDay[j] = Pair(firstPeriodSubject, firstPeriodTeacher)
                    continue
                }
                val subject = chosenTeachers.keys.random() // Randomly choose a subject
                if (!chosenTeachers.containsKey((subject))){
                    break
                }
                val teacher = chosenTeachers[subject] ?: "" // Get the teacher for the chosen subject
                oneDay[j] = Pair(subject, teacher) // Assign the subject and teacher to the timetable
            }
            if (br) break
            classTT[i] = oneDay
        }
    }

    fun calcFitness(allTimetables: List<Timetable>) {
        fitness = 0
        fitness += teacherOverlapFitness(allTimetables, 400) // Preventing overlap is critical
        fitness += lecHoursFitness(allTimetables, 200) // High importance to match lecture hours
        fitness += sameSubjectPerDayFitness(allTimetables, 100) // Prevents excessive repetition in a day
        fitness += sameSubjectPeriodInWeekFitness(allTimetables, 50) // Ensures balanced weekly distribution
    }

    private fun sameSubjectPerDayFitness(allTimetables: List<Timetable>, rate: Int): Int {
        var fitness = 0
        for (day in 0 until DAYS) {
            val subjectOccurrences = mutableMapOf<String, Int>()

            // Count occurrences of each subject in the day
            for (hour in 0 until HOURS) {
                val subject = classTT[day][hour].first
                subjectOccurrences[subject] = subjectOccurrences.getOrDefault(subject, 0) + 1
            }

            // Penalize if any subject occurs more than twice
            for ((_, count) in subjectOccurrences) {
                if (count > 2) {
                    fitness -= (count - 2).toDouble().pow(2.0).toInt() * rate
                }
            }

            // Reward if subjects are well distributed
            if (subjectOccurrences.size == HOURS-1) {
                fitness += rate
            }else if (subjectOccurrences.size == HOURS){
                fitness += rate * rate * rate
            }
        }
        return fitness
    }


    private fun sameSubjectPeriodInWeekFitness(allTimetables: List<Timetable>, rate: Int): Int {
        var fitness = 0
        for (i in 0 until HOURS) {
            val temp = mutableSetOf<String>()
            for (j in 0 until DAYS) {
                temp.add(classTT[j][i].first)
            }
            if (temp.size == 1) fitness+= rate*rate*rate
            else if (temp.size==2) fitness+= rate*rate
            else       fitness -= temp.size.toDouble().pow(2.0).toInt() * rate
        }
        return fitness
    }

    private fun teacherOverlapFitness(allTimetables: List<Timetable>, rate: Int): Int {
        var fitness = 0
        for (i in 0 until DAYS) {
            for (j in 0 until HOURS) {
                val temp = mutableSetOf(classTT[i][j].second)
                for (k in allTimetables.indices) {
                    temp.add(allTimetables[k].classTT[i][j].second)
                }
                fitness += if (temp.size == allTimetables.size + 1) rate * 2 else -((temp.size) * rate)
            }
        }
        return fitness
    }

    private fun lecHoursFitness(allTimetables: List<Timetable>, rate: Int): Int {
        var fitness = 0


        // Calculate subject hours per week
        for (i in 0 until DAYS) {
            for (j in 0 until HOURS) {
                val subject = classTT[i][j].first
                tempSubPeriodsPerWeek[subject] = (tempSubPeriodsPerWeek[subject] ?: 0) + 1
            }
        }
        var total_diff = 0
        // Evaluate fitness based on tempSubPeriodsPerWeek
        for ((subject, periods) in tempSubPeriodsPerWeek) {
            val diff = abs(subPeriodsPerWeek[subject]?.minus(periods) ?: 0)
            total_diff += diff
            fitness -= diff * rate
        }
        if (total_diff == 0) fitness += rate*rate*rate
        else if (total_diff < 5) fitness += rate * rate
        return fitness
    }


    fun printStats() {
        println("Class: $className")
        println("Fitness: $fitness")
        println("\nSubject Hours Per Week: Need and Given")
        for ((subject, periods) in subPeriodsPerWeek) {
            println("$subject $tempSubPeriodsPerWeek[subject] / ${periods}")
        }
        println()
    }

    fun printClassTT() {
        val teacherColumnWidth = classTT.flatten().maxOf { it.first.length } + 2
        val subjectColumnWidth = classTT.flatten().maxOf { it.second.length } + 2

        println("Day X${" ".repeat(subjectColumnWidth - 1)}${(1 until HOURS).joinToString(" ") { "Period $it".padEnd(teacherColumnWidth + subjectColumnWidth - 1) }}")
        for (i in 0 until DAYS) {
            print("Day ${i + 1}")
            for (j in 0 until HOURS) {
                val (subject, teacher) = classTT[i][j]
                print(subject.padEnd(teacherColumnWidth) + teacher.padEnd(subjectColumnWidth))
            }
            println()
        }
    }

}

