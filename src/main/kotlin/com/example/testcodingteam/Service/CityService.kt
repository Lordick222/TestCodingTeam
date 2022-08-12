package com.example.testcodingteam.Service

import com.example.testcodingteam.Repository.CityRepository
import com.example.testcodingteam.dto.CityDto
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CityService(private val cityRepository: CityRepository) {

    private companion object : KLogging()

    fun getAllByNameContains(name: String): List<CityDto> {
        if (name.isBlank()) return listOf()
        val result = cityRepository.getAllByNameContains(name).map { CityDto.fromCityDao(it) }
        logger.debug { "Founded ${result.size} sities by name : $name" }
        return result
    }

}