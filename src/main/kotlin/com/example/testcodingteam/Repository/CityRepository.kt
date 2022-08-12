package com.example.testcodingteam.Repository

import com.example.testcodingteam.Repository.model.CityDao
import com.example.testcodingteam.Service.TsvReader
import mu.KLogging
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct


@Repository
class CityRepository(private val tsvReader: TsvReader) {

    private companion object : KLogging()

    private val cityes = mutableMapOf<String, CityDao>()

    @PostConstruct
    fun enrichDataFromCsv() {
        cityes.putAll(tsvReader.readTsvFile(
            this.javaClass.classLoader.getResource("cities_canada-usa.tsv").toURI()
        ).map { it.name!! to it }
            .toMap()
        )
    }

    fun getAllByNameContains(name: String): List<CityDao> {
        return cityes.filter { it.key.contains(name) }.values.toList()
    }
}