package com.example.search.base

import android.app.Application
import com.example.search.di.*
import com.example.search.di.repositoryModule
import com.example.search.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules (
                networkModule,
                remoteDataSourceModule,
                localDataSourceModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}
