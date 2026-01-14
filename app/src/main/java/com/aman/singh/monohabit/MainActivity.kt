package com.aman.singh.monohabit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aman.singh.monohabit.ui.navigation.AppNavHost
import com.aman.singh.monohabit.ui.navigation.bottomNavigation.BottomNavBar
import com.aman.singh.monohabit.ui.navigation.bottomNavigation.BottomNavDestination
import com.aman.singh.monohabit.ui.theme.MonoHabitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MonoHabitTheme {
                AppScaffold()
            }
        }
    }
}

@Composable
fun AppScaffold() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavItems = listOf(
        BottomNavDestination.Home,
        BottomNavDestination.AddHabits,
        BottomNavDestination.History,
        BottomNavDestination.Stats
    )

    val showBottomBar = bottomNavItems.any {
        it.route == currentRoute
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    navController = navController
                )
            }
        }
    ) { padding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MonoHabitTheme {
        Greeting("Android")
    }
}