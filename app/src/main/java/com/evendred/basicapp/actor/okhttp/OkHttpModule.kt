package com.evendred.basicapp.actor.okhttp

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class OkHttpModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }
}