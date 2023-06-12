package com.id.parkee.commons.navigation

import android.content.Context
import android.content.Intent

interface DashboardNavigation {
    fun createIntent(context: Context?): Intent
}
