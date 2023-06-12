package com.id.parkee.di

import com.id.parkee.features.dashboard.DashboardActivity
import com.id.parkee.features.dashboard.DashboardViewModel
import com.id.parkee.features.dashboard.MovieAdapter
import com.id.parkee.features.dashboard.MovieBannerAdapter
import com.id.parkee.features.favorite.FavoriteActivity
import com.id.parkee.features.favorite.FavoriteAdapter
import com.id.parkee.features.favorite.FavoriteViewModel
import com.id.parkee.features.movie.MovieDetailActivity
import com.id.parkee.features.movie.MovieDetailViewModel
import com.id.parkee.features.movie.ReviewAdapter
import com.id.parkee.features.splash.SplashActivity
import com.id.parkee.features.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val featureModule = module {
    scope(named<SplashActivity>()) {
        viewModel {
            SplashViewModel(
                preferenceRepositoryContract = get()
            )
        }
    }
    scope(named<DashboardActivity>()) {
        viewModel {
            DashboardViewModel(
                movieRepositoryContract = get()
            )
        }
        scoped { MovieBannerAdapter() }
        scoped(named(DashboardActivity.TYPE_TOP_RATED)) {
            MovieAdapter()
        }
        scoped(named(DashboardActivity.TYPE_NOW_PLAYING)) {
            MovieAdapter()
        }
    }
    scope(named<MovieDetailActivity>()) {
        viewModel {
            MovieDetailViewModel(
                movieRepositoryContract = get(),
                roomRepositoryContract = get()
            )
        }
        scoped { ReviewAdapter() }
    }
    scope(named<FavoriteActivity>()) {
        viewModel {
            FavoriteViewModel(
                roomRepositoryContract = get()
            )
        }
        scoped { FavoriteAdapter() }
    }
}
