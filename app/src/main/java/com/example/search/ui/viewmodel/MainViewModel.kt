package com.example.search.ui.viewmodel

import android.util.Log
import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.search.model.vo.dto.NaverMovieResponse
import com.example.search.model.repository.MainRepoImpl
import com.example.search.base.BaseViewModel
import com.example.search.model.vo.entity.RecentSearch

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repo: MainRepoImpl) : BaseViewModel(){

    private val _searchedMovieData = MutableLiveData<NaverMovieResponse>()
    val movieResponseData: LiveData<NaverMovieResponse>
        get() = _searchedMovieData

    private val _recentSearchData = MutableLiveData<List<RecentSearch>>()
    val recentSearchData: LiveData<List<RecentSearch>>
        get() = _recentSearchData

    private val _isCompleted = MutableLiveData<Boolean>()
    val isInserted: LiveData<Boolean>
        get() = _isCompleted

    private val _isDeleted = MutableLiveData<RecentSearch>()
    val isDeleted: LiveData<RecentSearch>
        get() = _isDeleted

    fun getMovies(query: String) {
        addDisposable(
            repo.getMovies(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    d(TAG, it.total.toString())
                        _searchedMovieData.postValue(it)
                }, {
                    Log.d(TAG, it.message.toString())
                })
        )
    }

    fun getRecentSearch(){
        addDisposable(
            repo.getAllWords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _recentSearchData.postValue(it)
                }, {
                    Log.d(TAG, it.message.toString())
                })
        )
    }

    fun saveSearchWord(recentSearch: RecentSearch){
        addDisposable(
            repo.insert(recentSearch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _isCompleted.postValue(true)
                }, {
                    Log.d(TAG, it.message.toString())
                })
        )
    }

    fun delete(word: RecentSearch){
        addDisposable(
                repo.delete(word.id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            _isDeleted.postValue(word)
                        }, {
                            Log.d(TAG, it.message.toString())
                        })
        )
    }

}