package com.example.testcodingteam.Repository

import com.example.testcodingteam.Service.TsvReader
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import kotlin.test.assertContains
import kotlin.test.assertEquals

@ActiveProfiles(value = ["test"])
@SpringBootTest
internal class CityRepositoryTest {

    @Autowired
    private val tsvReader: TsvReader = TsvReader()

    @Autowired
    private val cityRepository: CityRepository = CityRepository(tsvReader)

    @Test
    fun enrichDataFromCsv() {
        assertEquals(cityRepository.getAllTowns().size, 5790)
    }

    @Test
    fun getAllByNameContains() {
        val cityName = "London"
        val result = cityRepository.getAllByNameContains(cityName)
        assertEquals(result.size, 4)
        val resultNames = result.map { it.name }.toList()
        assertContains(resultNames, "London" )
        assertContains(resultNames, "Londontowne" )
        assertContains(resultNames, "New London" )
        assertContains(resultNames, "Londonderry" )
    }
}