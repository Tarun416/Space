package com.example.data.repository

import com.example.data.mapper.ItemMapper
import com.example.data.source.DataSourceFactory
import com.example.domain.model.ItemDomain
import com.example.domain.repository.LocalRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LocalRepositoryImpl(
    private val dataSourceFactory: DataSourceFactory,
    private val mapper: ItemMapper
) : LocalRepository {

    override fun getData(): Flowable<List<ItemDomain>> {

        dataSourceFactory.remote().getData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { dataSourceFactory.local().insertData(it) }
            .subscribe({}, {})

        return dataSourceFactory.local().getData()
            .map { items -> items.map { mapper.mapDataToDomain(it) } }

    }

}