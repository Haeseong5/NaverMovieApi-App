package com.example.search.di

import com.example.search.model.remote.datasource.MovieRemoteDataSource
import com.example.search.model.remote.datasource.MovieRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single<MovieRemoteDataSource>{ MovieRemoteDataSourceImpl(get()) }

}