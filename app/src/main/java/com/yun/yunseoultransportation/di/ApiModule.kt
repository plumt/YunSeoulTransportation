package com.yun.yunseoultransportation.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.yun.yunseoultransportation.data.datasource.BusDataSource
import com.yun.yunseoultransportation.data.datasource.BusDataSourceImpl
import com.yun.yunseoultransportation.data.datasource.PathDataSource
import com.yun.yunseoultransportation.data.datasource.PathDataSourceImpl
import com.yun.yunseoultransportation.data.datasource.SearchDataSource
import com.yun.yunseoultransportation.data.datasource.SearchDataSourceImpl
import com.yun.yunseoultransportation.data.remote.api.BusApiService
import com.yun.yunseoultransportation.data.remote.api.PathApiService
import com.yun.yunseoultransportation.data.remote.api.SearchApiService
import com.yun.yunseoultransportation.data.repository.BusRepositoryImpl
import com.yun.yunseoultransportation.data.repository.PathRepositoryImpl
import com.yun.yunseoultransportation.data.repository.SearchRepositoryImpl
import com.yun.yunseoultransportation.domain.repository.BusRepository
import com.yun.yunseoultransportation.domain.repository.PathRepository
import com.yun.yunseoultransportation.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private fun provideRetrofit(
        @ApplicationContext context: Context,
        client: OkHttpClient,
        baseUrl: String
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideBusDataSource(
        context: Context,
        client: OkHttpClient
    ): BusDataSource {
        val retrofit = provideRetrofit(context, client, "http://ws.bus.go.kr/api/rest/")
        return BusDataSourceImpl(retrofit.create(BusApiService::class.java))
    }

    @Provides
    @Singleton
    fun provideBusRepository(busDataSource: BusDataSource): BusRepository {
        return BusRepositoryImpl(busDataSource)
    }

    @Provides
    @Singleton
    fun providePathDataSource(
        context: Context,
        client: OkHttpClient
    ): PathDataSource {
        val retrofit = provideRetrofit(context, client, "http://ws.bus.go.kr/api/rest/")
        return PathDataSourceImpl(retrofit.create(PathApiService::class.java))
    }

    @Provides
    @Singleton
    fun providePathRepository(pathDataSource: PathDataSource): PathRepository {
        return PathRepositoryImpl(pathDataSource)
    }

    @Provides
    @Singleton
    fun provideSearchDataSource(
        context: Context,
        client: OkHttpClient
    ): SearchDataSource {
        val retrofit = provideRetrofit(context, client, "https://dapi.kakao.com/v2/")
        return SearchDataSourceImpl(retrofit.create(SearchApiService::class.java))
    }

    @Provides
    @Singleton
    fun provideSearchRepository(searchDataSource: SearchDataSource): SearchRepository {
        return SearchRepositoryImpl(searchDataSource)
    }
}