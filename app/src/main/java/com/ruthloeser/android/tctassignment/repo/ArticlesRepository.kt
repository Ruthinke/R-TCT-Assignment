package com.ruthloeser.android.tctassignment.repo

import com.ruthloeser.android.tctassignment.model.Article
import com.ruthloeser.android.tctassignment.model.Result

interface ArticlesRepository {
    suspend fun getAllArticles(): Result<List<Article>>
    suspend fun clearAllArticles()
}