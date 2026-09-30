package com.mvd.applicant.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mvd.applicant.ui.calculator.CalculatorScreen
import com.mvd.applicant.ui.technique.TechniqueScreen
import com.mvd.applicant.ui.training.TrainingInputScreen
import com.mvd.applicant.ui.video.VideoScreen

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    data object Calculator : Screen("calculator", "Калькулятор", Icons.Default.Calculate)
    data object Technique : Screen("technique", "Техника", Icons.Default.FitnessCenter)
    data object Video : Screen("video", "Видео", Icons.Default.PlayCircle)
    data object Training : Screen("training", "Подготовка", Icons.Default.EventNote)
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val items = listOf(Screen.Calculator, Screen.Technique, Screen.Video, Screen.Training)

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            indicatorColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                            unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                        )
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Calculator.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Calculator.route) { CalculatorScreen() }
            composable(Screen.Technique.route) { TechniqueScreen() }
            composable(Screen.Video.route) { VideoScreen() }
            composable(Screen.Training.route) { TrainingInputScreen() }
        }
    }
}
