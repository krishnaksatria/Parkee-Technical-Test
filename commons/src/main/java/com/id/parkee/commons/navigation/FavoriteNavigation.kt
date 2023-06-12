package com.id.parkee.commons.navigation

import android.content.Context
import android.content.Intent

interface FavoriteNavigation {
    fun createIntent(context: Context?): Intent
}
