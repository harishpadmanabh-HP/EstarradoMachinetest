package com.harish.estarradomachinetest.data

import com.harish.estarradomachinetest.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.util.concurrent.TimeUnit

interface ApiService {


    @Multipart
    @POST("home")
    fun getData(@Part ("access_token") token:RequestBody,
    @Part("country_code") code:RequestBody):Call<ApiResponse>

    companion object {
        operator fun invoke(): ApiService {
            var logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

            val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .addInterceptor(logger)
                .build()


            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build()
                .create(ApiService::class.java)
        }
    }

}