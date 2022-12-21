package com.example.domain.repository

import com.example.domain.model.ItemDomain
import io.reactivex.Flowable
import io.reactivex.Single

interface LocalRepository {
    fun getData() : Flowable<List<ItemDomain>>
}