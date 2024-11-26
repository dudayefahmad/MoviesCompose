package com.ahmaddudayef.moviescompose.data.dto


import com.ahmaddudayef.moviescompose.domain.model.TvShow
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvShowResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val tvShowDtos: List<TvShowDto>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)

@JsonClass(generateAdapter = true)
data class TvShowDto(
    @Json(name = "adult")
    val adult: Boolean?,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "first_air_date")
    val firstAirDate: String?,
    @Json(name = "genre_ids")
    val genreIds: List<Int?>?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "origin_country")
    val originCountry: List<String?>?,
    @Json(name = "original_language")
    val originalLanguage: String?,
    @Json(name = "original_name")
    val originalName: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "popularity")
    val popularity: Double?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "vote_average")
    val voteAverage: Double?,
    @Json(name = "vote_count")
    val voteCount: Int?
)

fun List<TvShowDto>.toListTvShow(): List<TvShow> {
    return map { it.toTvShow() }
}

fun TvShowDto.toTvShow(): TvShow {
    return TvShow(
        id = id.toString(),
        title = name.toString(),
        posterUrl = "https://image.tmdb.org/t/p/w500$posterPath",
        rating = voteAverage?.toFloat() ?: 0f
    )
}