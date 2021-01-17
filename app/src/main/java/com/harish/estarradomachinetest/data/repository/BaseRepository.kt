package com.harish.estarradomachinetest.data.repository

import android.app.Application
import android.util.Log
import com.harish.estarradomachinetest.R
import com.harish.estarradomachinetest.Utils
import com.harish.estarradomachinetest.data.ApiResponse
import com.harish.estarradomachinetest.data.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BaseRepository(val context: Application) {

    private val api = ApiService()

    fun getData(
        accessToken: String,
        code: String,
        onApiCallback: (status: Boolean, message: String?, response: ApiResponse?) -> Unit
    ) {
        val token = RequestBody.create("text/plain".toMediaTypeOrNull(),"752027")
        val code = RequestBody.create("text/plain".toMediaTypeOrNull(),"SA")
        api.getData(token,code).enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("HHH","failure $t")
                if(!Utils.hasInternetConnection(context.applicationContext)){
                    onApiCallback(false,context.getString(R.string.offline),null)
                }else{
                    onApiCallback(false,context.getString(R.string.server_error),null)
                }
            }

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {

                Log.e("HHP",response.body().toString())
                if(response.isSuccessful){
                    onApiCallback(true,"",response.body())
                }else{
                    onApiCallback(false,context.getString(R.string.server_error),null)
                }

            }
        })

    }

}