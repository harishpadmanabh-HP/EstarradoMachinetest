package com.harish.estarradomachinetest

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harish.estarradomachinetest.data.ApiResponse
import com.harish.estarradomachinetest.data.repository.BaseRepository

class HomeViewModel(var application: Application) : ViewModel() {

    val apiResponse = MutableLiveData<ApiResponse>()
    val events = MutableLiveData<String>()
    val repository = BaseRepository(application)

    fun getDataForHome() =repository.getData("752027","SA",onApiCallback = {
        status, message, response ->

        if(status)
            apiResponse.postValue(response)
        else
            events.postValue(message)
    })


}



open class HomeViewModelFactory(val app: Application) :
    ViewModelProvider.AndroidViewModelFactory(app) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(app) as T
    }
}