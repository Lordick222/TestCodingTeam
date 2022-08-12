package com.example.testcodingteam.dto

class CityScoresDto(
    var name: String? = null,
    var lat: Double,
    var long: Double,
    var score: Double
) {
    companion object {
        fun fromCityDto(cityDto: CityDto, score: Double): CityScoresDto {
            return CityScoresDto(
                cityDto.name,
                cityDto.lat,
                cityDto.long,
                score
            )
        }
    }
}