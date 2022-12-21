package com.example.data.source

import com.example.data.model.ItemData
import io.reactivex.Single

interface RemoteDataSource {
    fun getData() : Single<List<ItemData>>
}