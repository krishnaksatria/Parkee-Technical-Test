package com.id.parkee.core.movie.data

import com.id.parkee.dto.movie.GetMovieListResponse

data class MovieListResponseModel(
    val page: Int = 0,
    val movieList: List<MovieDetailModel> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)

fun GetMovieListResponse.toModel(): MovieListResponseModel {
    return MovieListResponseModel(
        page ?: 0,
        results?.map { it.toModel() } ?: listOf(),
        totalPages ?: 0,
        totalResults ?: 0
    )
}
