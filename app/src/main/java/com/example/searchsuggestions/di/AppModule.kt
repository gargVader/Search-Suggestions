package com.example.searchsuggestions.di

import com.example.searchsuggestions.data.remote.DuckDuckGoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDuckDuckGoApi(): DuckDuckGoApi {
        return Retrofit.Builder()
            .baseUrl(DuckDuckGoApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

}