package com.id.parkee.di

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.id.parkee.room.data.FavoriteMovieData
import com.id.parkee.room.data.RoomDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomCoreModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            ParkeeDatabase::class.java,
            ParkeeDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}

@Database(
    entities = [
        FavoriteMovieData::class
    ],
    version = 1,
    exportSchema = false
)

abstract class ParkeeDatabase : RoomDatabase() {

    abstract fun roomDao(): RoomDao

    companion object {
        const val DATABASE_NAME = "parkee.db"
    }
}
