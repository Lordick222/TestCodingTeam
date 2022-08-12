package com.example.testcodingteam.Service

import com.example.testcodingteam.Repository.CityRepository
import com.example.testcodingteam.Repository.model.CityDao
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.io.File
import kotlin.test.assertEquals

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CityServiceTest {

    @MockBean
    lateinit var cityRepository: CityRepository

    private lateinit var cityService: CityService
    private val objectMapper: ObjectMapper = ObjectMapper().registerModule(KotlinModule())

    @BeforeAll
    fun init() {
        cityService = CityService(cityRepository)
    }

    @Test
    fun getAllByNameContains() {
        val requestName = "London"
        var fileString =
            File(this.javaClass.classLoader.getResource("cictiestest.json").toURI()).inputStream().readBytes()
                .toString(Charsets.UTF_8).replace("\n", " ")
        val citiesList: List<CityDao> = objectMapper.readerForListOf(CityDao::class.java).readValue(fileString)
        Mockito.`when`(cityRepository.getAllByNameContains(requestName)).thenReturn(citiesList)
        var result = cityService.getAllByNameContains(requestName)
        assertEquals(result.size, 4)
    }
}