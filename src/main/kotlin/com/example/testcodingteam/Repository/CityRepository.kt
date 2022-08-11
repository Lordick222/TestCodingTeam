package com.example.testcodingteam.Repository

import com.example.testcodingteam.Service.CsvReader
import mu.KLogging
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct


@Repository
class CityRepository(private val csvReader: CsvReader) {

    private companion object : KLogging()

    private val cityes = mutableListOf<CityDto>()


    @PostConstruct
    fun enrichDataFromCsv() {
        cityes.addAll(csvReader.readCsvFile(this.javaClass.classLoader.getResource("cities_canada-usa.tsv").path))
        logger.info { cityes }
    }


}