package com.example.testcodingteam.Service

import com.example.testcodingteam.Repository.TsvCityHeader
import com.example.testcodingteam.Repository.model.CityDao
import mu.KLogging
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.File
import java.net.URI


@Service
class TsvReader {

    private companion object : KLogging()

    fun readTsvFile(fileName: URI): List<CityDao> {
        val bufferedReader: BufferedReader =
            File(fileName).bufferedReader()
        val csvParser = CSVParser(bufferedReader, CSVFormat.newFormat('\t').withFirstRecordAsHeader())
        val cityes = mutableListOf<CityDao>()
        csvParser.forEach {
            try {
                cityes.add(
                    CityDao(
                        it.get(TsvCityHeader.ID.value).toLong(),
                        it.get(TsvCityHeader.NAME.value),
                        it.get(TsvCityHeader.LAT.value).toDouble(),
                        it.get(TsvCityHeader.LONG.value).toDouble()
                    )
                )
            } catch (e: Exception) {
                logger.error(e) { "Can not get record with id: $it.get(TsvCityHeader.ID.value)" }
            }
        }
        return cityes
    }
}