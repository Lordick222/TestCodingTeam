package com.example.testcodingteam.Service

import com.example.testcodingteam.dto.CityDto
import com.example.testcodingteam.dto.CityScoresDto
import org.springframework.stereotype.Service
import kotlin.math.abs

@Service
class ScoreCalculateService {
    fun calculateScoresCities(
        name: String,
        latitude: Double?,
        longitude: Double?,
        cities: List<CityDto>
    ): List<CityScoresDto> {
        when {
            (latitude != null && longitude != null) -> {
                val res = sortSitesByNameByLatLon(name, latitude, longitude, cities)
                return getCitiesArrayWithScoresByOrder(res)
            }
            (latitude != null) -> {
                val res = sortSitesByNameByLat(name, latitude, cities)
                return getCitiesArrayWithScoresByOrder(res)
            }
            (longitude != null) -> {
                val res = sortSitesByNameByLon(name, longitude, cities)
                return getCitiesArrayWithScoresByOrder(res)
            }
            else -> {
                val res = sortSitesByName(name, cities)
                return getCitiesArrayWithScoresByOrder(res)
            }
        }
    }

    fun sortSitesByNameByLatLon(
        name: String,
        latitude: Double,
        longitude: Double,
        cities: List<CityDto>
    ): List<CityDto> {
        return cities.map { abs(abs(it.lat) - abs(latitude)) + abs(abs(it.long) - abs(longitude)) to it }
            .toMap()
            .toSortedMap()
            .values.toList()
    }

    fun sortSitesByNameByLat(
        name: String,
        latitude: Double,
        cities: List<CityDto>
    ): List<CityDto> {
        return cities.map { abs(abs(it.lat) - abs(latitude)) to it }
            .toMap()
            .toSortedMap()
            .values.toList()
    }

    fun sortSitesByNameByLon(
        name: String,
        longitude: Double,
        cities: List<CityDto>
    ): List<CityDto> {
        return cities.map { abs(abs(it.long) - abs(longitude)) to it }
            .toMap()
            .toSortedMap()
            .values.toList()
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
        maxScore += countStep
        return cities.map {
            maxScore -= countStep
            CityScoresDto.fromCityDto(it, maxScore)
        }
    }
}