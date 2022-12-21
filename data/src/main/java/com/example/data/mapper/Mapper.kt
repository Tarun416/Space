package com.example.data.mapper

interface Mapper <I,O>{

    fun mapDataToDomain(data : I) : O
    fun mapDomainToData(data : O) : I
}