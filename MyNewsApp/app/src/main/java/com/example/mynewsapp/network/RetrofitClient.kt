package com.example.mynewsapp.network

import com.example.mynewsapp.util.AppConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.NEWS_API_BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request()
                            .newBuilder()
                            .addHeader(
                                "Authorization",
                                "Bearer " + AppConstants.API_KEY
                            )
                            .build()

                        chain.proceed(request)
                    }
                    .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val newsApiService by lazy { retrofit.create(NewsApi::class.java) }
    }

}