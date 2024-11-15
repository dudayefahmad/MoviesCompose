package com.ahmaddudayef.moviescompose.ui.navigation

sealed class Screen(val route: String) {
    object Movies : Screen("movies")
    object DetailMovie : Screen("movie/{movieId}") {
        fun createRoute(movieId: Long) = "movie/$movieId"
    }
    object Profile : Screen("profile")
}