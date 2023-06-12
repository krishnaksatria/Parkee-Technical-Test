package com.id.parkee.dto.movie.reviews

import com.google.gson.annotations.SerializedName

data class MovieReviewData(
    val author: String?,
    @SerializedName("author_details") val authorDetails: AuthorData?,
    @SerializedName("genre_ids") val genreIds: List<Int>?,
    val content: String?,
    @SerializedName("created_at") val createdAt: String?,
    val id: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    val url: String?
)
