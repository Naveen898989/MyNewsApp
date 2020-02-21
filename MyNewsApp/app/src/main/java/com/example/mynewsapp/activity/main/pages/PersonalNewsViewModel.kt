package com.example.mynewsapp.activity.main.pages

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.mynewsapp.base.BaseViewModel
import com.example.mynewsapp.data.UserDataStoreManager
import com.example.mynewsapp.model.ErrorObject
import com.example.mynewsapp.model.News
import com.example.mynewsapp.model.User
import com.example.mynewsapp.network.RetrofitClient
import com.example.mynewsapp.util.isNetworkConnected
import kotlinx.coroutines.launch
import java.io.IOException

class PersonalNewsViewModel(application: Application) : BaseViewModel(application) {

    val personalNewsLiveData = MutableLiveData<ArrayList<News>>()
    val errorLiveData = MutableLiveData<ErrorObject>()

    private val userDataStoreManager = UserDataStoreManager(application)
    private var userData: User? = userDataStoreManager.getUserData()

    private var selectedCategory: String? = null

    init {
        if (userData?.category != null) {
            if (isNetworkConnected(application)) {
                fetchNews()
            } else {
                errorLiveData.postValue(ErrorObject(ErrorObject.ERROR_ID_NO_INTERNET, null))
            }
        } else {
            personalNewsLiveData.postValue(null)
        }

        userDataStoreManager.listenToPreferenceChanges { userData ->
            if (!userData?.category.equals(selectedCategory)) {
                this.userData = userData
                fetchNews()
            }
        }
    }

    fun fetchNews() {
        progressBarVisibilityLiveData.postValue(true)

        scope.launch {
            selectedCategory = userData!!.category!!
            val response =
                RetrofitClient.newsApiService.retrievePersonalNews(selectedCategory!!)
                    .execute()
            if (response.isSuccessful && response.body()?.status.equals("ok")) {
                personalNewsLiveData.postValue(response.body()!!.body)
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
