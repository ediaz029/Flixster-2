package com.codepath.articlesearch

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class SearchNewResultsMovie(
    @SerialName("results")
    val results: List<Movie>?
)

@Keep
@Serializable
data class Movie(
    @SerialName("title")
    val title: String?,

    @SerialName("backdrop_path")
    val imageUrl: String?,

    @SerialName("release_date")
    val releaseDate: String?,

    @SerialName("overview")
    val summary: String?,

    @SerialName("vote_average")
    val vote: String?,

) : java.io.Serializable
