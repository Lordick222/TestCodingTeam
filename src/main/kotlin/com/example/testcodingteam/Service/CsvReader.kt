package com.example.testcodingteam.Service

import com.example.testcodingteam.Repository.CityDao
import com.example.testcodingteam.Repository.TsvCityHeader
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.File


@Service
class CsvReader {

    fun readCsvFile(fileName: String): List<CityDao> {
        val bufferedReader: BufferedReader =
            File(this.javaClass.classLoader.getResource(fileName).toURI()).bufferedReader()
        val csvParser = CSVParser(bufferedReader, CSVFormat.newFormat('\t').withFirstRecordAsHeader())
        val cityes = mutableListOf<CityDao>()
        for (csvRecord in csvParser) {
            cityes.add(
                CityDao(
                    csvRecord.get(TsvCityHeader.ID.value).toLong(),
                    csvRecord.get(TsvCityHeader.NAME.value),
                    csvRecord.get(TsvCityHeader.LAT.value).toDouble(),
                    csvRecord.get(TsvCityHeader.LONG.value).toDouble()
                )
            )
        }
        return cityes
    }


}