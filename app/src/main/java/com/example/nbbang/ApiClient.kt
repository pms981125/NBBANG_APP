package com.example.nbbang

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


// ApiClient.kt
object ApiClient {

    private const val BASE_URL = "http://211.212.148.6:8080"

    var gson = GsonBuilder()
        .setLenient()
        .create()

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY//개발용
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // 로깅 인터셉터 추가
        .build()


    /*val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()*/

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}
