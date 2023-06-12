package com.id.parkee.core.preferences.domain

interface PreferenceRepositoryContract {
    fun setToken(token: String)
    fun getToken(): String
}
