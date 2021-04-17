package com.ruthloeser.android.tctassignment.networking

import com.ruthloeser.android.tctassignment.model.Article
import com.ruthloeser.android.tctassignment.model.Failure
import com.ruthloeser.android.tctassignment.model.Result
import com.ruthloeser.android.tctassignment.model.Success

class RemoteApi(private val apiService: RemoteApiService) {

    companion object {
        private const val ARTICLES_LINK = "https://cdn.theculturetrip.com/home-assignment/response.json"
    }

    suspend fun getArticles(): Result<List<Article>> =
        try {
            val response = apiService.getArticles(ARTICLES_LINK)
            val error = response.errorBody().toString()

            if (response.isSuccessful) {

                val data = response.body()
                if (data == null) {
                    Failure(NullPointerException(error))
                }
                else {
                    Success(data.data.distinctBy{it.id})
                }
            } else {

                Failure(Exception("code: ${response.code()}, body: $error"))
            }
        } catch (error: Throwable){

            Failure(error)
        }

}