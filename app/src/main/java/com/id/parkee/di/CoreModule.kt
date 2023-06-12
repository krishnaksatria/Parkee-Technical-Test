package com.id.parkee.di

import com.id.parkee.core.movie.data.MovieRepository
import com.id.parkee.core.movie.data.MovieService
import com.id.parkee.core.movie.domain.MovieRepositoryContract
import com.id.parkee.core.preferences.data.PreferenceRepository
import com.id.parkee.core.preferences.domain.PreferenceRepositoryContract
import org.koin.dsl.module
import retrofit2.Retrofit

val coreModule = module {
    single<PreferenceRepositoryContract> {
        PreferenceRepository(
            encryptedPreferencesContract = get()
        )
    }
    single<MovieRepositoryContract> {
        val retrofit: Retrofit = get()
        MovieRepository(
            movieService = retrofit.create(MovieService::class.java)
        )
    }
}
