package com.ruthloeser.android.tctassignment.ui.articles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruthloeser.android.tctassignment.model.Article
import com.ruthloeser.android.tctassignment.model.Result
import com.ruthloeser.android.tctassignment.repo.ArticlesRepository
import com.ruthloeser.android.tctassignment.repo.ArticlesRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticlesViewModel(private val repository: ArticlesRepository = ArticlesRepositoryImpl()) : ViewModel() {

    val allArticles = MutableLiveData<Result<List<Article>>>()

    init {
        getAllArticles()
    }

    suspend fun clear() {
        repository.clearAllArticles()
    }

    private fun getAllArticles()  {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getAllArticles()
            allArticles.postValue(result)
        }
    }

}