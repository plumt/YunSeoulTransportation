package com.yun.yunseoultransportation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val connectTimeout: Long = 5000
    private const val readTimeout: Long = 5000
    private const val cacheSize = 10 * 1024 * 1024L // 10 MB

    @Singleton
    @Provides
    fun provideHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val cache = Cache(context.cacheDir, cacheSize)
        return OkHttpClient.Builder()
            .cache(cache)
//            .addInterceptor(LoggingInterceptor())
            .addNetworkInterceptor(NetworkCacheInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
            .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }
}

class NetworkCacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        return if (response.code == 200) {
            val cacheControl = CacheControl.Builder()
                .maxAge(1, TimeUnit.MINUTES)
                .build()
            response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", cacheControl.toString())
                .build()
        } else {
            response
        }
    }
}