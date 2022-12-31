package com.iqbal.aplikasifinalproject.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitClient {

    private const val BASE_URL = "https://flightgo-be-server.up.railway.app/v1/api/"

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun setupRetrofitGithub(okHttp : OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun apiService(retrofit : Retrofit): ResfulAPI = retrofit.create(ResfulAPI::class.java)



}