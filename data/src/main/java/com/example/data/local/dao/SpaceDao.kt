package com.example.data.local.dao

import androidx.room.*
import com.example.data.local.model.Item
import io.reactivex.Flowable


@Dao
interface SpaceDao {

    @Query("Select * from nasa_table")
    fun getAllData(): Flowable<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Item>)

    @Query("Delete from nasa_table")
    fun delete()
}