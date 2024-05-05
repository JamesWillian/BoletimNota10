package com.jammes.boletimnota10.di

import com.jammes.boletimnota10.BuildConfig
import com.jammes.boletimnota10.core.repository.api.ApiKeyInterceptor
import com.jammes.boletimnota10.core.repository.api.UsuarioApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val BASE_URL = "https://parseapi.back4app.com/functions/"

    @Provides
    @Singleton
    fun provideAPiKeyInterceptor(): ApiKeyInterceptor {
        return ApiKeyInterceptor(BuildConfig.APPLICATION_KEY, BuildConfig.REST_API_KEY, "")
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideUsuarioApiService(okHttpClient: OkHttpClient): UsuarioApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsuarioApiService::class.java)
    }
}