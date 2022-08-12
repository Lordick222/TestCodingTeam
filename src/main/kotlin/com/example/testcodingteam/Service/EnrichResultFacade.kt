package com.example.testcodingteam.Service

import com.example.testcodingteam.dto.CityScoresDto
import org.springframework.stereotype.Service

@Service
class EnrichResultFacade(
    private val cityService: CityService,
    private val scoreCalculateService: ScoreCalculateService
) {

    fun getCitiesWithScores(name: String, latitude: Double?, longitude: Double?): List<CityScoresDto> {
        val cities = cityService.getAllByNameContains(name)
        if (cities.isEmpty()) return listOf()
        return scoreCalculateService.calculateScoresCities(name, latitude, longitude, cities)
    }
}