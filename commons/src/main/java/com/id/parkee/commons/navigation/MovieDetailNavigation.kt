package com.id.parkee.commons.navigation

import android.content.Context
import android.content.Intent
import com.id.parkee.core.movie.data.MovieDetailModel

interface MovieDetailNavigation {
    fun createIntent(context: Context?, movie: MovieDetailModel): Intent
}
