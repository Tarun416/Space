package com.example.data.remote

import com.example.data.Constants
import com.example.data.model.ItemData
import com.example.data.source.RemoteDataSource
import io.reactivex.Single
import java.lang.RuntimeException
import javax.inject.Inject

class SpaceServiceImpl @Inject constructor(private val service: SpaceService) : RemoteDataSource {

    override fun getData(): Single<List<ItemData>> {
        return service.getData(Constants.API_KEY, "10").map {
            if (it.isSuccessful)
                return@map it.body()
            else {
                throw java.lang.Exception("Something went wrong")
            }

        }.map {
            return@map it
        }
    }

}

