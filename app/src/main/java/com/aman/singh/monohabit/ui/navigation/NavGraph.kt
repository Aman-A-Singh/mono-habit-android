package com.aman.singh.monohabit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {

    composable(Destination.Home.route) {
        HomeScreen(
            onAddCheckIn = {
                navController.navigate(Destination.AddHabit.route)
            },
            onHistoryClick = {
                navController.navigate(Destination.History.route)
            }
        )
    }

    composable(Destination.AddHabit.route) {
        AddCheckInScreen(
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
    onAddCheckIn: () -> Unit,
    onHistoryClick: () -> Unit
) {
}

@Composable
fun AddCheckInScreen(
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