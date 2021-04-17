package com.ruthloeser.android.tctassignment.repo

import com.ruthloeser.android.tctassignment.App
import com.ruthloeser.android.tctassignment.R
import com.ruthloeser.android.tctassignment.model.Article
import com.ruthloeser.android.tctassignment.model.Failure
import com.ruthloeser.android.tctassignment.model.Result
import java.io.File
import java.io.IOException

class ArticlesRepositoryImpl : ArticlesRepository {

    private val remoteApi = App.remoteApi

    override suspend fun getAllArticles(): Result<List<Article>> =
        if(App.networkStatusChecker().hasInternetConnection())
            remoteApi.getArticles()
        else
            Failure(
                IOException(App.getContext().getString(R.string.connectivity_issue)))


    override suspend fun clearAllArticles() {
        val cacheDirectory = App.getContext().cacheDir

        val dirPath = App.getContext().cacheDir.parent

        val appDirectory =
            if (dirPath != null)
                File(dirPath)
            else
                null

        if (appDirectory != null && appDirectory.exists())
            appDirectory.deleteRecursively()
        else
            cacheDirectory.deleteRecursively()

    }

}