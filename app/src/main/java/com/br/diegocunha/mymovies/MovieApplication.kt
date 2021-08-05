package com.br.diegocunha.mymovies

import android.app.Application
import com.br.diegocunha.mymovies.coroutines.coroutineModule
import com.br.diegocunha.mymovies.datasource.api.rest.apiModule
import com.br.diegocunha.mymovies.datasource.api.rest.restModule
import com.br.diegocunha.mymovies.ui.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MovieApplication)
            modules(apiModule, restModule, appModule, coroutineModule)
        }
    }
}