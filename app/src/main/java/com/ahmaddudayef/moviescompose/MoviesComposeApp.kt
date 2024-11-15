package com.ahmaddudayef.moviescompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahmaddudayef.moviescompose.ui.navigation.NavigationItem
import com.ahmaddudayef.moviescompose.ui.navigation.Screen
import com.ahmaddudayef.moviescompose.ui.screens.movie.MovieScreen
import com.ahmaddudayef.moviescompose.ui.screens.profile.ProfileScreen
import com.ahmaddudayef.moviescompose.ui.theme.MoviesComposeTheme

@Composable
fun MoviesComposeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentState = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentState != Screen.DetailMovies.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Movies.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Movies.route) {
                MovieScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    userName = stringResource(R.string.profile_name),
                    userEmail = stringResource(R.string.email),
                    userImageUrl = stringResource(R.string.profile_photo)
                )
            }
        }
    }

}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentState = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_movies),
                icon = Icons.Default.PlayArrow,
                screen = Screen.Movies
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentState == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesComposeAppPreview() {
    MoviesComposeTheme {
        MoviesComposeApp()
    }
}

