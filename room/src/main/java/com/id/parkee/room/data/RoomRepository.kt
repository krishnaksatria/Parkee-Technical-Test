package com.id.parkee.room.data

import com.id.parkee.room.domain.RoomRepositoryContract

class RoomRepository(
    private val roomDao: RoomDao
) : RoomRepositoryContract {
    override fun insertFavoriteMovie(movie: FavoriteMovieData): Long =
        roomDao.insertFavoriteMovie(movie)

    override fun removeFavoriteMovie(movie: FavoriteMovieData): Int =
        roomDao.removeFavoriteMovie(movie)

    override fun getFavoriteMovieList(): List<FavoriteMovieData> = roomDao.getFavoriteMovieList()
}
