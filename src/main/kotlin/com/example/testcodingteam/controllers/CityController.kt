package com.example.testcodingteam.controllers

import com.example.testcodingteam.Service.EnrichResultFacade
import com.example.testcodingteam.dto.CityScoresDto
import mu.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class CityController(private val enrichResultFacade: EnrichResultFacade) {

    private companion object : KLogging()

    @GetMapping("/suggestions")
    fun migrationBigTables(
        @RequestParam(name = "q", required = true) townName: String,
        @RequestParam(name = "latitude", required = false) latitude: Double?,
        @RequestParam(name = "longitude", required = false) longitude: Double?,
    ): List<CityScoresDto> {
        return enrichResultFacade.getCitiesWithScores(townName, latitude, longitude)
    }

}