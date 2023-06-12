package com.id.parkee.core.movie.data

import android.os.Parcelable
import com.id.parkee.dto.movie.MovieDetailData
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailModel(
    val adult: Boolean = false,
    val backdropPath: String = "",
    val genreIds: List<Int> = listOf(),
    val id: Int = 0,
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Long = 0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Long = 0,
    val voteCount: Int = 0
) : Parcelable

fun MovieDetailData.toModel(): MovieDetailModel {
    return MovieDetailModel(
        adult ?: false,
        backdropPath ?: "",
        genreIds?.map { it } ?: listOf(),
        id ?: 0,
        originalLanguage ?: "",
        originalTitle ?: "",
        overview ?: "",
        (popularity ?: 0).toLong(),
        posterPath ?: "",
        releaseDate ?: "",
        title ?: "",
        video ?: false,
        (voteAverage ?: 0).toLong(),
        voteCount ?: 0
    )
}
