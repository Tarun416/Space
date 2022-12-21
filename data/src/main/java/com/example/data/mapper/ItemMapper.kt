package com.example.data.mapper

import com.example.data.model.ItemData
import com.example.domain.model.ItemDomain
import javax.inject.Inject

class ItemMapper @Inject constructor() : Mapper<ItemData, ItemDomain> {
    override fun mapDataToDomain(data: ItemData): ItemDomain {
        return with(data)
        {
            ItemDomain(this.explanation, this.hdurl, this.title, this.url)
        }
    }

    override fun mapDomainToData(data: ItemDomain): ItemData {
        return ItemData()
    }
}