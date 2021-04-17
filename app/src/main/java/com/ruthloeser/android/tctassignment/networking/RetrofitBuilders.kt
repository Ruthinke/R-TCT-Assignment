package com.ruthloeser.android.tctassignment.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory

fun buildRetrofit(): Retrofit =
        Retrofit.Builder()
                .client(buildClient())
                .baseUrl("http://localhost/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

fun buildApiService(): RemoteApiService =
        buildRetrofit().create(RemoteApiService::class.java)

fun buildClient(): OkHttpClient =
        OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
