package com.example.data.remote

import com.example.data.model.ItemData
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceService {

    @GET("/planetary/apod")
    fun getData(@Query("api_key") api_key: String,@Query("count") count: String): Single<Response<List<ItemData>>>
}