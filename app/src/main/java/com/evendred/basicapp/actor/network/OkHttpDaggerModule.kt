package com.evendred.basicapp.actor.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class OkHttpDaggerModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()
}