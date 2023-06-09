package com.technocorp.mqutqaruv.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.technocorp.mqutqaruv.BuildConfig
import com.technocorp.mqutqaruv.data.remote.api.Api
import com.technocorp.mqutqaruv.data.repository.MainRepositoryImpl
import com.technocorp.mqutqaruv.domain.repository.MainRepository
import com.technocorp.mqutqaruv.util.DefaultLocationClient
import com.technocorp.mqutqaruv.util.LocationClient
import com.technocorp.mqutqaruv.util.RequestInterceptor
import com.technocorp.mqutqaruv.util.SharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            client(okHttp)
            baseUrl(BuildConfig.API_BASE_URL)
        }.build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideRepo(
        api: Api,
        pref: SharedPref
    ): MainRepository = MainRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        requestInterceptor: RequestInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        val okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(120, TimeUnit.SECONDS)
            addInterceptor(requestInterceptor)
            if (BuildConfig.DEBUG) {
                addInterceptor(httpLoggingInterceptor)
            }
        }
        return okHttpClient.build()
    }

    @Provides
    fun provideRequestInterceptor(
        prefs: SharedPref,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): RequestInterceptor {
        return RequestInterceptor(prefs, httpLoggingInterceptor)
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    @Singleton
    fun provideLocationClient(
        app: Application,
        fusedLocationProviderClient: FusedLocationProviderClient
    ): LocationClient {
        return DefaultLocationClient(app, fusedLocationProviderClient)
    }
}