package com.example.data.local

import com.example.data.local.dao.SpaceDao
import com.example.data.local.model.Item
import com.example.data.source.LocalDataSource
import com.example.data.model.ItemData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: SpaceDao
) : LocalDataSource {
    override fun getData(): Flowable<List<ItemData>> {
        return dao.getAllData().map { items ->
            items.map {
                ItemData(
                    it.copyright,
                    it.date,
                    it.explanation,
                    it.hdurl,
                    it.media_type,
                    it.service_version,
                    it.title,
                    it.url
                )
            }
        }
    }

    override fun insertData(list: List<ItemData>) {

        Completable.fromRunnable {
            dao.delete()
            dao.insertAll(list.map {
                Item(
                    it.copyright,
                    it.date ?: "",
                    it.explanation ?: "",
                    it.hdurl ?: "",
                    it.mediaType ?: "",
                    it.serviceVersion ?: "",
                    it.title ?: "",
                    it.url ?: ""
                )
            })
        }
            .subscribeOn(Schedulers.io())
            .subscribe()


    }
}