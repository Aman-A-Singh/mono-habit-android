package com.aman.singh.monohabit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.aman.singh.monohabit.ui.navigation.addhabit.AddHabitScreen
import com.aman.singh.monohabit.ui.navigation.dashboard.DashboardScreen
import com.aman.singh.monohabit.ui.navigation.dashboard.DashboardScreen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {

    composable(Destination.Home.route) {
        HomeScreen(
            onAddHabit = {
                navController.navigate(Destination.AddHabit.route)
            },
            onHistoryClick = {
                navController.navigate(Destination.History.route)
            }
        )
    }

    composable(Destination.AddHabit.route) {
        AddHabitScreen(
            onBack = { navController.popBackStack() }
        )
    }

    composable(Destination.History.route) {
        HistoryScreen(
            onBack = { navController.popBackStack() }
        )
    }

    composable(Destination.Stats.route) {
        StatsScreen(
            onBack = { navController.popBackStack() }
        )
    }
}


@Composable
fun HomeScreen(
    onAddHabit: () -> Unit,
    onHistoryClick: () -> Unit
) {
    DashboardScreen(onAddHabit = onAddHabit)
}

@Composable
fun AddHabitScreen(
    onBack: () -> Unit
) {

}
@Composable
fun HistoryScreen(
    onBack: () -> Unit
) {

}


@Composable
fun StatsScreen(
    onBack: () -> Unit
) {

}