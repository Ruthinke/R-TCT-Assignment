package com.ruthloeser.android.tctassignment.networking

import com.ruthloeser.android.tctassignment.model.ArticleResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface RemoteApiService {

    @GET
    suspend fun getArticles(@Url url: String): Response<ArticleResult>

}