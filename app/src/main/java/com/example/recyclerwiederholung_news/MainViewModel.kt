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

    init {
        loadNews()
        // ausgelagert
        //_news.value = repository.loadNews()
        Log.d("MainViewModel", "newslist is loading")
    }

    private fun loadNews() {
        viewModelScope.launch {
            delay(5000)
            _news.value = repository.loadNews()
        }
    }


}