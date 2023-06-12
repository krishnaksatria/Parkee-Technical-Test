package com.id.parkee.core.movie.data.reviews

import com.id.parkee.dto.movie.reviews.MovieReviewData

data class MovieReviewModel(
    val author: String = "",
    val authorDetails: AuthorModel = AuthorModel(),
    val genreIds: List<Int> = listOf(),
    val content: String = "",
    val createdAt: String = "",
    val id: String = "",
    val updatedAt: String = "",
    val url: String = ""
)

fun MovieReviewData.toModel(): MovieReviewModel {
    return MovieReviewModel(
        author ?: "",
        authorDetails?.toModel() ?: AuthorModel(),
        genreIds?.map { it } ?: listOf(),
        content ?: "",
        createdAt ?: "",
        id ?: "",
        updatedAt ?: "",
        url ?: ""
    )
}
