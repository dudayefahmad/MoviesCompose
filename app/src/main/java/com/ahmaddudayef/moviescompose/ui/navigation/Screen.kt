package com.ahmaddudayef.moviescompose.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object DetailContent : Screen("content/{id}/{contentType}") {
        fun createRoute(id: Long, contentType: String) = "content/$id/$contentType"
    }
    object Profile : Screen("profile")
    object Explore: Screen("explore")
}