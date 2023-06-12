package com.id.parkee.core.movie.data.reviews

import com.id.parkee.dto.movie.reviews.AuthorData

data class AuthorModel(
    val name: String = "",
    val username: String = "",
    val avatarPath: String = "",
    val rating: Float = 0F
)

fun AuthorData.toModel(): AuthorModel {
    return AuthorModel(
        name ?: "",
        username ?: "",
        avatarPath ?: "",
        rating ?: 0F
    )
}
