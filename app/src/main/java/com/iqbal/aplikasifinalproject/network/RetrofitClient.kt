package com.iqbal.aplikasifinalproject.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {

    const val BASE_URL = "https://6340e78716ffb7e275c7134c.mockapi.io/"


    val instance : ResfulAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ResfulAPI::class.java)
    }
}