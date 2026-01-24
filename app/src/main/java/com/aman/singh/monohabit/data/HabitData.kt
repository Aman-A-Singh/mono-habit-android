package com.aman.singh.monohabit.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import com.aman.singh.monohabit.model.Habit

object HabitData {

    private val allHabits = mutableListOf(
        Habit(1, "Read 30 mins", 0, false, Icons.Default.MenuBook, Color(0xFF6750A4), "MenuBook"),
        Habit(2, "Drink Water", 0, true, Icons.Default.LocalDrink, Color(0xFF006D77), "LocalDrink"),
        Habit(3, "Morning Workout", 0, false, Icons.Default.FitnessCenter, Color(0xFFB94053), "FitnessCenter"),
        Habit(4, "Meditate", 0, true, Icons.Default.SelfImprovement, Color(0xFF725B00), "SelfImprovement"),
        Habit(5, "Write Journal", 0, false, Icons.Default.Book, Color(0xFF4A6A86), "Book"),
        Habit(6, "Write Journal", 0, false, Icons.Default.Book, Color(0xFF4A6A86), "Book"),
        Habit(7, "Write Journal", 0, false, Icons.Default.Book, Color(0xFF4A6A86), "Book"),
        Habit(8, "Write Journal", 0, false, Icons.Default.Book, Color(0xFF4A6A86), "Book"),
        Habit(9, "Write Journal", 0, false, Icons.Default.Book, Color(0xFF4A6A86), "Book"),
        Habit(10, "Write Journal", 0, false, Icons.Default.Book, Color(0xFF4A6A86), "Book"),
        Habit(11, "Write Journal", 0, false, Icons.Default.Book, Color(0xFF4A6A86), "Book"),
        Habit(12, "Write Journal", 0, false, Icons.Default.Book, Color(0xFF4A6A86), "Book"),
        Habit(13, "Write Journal", 0, false, Icons.Default.Book, Color(0xFF4A6A86), "Book"),
    )

    fun getAllHabits(): List<Habit> {
        return allHabits.toList()
    }

    fun addHabit(habit: Habit) {
        val newId = (allHabits.maxOfOrNull { it.id } ?: 0) + 1
        allHabits.add(habit.copy(id = newId))
    }

    fun updateHabit(habit: Habit) {
        val index = allHabits.indexOfFirst { it.id == habit.id }
        if (index != -1) {
            allHabits[index] = habit
        }
    }

    fun deleteHabit(habit: Habit) {
        allHabits.removeAll { it.id == habit.id }
    }
}
