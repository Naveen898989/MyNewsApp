package com.example.mynewsapp.activity.main.pages

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.mynewsapp.base.BaseViewModel
import com.example.mynewsapp.data.UserDataStoreManager
import com.example.mynewsapp.model.User
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : BaseViewModel(application) {

    val userLiveData = MutableLiveData<User>()

    private val userDataStoreManager = UserDataStoreManager(application)

    init {
        scope.launch {
            userLiveData.postValue(userDataStoreManager.getUserData())
        }
    }

    fun saveUserData(username: String?, email: String?, category: String?) {
        scope.launch {
            userDataStoreManager.saveUserData(User(username, email, category))
        }
    }

}
