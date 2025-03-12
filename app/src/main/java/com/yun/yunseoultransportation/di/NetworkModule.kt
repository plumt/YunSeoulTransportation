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
            .addInterceptor(KakaoAuthInterceptor("19589e0aaca9b5da11b10fa3870a2e37")) // 카카오 인증 인터셉터 추가
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
        val request = chain.request()
        // 요청 헤더에서 "Cache-Control: no-cache" 값이 있는지 확인
        val shouldSkipCache = request.header("Cache-Control") == "no-cache"
        return if (response.code == 200) {
            val cacheControl = CacheControl.Builder()
                .maxAge(1, if(shouldSkipCache) TimeUnit.SECONDS else TimeUnit.MINUTES)
//                .maxAge(10, TimeUnit.SECONDS)
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

class KakaoAuthInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestUrl = request.url.toString()

        // 카카오 API 요청인 경우에만 헤더 추가
        return if (requestUrl.contains("dapi.kakao.com")) {
            val newRequest = request.newBuilder()
                .addHeader("Authorization", "KakaoAK $apiKey")
                .build()
            chain.proceed(newRequest)
        } else {
            // 카카오 API가 아닌 경우 원래 요청 그대로 진행
            chain.proceed(request)
        }
    }
}