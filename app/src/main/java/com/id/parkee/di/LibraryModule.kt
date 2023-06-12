package com.id.parkee.di

import com.parkee.android.libraries.encrypted.EncryptedPreferences
import com.parkee.android.libraries.encrypted.EncryptedPreferencesContract
import com.id.parkee.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

var libraryModule = module {
    single<EncryptedPreferencesContract> {
        EncryptedPreferences(
            androidContext(),
            SECURE_PREFS_FILE_KEY
        )
    }
}

const val SECURE_PREFS_FILE_KEY = "${BuildConfig.APPLICATION_ID}.secure_preferences"
