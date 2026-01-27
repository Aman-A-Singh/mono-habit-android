package com.aman.singh.monohabit.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import com.aman.singh.monohabit.model.Habit

object HabitData {

    private val allHabits = mutableListOf(
        Habit(1, "Read 30 mins", 0, false, Icons.Default.MenuBook, Color(0xFF6750A4), "MenuBook")
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
