package com.id.parkee

import android.app.Application
import com.id.parkee.di.coreModule
import com.id.parkee.di.featureModule
import com.id.parkee.di.libraryModule
import com.id.parkee.di.navigationModule
import com.id.parkee.di.networkModule
import com.id.parkee.di.roomCoreModule
import com.id.parkee.di.roomRepositoryCoreModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTimber()
    }

    private fun initKoin() = startKoin {
        androidContext(this@BaseApplication)

        val modules = listOf(
            libraryModule,
            networkModule,
            coreModule,
            navigationModule,
            featureModule,
            roomRepositoryCoreModule,
            roomCoreModule
        )
        modules(modules)
    }

    private fun initTimber() {
        Timber.plant(TimberTreeLogging())
    }
}
