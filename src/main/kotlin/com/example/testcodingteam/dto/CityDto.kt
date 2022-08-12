package com.example.testcodingteam.dto

import com.example.testcodingteam.Repository.model.CityDao

data class CityDto(
    var id: Long,
    var name: String? = null,
    var lat: Double,
    var long: Double,
) {
    companion object {
        fun fromCityDao(cityDao: CityDao): CityDto {
            return CityDto(
                cityDao.id!!,
                cityDao.name,
                cityDao.lat!!,
                cityDao.long!!
            )
        }
    }
}
