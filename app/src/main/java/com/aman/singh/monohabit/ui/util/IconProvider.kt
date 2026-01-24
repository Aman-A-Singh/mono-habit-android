package com.aman.singh.monohabit.ui.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

object IconProvider {
    val icons = mapOf(
        "MenuBook" to Icons.Default.MenuBook,
        "LocalDrink" to Icons.Default.LocalDrink,
        "FitnessCenter" to Icons.Default.FitnessCenter,
        "SelfImprovement" to Icons.Default.SelfImprovement,
        "Book" to Icons.Default.Book,
        "Face" to Icons.Default.Face,
        "Home" to Icons.Default.Home,
        "Work" to Icons.Default.Work,
        "Pets" to Icons.Default.Pets,
        "SportsEsports" to Icons.Default.SportsEsports,
        "MusicNote" to Icons.Default.MusicNote,
        "Brush" to Icons.Default.Brush,
        "MonitorHeart" to Icons.Default.MonitorHeart,
        "Bedtime" to Icons.Default.Bedtime,
        "Lightbulb" to Icons.Default.Lightbulb,
        "Star" to Icons.Default.Star,
        "Favorite" to Icons.Default.Favorite,
        "ThumbUp" to Icons.Default.ThumbUp,
        "Build" to Icons.Default.Build,
        "Alarm" to Icons.Default.Alarm
    )

    fun getIcon(name: String): ImageVector {
        return icons[name] ?: Icons.Default.Star // Return a default icon if not found
    }

    fun getIconName(icon: ImageVector): String {
        return icons.entries.find { it.value == icon }?.key ?: "Star"
    }
}
