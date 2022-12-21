package com.example.data.source

import com.example.data.model.ItemData
import io.reactivex.Flowable
import io.reactivex.Single

interface LocalDataSource {

    fun getData(): Flowable<List<ItemData>>
    fun insertData(list: List<ItemData>)

}