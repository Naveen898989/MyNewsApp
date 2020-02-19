package com.example.mynewsapp.model

import com.google.gson.annotations.SerializedName

class News {

    @SerializedName("title")
    lateinit var title: String

    @SerializedName("author")
    lateinit var author: String

    @SerializedName("description")
    lateinit var description: String

    @SerializedName("url")
    lateinit var url: String

    @SerializedName("urlToImage")
    lateinit var urlToImage: String

    @SerializedName("publishedAt")
    lateinit var publishedAt: String

    @SerializedName("content")
    lateinit var content: String

    inner class Source {

        @SerializedName("id")
        lateinit var id: String

        @SerializedName("name")
        lateinit var name: String
    }

}