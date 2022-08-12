package com.example.testcodingteam.Repository

import com.example.testcodingteam.Repository.model.CityDao
import com.example.testcodingteam.Service.TsvReader
import mu.KLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct


@Repository
class CityRepository(private val tsvReader: TsvReader) {

    private companion object : KLogging()

    private val cityes = mutableMapOf<String, CityDao>()

    @Value("\${path-to-csv}")
    final lateinit var path: String

    @PostConstruct
    fun enrichDataFromCsv() {
        cityes.putAll(tsvReader.readTsvFile(
            this.javaClass.classLoader.getResource(path).toURI()
        ).map { it.name!! to it }
            .toMap()
        )
    }

    fun getAllByNameContains(name: String): List<CityDao> {
        var result = cityes.filter { it.key.contains(name) }.values.toList()
        logger.debug { "Founded ${result.size} sities by name : $name" }
        return result
    }

    fun getAllTowns(): HashMap<String, CityDao> {
        return HashMap(cityes)
    }
}