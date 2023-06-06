package com.technocorp.mqutqaruv.di

import com.technocorp.mqutqaruv.util.DefaultLocationClient
import com.technocorp.mqutqaruv.util.LocationClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationClient(defaultLocationClient: DefaultLocationClient): LocationClient
}