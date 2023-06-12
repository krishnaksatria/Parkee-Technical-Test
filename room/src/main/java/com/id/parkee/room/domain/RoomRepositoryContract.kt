package com.id.parkee.room.domain

import com.id.parkee.room.data.FavoriteMovieData

interface RoomRepositoryContract {
    fun insertFavoriteMovie(movie: FavoriteMovieData): Long
    fun removeFavoriteMovie(movie: FavoriteMovieData): Int
    fun getFavoriteMovieList(): List<FavoriteMovieData>
}
