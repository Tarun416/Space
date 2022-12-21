package com.example.data.source

class DataSourceFactory (private val localDataSource: LocalDataSource , private val  remoteDataSource: RemoteDataSource){

    fun local() = localDataSource
    fun remote() = remoteDataSource
}