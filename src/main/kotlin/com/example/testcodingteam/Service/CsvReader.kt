package com.example.testcodingteam.Service

import com.example.testcodingteam.Repository.CityDto
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.MappingIterator
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import org.springframework.stereotype.Service


@Service
class CsvReader {

    val objectMapper = CsvMapper()

    fun readCsvFile(fileName: String): List<CityDto> {
        val schema: CsvSchema = objectMapper.schemaFor(CityDto::class.java).withColumnSeparator('\t').withHeader()
        val it: MappingIterator<CityDto> = objectMapper
            .readerFor(CityDto::class.java)
            .with(schema)
            .readValues(this.javaClass.classLoader.getResource("cities_canada-usa.tsv"))
        return it.readAll()
    }
}