package com.example.testcodingteam.Service

import com.example.testcodingteam.Repository.CityRepository
import com.example.testcodingteam.dto.CityDto
import org.springframework.stereotype.Service

@Service
class CityService(private val cityRepository: CityRepository) {

    fun getAllByNameContains(name: String): List<CityDto> {
        if (name.isBlank()) return listOf()
        return cityRepository.getAllByNameContains(name).map { CityDto.fromCityDao(it) }
    }

}