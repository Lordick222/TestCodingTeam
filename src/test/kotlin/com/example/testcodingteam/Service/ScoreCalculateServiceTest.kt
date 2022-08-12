package com.example.testcodingteam.Service

import com.example.testcodingteam.Repository.model.CityDao
import com.example.testcodingteam.dto.CityDto
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.File
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ScoreCalculateServiceTest {

    private val objectMapper: ObjectMapper = ObjectMapper().registerModule(KotlinModule())
    private val scoreCalculateService: ScoreCalculateService = ScoreCalculateService()
    lateinit var citiesList: List<CityDto>
    private var CITY_NAME = "London"
    private var LATITUDE = 44.39276
    private var LONGITUDE = -83.44825

    @BeforeAll
    fun init() {
        var fileString =
            File(this.javaClass.classLoader.getResource("cictiestest.json").toURI()).inputStream().readBytes()
                .toString(Charsets.UTF_8).replace("\n", " ")
        citiesList = objectMapper.readerForListOf(CityDto::class.java).readValue(fileString)
    }

    @Test
    fun sortSitesByNameByLatLon() {
        val result = scoreCalculateService.calculateScoresCities(CITY_NAME, LATITUDE, LONGITUDE, ArrayList(citiesList));
        assertEquals(result.get(0).score, 1.0)
        assertEquals(result.get(0).name, "London")
        assertEquals(result.get(1).score, 0.75)
        assertEquals(result.get(1).name, "New London")
        assertEquals(result.get(2).score, 0.5)
        assertEquals(result.get(2).name, "Londontowne")
        assertEquals(result.get(3).score, 0.25)
        assertEquals(result.get(3).name, "Londonderry")
    }

    @Test
    fun sortSitesByNameByLat() {
        val result = scoreCalculateService.calculateScoresCities(CITY_NAME, LATITUDE, null, ArrayList(citiesList));
        assertEquals(result.get(0).score, 1.0)
        assertEquals(result.get(0).name, "New London")
        assertEquals(result.get(1).score, 0.75)
        assertEquals(result.get(1).name, "Londonderry")
        assertEquals(result.get(2).score, 0.5)
        assertEquals(result.get(2).name, "London")
        assertEquals(result.get(3).score, 0.25)
        assertEquals(result.get(3).name, "Londontowne")
    }

    @Test
    fun sortSitesByNameByLon() {
        val result = scoreCalculateService.calculateScoresCities(CITY_NAME, null, LONGITUDE, ArrayList(citiesList));
        assertEquals(result.get(0).score, 1.0)
        assertEquals(result.get(0).name, "London")
        assertEquals(result.get(1).score, 0.75)
        assertEquals(result.get(1).name, "New London")
        assertEquals(result.get(2).score, 0.5)
        assertEquals(result.get(2).name, "Londontowne")
        assertEquals(result.get(3).score, 0.25)
        assertEquals(result.get(3).name, "Londonderry")
    }

    @Test
    fun sortSitesByName() {
        val result = scoreCalculateService.calculateScoresCities(CITY_NAME, null, null, ArrayList(citiesList));
        assertEquals(result.get(0).score, 1.0)
        assertEquals(result.get(0).name, "London")
        assertEquals(result.get(1).score, 0.75)
        assertEquals(result.get(1).name, "New London")
        assertEquals(result.get(2).score, 0.5)
        assertEquals(result.get(2).name, "Londontowne")
        assertEquals(result.get(3).score, 0.25)
        assertEquals(result.get(3).name, "Londonderry")
    }

}