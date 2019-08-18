// Copyright (c) 2019 . Wilberforce Uwadiegwu. All Rights Reserved.

package com.jadebyte.jadeplayer.main.common.view

import android.app.Application
import android.database.ContentObserver
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jadebyte.jadeplayer.main.common.data.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Wilberforce on 19/04/2019 at 16:45.
 */
abstract class BaseViewModel<T>(application: Application) : AndroidViewModel(application) {

    protected val data = MutableLiveData<List<T>>()
    val items: LiveData<List<T>> get() = data
    abstract var repository: BaseRepository<T>
    open var projection: Array<String>? = null
    open var selection: String? = null
    open var selectionArgs: Array<String>? = null
    open var sortOrder: String? = null
    abstract var uri: Uri


    private val observer: ContentObserver = object : ContentObserver(null) {
        override fun onChange(selfChange: Boolean) {
            loadData()
        }
    }

    fun init() {
        observer.onChange(false)
        getApplication<Application>().contentResolver.registerContentObserver(uri, true, observer)
    }


    private fun loadData() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.loadData(uri, projection, selection, selectionArgs, sortOrder)
            }
            deliverResult(result)

        }
    }

    override fun onCleared() {
        super.onCleared()
        getApplication<Application>().contentResolver.unregisterContentObserver(observer)
    }


    // Give child classes the opportunity to intercept and modify result
    open fun deliverResult(items: List<T>) {
        if (data.value != items) data.value = items
    }

    fun overrideCurrentItems(items: List<T>) {
        data.value = items
    }
}

