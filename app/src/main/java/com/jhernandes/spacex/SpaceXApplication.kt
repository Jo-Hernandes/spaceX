package com.jhernandes.spacex

import android.app.Application
import com.jhernandes.spacex.dependencyInjection.application
import com.jhernandes.spacex.dependencyInjection.dataSource
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SpaceXApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@SpaceXApplication)
            modules(dataSource + application)
        }
    }

}
