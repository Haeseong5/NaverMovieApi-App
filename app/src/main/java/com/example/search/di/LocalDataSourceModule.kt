
package com.example.search.di

import androidx.room.Room
import com.example.search.model.local.LocalDataSource
import com.example.search.model.local.database.LocalDatabase
import com.example.search.model.local.datasource.RecentSearchDataSource
import com.example.search.model.local.datasource.RecentSearchDataSourceImpl

import org.koin.dsl.module

val localDataSourceModule = module {
    single {
        Room.databaseBuilder(get(), LocalDatabase::class.java, "test_local.db")
            .fallbackToDestructiveMigration().build()
    }
    single { get<LocalDatabase>().recentSearchDao() }
    single { LocalDataSource(get()) }
    single<RecentSearchDataSource> { RecentSearchDataSourceImpl(get()) }
}