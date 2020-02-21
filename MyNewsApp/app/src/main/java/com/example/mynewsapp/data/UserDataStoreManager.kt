package com.example.mynewsapp.data

import android.content.Context
import android.content.SharedPreferences
import com.example.mynewsapp.model.User
import com.google.gson.Gson

private const val USER_DATA_PREFERENCES: String = "com.example.mynewsapp.UserDataPreferences"

private const val KEY_USER_DATA: String = "userData"

class UserDataStoreManager(context: Context) : SharedPreferences.OnSharedPreferenceChangeListener {

    private var preferences: SharedPreferences =
        context.getSharedPreferences(USER_DATA_PREFERENCES, Context.MODE_PRIVATE)
    private var listener: ((User?) -> Unit)? = null

    fun saveUserData(user: User) {
        val editor = preferences.edit()
        editor.putString(KEY_USER_DATA, Gson().toJson(user))
        editor.commit()
    }

    fun getUserData(): User? {
        val userDataJson = preferences.getString(KEY_USER_DATA, null)
        if (userDataJson != null) {
            return Gson().fromJson(userDataJson, User::class.java)
        }

        return null
    }

    fun listenToPreferenceChanges(listener : ((User?) -> Unit)) {
        this.listener = listener
        preferences.registerOnSharedPreferenceChangeListener(this)
    }

    fun stopListeners() {
        this.listener = null
        preferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(preferences: SharedPreferences?, key: String?) {
        if (key.equals(KEY_USER_DATA)) {
            listener?.invoke(getUserData())
        }
    }

}