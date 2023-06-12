package com.id.parkee.di

import android.content.Context
import android.content.Intent
import com.id.parkee.commons.navigation.DashboardNavigation
import com.id.parkee.commons.navigation.FavoriteNavigation
import com.id.parkee.commons.navigation.MovieDetailNavigation
import com.id.parkee.core.movie.data.MovieDetailModel
import com.id.parkee.features.dashboard.DashboardActivity
import com.id.parkee.features.favorite.FavoriteActivity
import com.id.parkee.features.movie.MovieDetailActivity
import org.koin.dsl.module

val navigationModule = module {
    single<DashboardNavigation> {
        object : DashboardNavigation {
            override fun createIntent(context: Context?) =
                Intent(context, DashboardActivity::class.java)
        }
    }
    single<MovieDetailNavigation> {
        object : MovieDetailNavigation {
            override fun createIntent(context: Context?, movie: MovieDetailModel): Intent {
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra(MovieDetailActivity.MOVIE_MODEL_ARGS, movie)
                return intent
            }
        }
    }
    single<FavoriteNavigation> {
        object : FavoriteNavigation {
            override fun createIntent(context: Context?) =
                Intent(context, FavoriteActivity::class.java)
        }
    }
}
