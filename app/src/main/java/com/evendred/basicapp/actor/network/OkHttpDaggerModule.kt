package com.evendred.basicapp.actor.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Singleton

@Module
class OkHttpDaggerModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    fun provideHttpRequestBuilder(): Request.Builder = Request.Builder()
}