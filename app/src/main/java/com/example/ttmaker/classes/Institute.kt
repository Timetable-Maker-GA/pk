package com.example.ttmaker.classes

import android.util.Log
import com.example.ttmaker.classes.types.TeacherInfo
import kotlin.random.Random


data class Institute(
    val name: String,
    val teachers: List<TeacherInfo>,
    val subjects: List<String>,
    val allTimetables: MutableList<Timetable> = mutableListOf(),
    val DAYS: Int,
    val HOURS: Int,
    val POPULATION_SIZE: Int,
    val GENERATIONS: Int,
    val id: String,
    val createdAt: Long
) {

    fun createTimetable(className: String, section: String, subPeriodsPerWeek: Map<String, Int>, createdAt: Long,
                        level: Int,
                        currentGeneration: Int,
                        updateGen: (Int) -> Unit
                        ) {
        val population = mutableListOf<Timetable>()
        val random = Random.Default
        var pop_count = level
        var gen_count = level*10
        Log.d("kxxk", level.toString())
        if (level < 10)  {
            pop_count = POPULATION_SIZE
            gen_count = GENERATIONS
        }
        repeat(pop_count) {
            Log.d("pppppppppppppppppppppppppppp", it.toString())
            // Initialize mutable variables
            val tt : Timetable = Timetable(className, section, teachers, subjects, subPeriodsPerWeek, DAYS, HOURS, createdAt)
            tt.calcFitness(allTimetables)
            population.add(tt)
        }
        repeat(gen_count) { generation ->
            updateGen(generation)
            Log.d("GENERATION", generation.toString())
            val newPopulation = mutableListOf<Timetable>()

            repeat(pop_count) {
                val a = population.random(random)
                val b = population.random(random)
                val parent1 = if (a.fitness > b.fitness) a else b
//                val parent1 = population.maxByOrNull { it.fitness } ?: population.first()

                val c = population.random(random)
                val d = population.random(random)
                val parent2 = if (c.fitness > d.fitness) c else d

                val child = crossOver1(parent1, parent2)
                mutate(child, 3, subjects)

                child.calcFitness(allTimetables)
                newPopulation.add(child)
            }

            population.clear()
            population.addAll(newPopulation)
        }

        println("One timetable created!")
        val bestTimetable = population.maxByOrNull { it.fitness } ?: throw IllegalStateException("Population is empty")
        allTimetables.add(bestTimetable)
    }

    private fun crossOver1(parent1: Timetable, parent2: Timetable): Timetable {
        val random = Random.Default
        val total = DAYS * HOURS
        val crossPoint = random.nextInt(total)
        val child = parent2.copy()

        val sameFirstPeriod = Random.nextBoolean() // 50% probability

        for (i in 0 until DAYS) {
            for (j in 1 until HOURS) {
                if (sameFirstPeriod && j == 0) continue
                if (i * HOURS + j < crossPoint) {
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
            val day = random.nextInt(DAYS)
            val hour = 1 + random.nextInt(HOURS-1)

            val newSubjectIndex = random.nextInt(subjects.size)
            val newSubject = subjects[newSubjectIndex]

            // Ensure newSubject has an associated teacher in chosenTeachers map
            val newTeacher = child.chosenTeachers[newSubject]

            // Update the timetable with the new subject and teacher
            child.classTT[day][hour] = newSubject to (newTeacher ?: "")
        }
    }

//    private fun crossOver2(parent1: Timetable, parent2: Timetable): Timetable {
//        val random = Random.Default
//        val periodCrossOverPoint = random.nextInt(HOURS)
//        val child = parent2.copy()
//
//        for (i in 0 until DAYS) {
//            for (j in 0 until periodCrossOverPoint) {
//                child.classTT[i][j] = parent1.classTT[i][j]
//                child.chosenTeacher[child.classTT[i][j].first]?.let {
//                    child.classTT[i][j] = child.classTT[i][j].copy(second = it)
//                }
//            }
//        }
//        return child
//    }
}