package com.id.parkee.room.data

import com.id.parkee.room.domain.RoomRepositoryContract

class RoomRepository(
    private val roomDao: RoomDao
) : RoomRepositoryContract {
    override fun insertFavoriteMovie(reminder: FavoriteMovieData): Long =
        roomDao.insertFavoriteMovie(reminder)

    override fun removeFavoriteMovie(reminder: FavoriteMovieData): Int =
        roomDao.removeFavoriteMovie(reminder)

    override fun getFavoriteMovieList(): List<FavoriteMovieData> = roomDao.getFavoriteMovieList()
}
