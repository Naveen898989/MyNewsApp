package com.example.mynewsapp.model

class ErrorObject(val id: Int, val t: Throwable?) {

    companion object {
        const val ERROR_ID_NO_INTERNET = -1
        const val ERROR_ID_UNKNOWN = -2
    }

}
