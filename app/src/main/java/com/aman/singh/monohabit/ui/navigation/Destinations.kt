package com.aman.singh.monohabit.ui.navigation

sealed class Destination(val route: String) {

    object Home : Destination("home")

    object AddHabit : Destination("add_habit")
    object History : Destination("history")
    object Stats : Destination("stats")

}
