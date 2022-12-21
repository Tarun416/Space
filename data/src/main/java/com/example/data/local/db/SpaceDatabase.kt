package com.example.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.SpaceDao
import com.example.data.local.model.Item

@Database(version = 1, exportSchema = false, entities = [Item::class])
abstract class SpaceDatabase : RoomDatabase(){
    abstract fun dao(): SpaceDao
}