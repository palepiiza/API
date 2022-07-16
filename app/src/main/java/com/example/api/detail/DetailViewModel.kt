package com.example.api.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api.network.GithubApi
import com.example.api.network.GithubData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailViewModel (username: String): ViewModel() {
    private val _item = MutableLiveData<GithubData>()
    val item : LiveData<GithubData>
        get() = _item

    private var vmJob = Job()
    private var crScope = CoroutineScope(vmJob + Dispatchers.Main)
  init {
      crScope.launch {
          try{
              val result = GithubApi.retrofitService.showUser(username)
                  _item.value = result
          }catch (t:Throwable){
                //...show error
          }
      }
  }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }
}