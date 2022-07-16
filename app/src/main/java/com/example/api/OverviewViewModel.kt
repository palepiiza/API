package com.example.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api.network.GithubApi
import com.example.api.network.GithubData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewViewModel : ViewModel(){
    private val _items = MutableLiveData<List<GithubData>>()
    val items : LiveData<List<GithubData>>
    get() = _items

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    private var vmJob = Job()
    private var crScope = CoroutineScope(vmJob + Dispatchers.Main)

    init {
        initData()
    }

    private fun initData(){
        _response.value = "Looding....."
        crScope.launch {
        try{
            val result = GithubApi.retrofitService.showList()
            if(result.size > 0){
                _items.value = result
                _response.value = "Ok"
            }
        }catch (t:Throwable){
            _response.value = "Failed , Internet OFF"
        }
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }
}