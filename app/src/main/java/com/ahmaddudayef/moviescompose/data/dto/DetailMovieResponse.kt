package com.ahmaddudayef.moviescompose.data.dto


import com.ahmaddudayef.moviescompose.domain.model.DetailMovie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailMovieResponse(
    @Json(name = "adult")
    val adult: Boolean?,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "belongs_to_collection")
    val belongsToCollection: BelongsToCollection?,
    @Json(name = "budget")
    val budget: Int?,
    @Json(name = "genres")
    val genres: List<Genre?>?,
    @Json(name = "homepage")
    val homepage: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "imdb_id")
    val imdbId: String?,
    @Json(name = "origin_country")
    val originCountry: List<String?>?,
    @Json(name = "original_language")
    val originalLanguage: String?,
    @Json(name = "original_title")
    val originalTitle: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "popularity")
    val popularity: Double?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompany?>?,
    @Json(name = "production_countries")
    val productionCountries: List<ProductionCountry?>?,
    @Json(name = "release_date")
    val releaseDate: String?,
    @Json(name = "revenue")
    val revenue: Int?,
    @Json(name = "runtime")
    val runtime: Int?,
    @Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguage?>?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "tagline")
    val tagline: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "video")
    val video: Boolean?,
    @Json(name = "vote_average")
    val voteAverage: Double?,
    @Json(name = "vote_count")
    val voteCount: Int?
) {
    @JsonClass(generateAdapter = true)
    data class BelongsToCollection(
        @Json(name = "backdrop_path")
        val backdropPath: String?,
        @Json(name = "id")
        val id: Int?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "poster_path")
        val posterPath: String?
    )

    @JsonClass(generateAdapter = true)
    data class Genre(
        @Json(name = "id")
        val id: Int?,
        @Json(name = "name")
        val name: String?
    )

    @JsonClass(generateAdapter = true)
    data class ProductionCompany(
        @Json(name = "id")
        val id: Int?,
        @Json(name = "logo_path")
        val logoPath: String?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "origin_country")
        val originCountry: String?
    )

    @JsonClass(generateAdapter = true)
    data class ProductionCountry(
        @Json(name = "iso_3166_1")
        val iso31661: String?,
        @Json(name = "name")
        val name: String?
    )

    @JsonClass(generateAdapter = true)
    data class SpokenLanguage(
        @Json(name = "english_name")
        val englishName: String?,
        @Json(name = "iso_639_1")
        val iso6391: String?,
        @Json(name = "name")
        val name: String?
    )
}

fun DetailMovieResponse.toDetailMovie(): DetailMovie {
    return DetailMovie(
        id = id.toString(),
        title = title ?: "",
        posterUrl = "https://image.tmdb.org/t/p/w500$posterPath",
        overview = overview ?: "",
        releaseDate = releaseDate ?: "",
        rating = voteAverage?.toFloat() ?: 0.0f
    )
}