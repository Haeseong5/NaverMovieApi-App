package com.example.search.di

import com.example.search.BuildConfig
import com.example.search.exception.NoConnectionInterceptor
import com.example.search.util.BaseUtil.BASE_URL
import com.example.search.util.BaseUtil.X_Naver_Client_Id
import com.example.search.util.BaseUtil.X_Naver_Client_Secret
import com.project.monopad.network.remote.api.NaverApi
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * https://eclipse-owl.tistory.com/category/%EA%B0%9C%EB%B0%9C
 */
val networkModule = module{
    single<NaverApi>{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get(named("network")))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NaverApi::class.java)
    }

    single { Cache(androidApplication().cacheDir, 10L * 1024 * 1024) }

    single(named("network")){
        OkHttpClient.Builder().apply {
            cache(get())
//            addInterceptor(HttpLoggingInterceptor().apply {
//                if (BuildConfig.DEBUG)
//                    level = HttpLoggingInterceptor.Level.BODY
//            })
            addInterceptor(NoConnectionInterceptor(get()))
            addInterceptor { chain ->
                chain.request()
                    .newBuilder()
                    .addHeader("X-Naver-Client-Id", X_Naver_Client_Id)
                    .addHeader("X-Naver-Client-Secret", X_Naver_Client_Secret)
                    .build()
                    .let { request ->
                        chain.proceed(request)
                    }
            }
            connectTimeout(15L, TimeUnit.MINUTES)
            readTimeout(15L, TimeUnit.SECONDS)
        }.build()
    }

}