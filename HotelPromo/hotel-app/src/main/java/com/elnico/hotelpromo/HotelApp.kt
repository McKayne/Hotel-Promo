package com.elnico.hotelpromo

import android.app.Application
import android.preference.PreferenceManager
import com.elnico.hotelpromo.di.getViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.osmdroid.config.Configuration

class HotelApp: Application() {

    override fun onCreate() {
        super.onCreate()

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))

        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    getViewModelModule()
                )
            )
        }
    }
}