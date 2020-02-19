package com.example.mynewsapp.model

import com.example.mynewsapp.model.News
import com.google.gson.annotations.SerializedName

class NewsResponse {

    @SerializedName("status")
    lateinit var status: String

    @SerializedName("totalResults")
    lateinit var totalResults: String

    @SerializedName("articles")
    lateinit var body: ArrayList<News>

}