package com.example.mynewsapp.model

import com.example.mynewsapp.util.NEWS_SORT_ORDER_VALUES

data class User(
    val name: String?,
    val email: String?,
    val category: String?,
    val sortBy: String? = NEWS_SORT_ORDER_VALUES[0]
)