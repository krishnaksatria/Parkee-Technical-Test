package com.id.parkee.features.splash

import com.id.parkee.commons.ui.viewmodel.BaseViewModel
import com.id.parkee.core.preferences.domain.PreferenceRepositoryContract

class SplashViewModel(
    private val preferenceRepositoryContract: PreferenceRepositoryContract
) : BaseViewModel() {
    fun saveToken(token: String) {
        preferenceRepositoryContract.setToken(token)
    }
}
