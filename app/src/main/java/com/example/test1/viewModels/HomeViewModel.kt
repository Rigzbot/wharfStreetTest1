package com.example.test1.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test1.domain.ApiData
import com.example.test1.network.Api
import kotlinx.coroutines.launch
import java.lang.Exception

enum class ApiStatus {LOADING, ERROR, DONE}

class HomeViewModel: ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _dataList = MutableLiveData<List<ApiData>>()
    val dataList: LiveData<List<ApiData>> = _dataList

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _dataList.value = Api.retrofitService.getData()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _dataList.value = listOf()
            }
        }
    }
}