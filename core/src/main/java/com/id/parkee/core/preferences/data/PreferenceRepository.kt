package com.id.parkee.core.preferences.data

import com.id.parkee.core.preferences.domain.PreferenceRepositoryContract
import com.parkee.android.libraries.encrypted.EncryptedPreferencesContract

class PreferenceRepository(
    private val encryptedPreferencesContract: EncryptedPreferencesContract
) : PreferenceRepositoryContract {
    override fun setToken(token: String) {
        encryptedPreferencesContract.setString(TOKEN, token)
    }

    override fun getToken(): String =
        encryptedPreferencesContract.getString(TOKEN) ?: ""

    companion object {
        const val TOKEN = "token"
    }
}
