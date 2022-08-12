package com.example.testcodingteam.Repository

import com.example.testcodingteam.Service.CsvReader
import mu.KLogging
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct


@Repository
class CityRepository(private val csvReader: CsvReader) {

    private companion object : KLogging()

    private val cityes = mutableListOf<CityDao>()

    @PostConstruct
    fun enrichDataFromCsv() {
        cityes.addAll(csvReader.readCsvFile("cities_canada-usa.tsv"))
        logger.info { cityes }
    }


}