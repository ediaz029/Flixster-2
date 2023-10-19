package com.codepath.articlesearch

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class SearchNewResultsCelebrity(
    @SerialName("results")
    val results: List<Celebrity>?
)

@Keep
@Serializable
data class Celebrity(
    @SerialName("name")
    val name: String?,

    @SerialName("popularity")
    val popularity: String?,

    @SerialName("profile_path")
    val imageUrl: String?,

    @SerialName("known_for_department")
    val knownFor: String?,

    @SerialName("known_for")
    val knownformovies: List<KnownForMovie>,

    ) : java.io.Serializable

@Keep
@Serializable
data class KnownForMovie(
    @SerialName("title")
    val title: String?,

    @SerialName("overview")
    val overview: String?,
) : java.io.Serializable


