package com.aman.singh.monohabit.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Habit(
    val id: Int,
    val title: String,
    val streak: Int,
    val isCompleted: Boolean,
    val icon: ImageVector,
    val color: Color,
    val iconName: String // Added to help with saving/retrieving
)
