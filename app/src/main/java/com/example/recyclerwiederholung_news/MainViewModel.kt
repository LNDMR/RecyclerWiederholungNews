package com.example.recyclerwiederholung_news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerwiederholung_news.data.Repository
import com.example.recyclerwiederholung_news.data.model.NewsArticle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val repository = Repository()

    private val _news = MutableLiveData<List<NewsArticle>>()
    val news: LiveData<List<NewsArticle>>
            get() = _news

    private val _commentList = MutableLiveData<List<String>>()
    val commentList: LiveData<List<String>>
        get() = _commentList

    init {
        loadNews()
        // ausgelagert
        //_news.value = repository.loadNews()
        Log.d("MainViewModel", "newslist is loading")
    }

    fun loadComments(id: Int) {
        val article = _news.value?.find { it.id == id}

        if(article != null) {
            _commentList.value = article.comment
        }
    }

    private fun loadNews() {
        viewModelScope.launch {
            delay(5000)
            _news.value = repository.loadNews()
        }
    }


}