package com.example.daggerhiltinmultymoduleproject.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.Repository
import com.example.core.data.model.ResponseDTOModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _news = MutableLiveData<ArrayList<ResponseDTOModel.Articles>>()
    val news: LiveData<ArrayList<ResponseDTOModel.Articles>> = _news

    fun loadNews() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.news()
            }.onSuccess {
                it.articles?.let {
                    _news.postValue(it)
                }
            }.onFailure {

            }
        }
    }

}