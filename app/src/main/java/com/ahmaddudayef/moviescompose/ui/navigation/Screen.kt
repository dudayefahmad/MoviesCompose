package com.ahmaddudayef.moviescompose.ui.navigation

sealed class Screen(val route: String) {
    object Movies : Screen("movies")
    object DetailMovies : Screen("movies/{movieId}")
    object Profile: Screen("profile")
}