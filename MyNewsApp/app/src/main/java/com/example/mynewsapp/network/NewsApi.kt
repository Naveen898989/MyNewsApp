package com.example.mynewsapp.network

import com.example.mynewsapp.model.News
import com.example.mynewsapp.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun retrieveTopNews(@Query("country") country: String = "us"): Call<NewsResponse>

}