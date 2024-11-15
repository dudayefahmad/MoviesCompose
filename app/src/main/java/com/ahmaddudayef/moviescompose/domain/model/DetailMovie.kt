package com.ahmaddudayef.moviescompose.domain.model

data class DetailMovie(
    val id: String,
    val title: String,
    val posterUrl: String,
    val overview: String,
    val releaseDate: String,
    val rating: Float
)