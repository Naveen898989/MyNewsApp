package com.example.mynewsapp.network

import com.example.mynewsapp.model.NewsResponse
import com.example.mynewsapp.util.NEWS_SORT_ORDER_VALUES
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun retrieveTopNews(@Query("country") country: String = "us"): Call<NewsResponse>

    @GET("everything")
    fun retrievePersonalNews(@Query("q") query: String, @Query("sortBy") sortBy: String = NEWS_SORT_ORDER_VALUES[0]): Call<NewsResponse>

}