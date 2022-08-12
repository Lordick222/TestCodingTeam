package com.example.testcodingteam.Service

import com.example.testcodingteam.dto.CityDto
import com.example.testcodingteam.dto.CityScoresDto
import org.springframework.stereotype.Service

@Service
class ScoreCalculateService {
    fun calculateScoresCities(
        name: String,
        latitude: Double?,
        longitude: Double?,
        cities: List<CityDto>
    ): List<CityScoresDto> {
        return getCitiesArrayWithScoresByOrder(sortSitesByName(name, cities))
    }

    fun calculateScoresWithLatLon(
        name: String,
        latitude: Double?,
        longitude: Double?,
        cities: List<CityDto>
    ): List<CityScoresDto> {

    }

    fun sortSitesByName(name: String, cities: List<CityDto>): List<CityDto> {
        if (cities.isEmpty()) return emptyList()
        return cities.sortedBy { it.name!!.length }
    }

    fun getCitiesArrayWithScoresByOrder(cities: List<CityDto>): List<CityScoresDto> {
        if (cities.isEmpty()) return emptyList()
        var maxScore = 1.0
        if (cities.size == 1) return listOf(CityScoresDto.fromCityDto(cities.first(), maxScore))
        val countStep = maxScore / cities.size
        return cities.map {
            maxScore -= countStep
            CityScoresDto.fromCityDto(it, maxScore)
        }
    }
}