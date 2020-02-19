package com.example.mynewsapp.activity.main.pages

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.mynewsapp.base.BaseViewModel
import com.example.mynewsapp.model.ErrorObject
import com.example.mynewsapp.model.News
import com.example.mynewsapp.network.RetrofitClient
import com.example.mynewsapp.util.isNetworkConnected
import kotlinx.coroutines.launch
import java.io.IOException

class TopNewsViewModel(application: Application) : BaseViewModel(application) {

    val progressBarVisibilityLiveData = MutableLiveData<Boolean>()
    val topNewsLiveData = MutableLiveData<List<News>>()
    val errorLiveData = MutableLiveData<ErrorObject>()

    init {
        if (isNetworkConnected(application)) {
            fetchNews()
        } else {
            errorLiveData.postValue(ErrorObject(ErrorObject.ERROR_ID_NO_INTERNET, null))
        }
    }

    fun fetchNews() {
        progressBarVisibilityLiveData.postValue(true)

        scope.launch {
            val response =
                RetrofitClient.newsApiService.retrieveTopNews("us")
                    .execute()
            if (response.isSuccessful && response.body()?.status.equals("ok")) {
                topNewsLiveData.postValue(response.body()!!.body)
            } else {
                errorLiveData.postValue(
                    ErrorObject(
                        ErrorObject.ERROR_ID_UNKNOWN,
                        IOException("Error occurred while retrieving tutors: " + response.errorBody())
                    )
                )
            }
            progressBarVisibilityLiveData.postValue(false)
        }
    }

}
