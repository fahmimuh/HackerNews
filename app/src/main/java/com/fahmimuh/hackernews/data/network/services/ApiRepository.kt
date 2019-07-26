package com.fahmimuh.hackernews.data.network.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRepository {

    fun create(): ApiServices {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://hacker-news.firebaseio.com/v0/")
                .build()
        return retrofit.create(ApiServices::class.java)
    }
}
