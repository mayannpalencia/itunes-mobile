package com.mayann.network.module

import com.mayann.domain.extension.json
import com.mayann.network.BuildConfig
import com.mayann.network.utilities.CurlLoggerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        curlLoggerInterceptor: CurlLoggerInterceptor
    ) = OkHttpClient.Builder().apply {
        connectTimeout(20, TimeUnit.SECONDS)
        readTimeout(20, TimeUnit.SECONDS)
        writeTimeout(20, TimeUnit.SECONDS)
        addInterceptor(loggingInterceptor)
        addInterceptor(curlLoggerInterceptor)
    }.build()

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
        client(okHttpClient)
        baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(
                GsonConverterFactory.create(json())
            )
    }.build()

    @Singleton
    @Provides
    fun provideCurlInterceptor() = CurlLoggerInterceptor()

}