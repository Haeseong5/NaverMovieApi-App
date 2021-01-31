package com.example.search.di

import com.example.search.model.repository.MainRepoImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { MainRepoImpl(get(), get()) }
}