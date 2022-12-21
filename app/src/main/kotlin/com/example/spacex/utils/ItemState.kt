package com.example.spacex.utils

import com.example.domain.model.ItemDomain

sealed class ItemState {
    object ShowLoading : ItemState()
    object HideLoading : ItemState()
    object Empty : ItemState()
    data class Success(val list: List<ItemDomain>) : ItemState()
    data class Error(val exception: Throwable) : ItemState()
}