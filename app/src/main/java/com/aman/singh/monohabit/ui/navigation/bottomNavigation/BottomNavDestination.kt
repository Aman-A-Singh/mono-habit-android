package com.aman.singh.monohabit.ui.navigation.bottomNavigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.aman.singh.monohabit.ui.navigation.Destination

sealed class BottomNavDestination(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home : BottomNavDestination(
        route = Destination.Home.route,
        label = "Home",
        icon = Icons.Default.Home
    )

    object AddHabits : BottomNavDestination(
        route = Destination.AddHabit.route,
        label = "Add Habits",
        icon = Icons.Default.Add
    )

    object History : BottomNavDestination(
        route = Destination.History.route,
        label = "History",
        icon = Icons.AutoMirrored.Filled.List
    )

    object Stats : BottomNavDestination(
        route = Destination.Stats.route,
        label = "Stats",
        icon = Icons.Default.BarChart
    )


}