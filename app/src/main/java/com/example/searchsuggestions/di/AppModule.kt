package com.example.searchsuggestions.di

import com.example.searchsuggestions.data.remote.DuckDuckGoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDuckDuckGoApi(): DuckDuckGoApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(DuckDuckGoApi.BASE_URL)
            .build()
            .create(DuckDuckGoApi::class.java)
    }

}