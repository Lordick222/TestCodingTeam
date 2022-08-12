package com.example.testcodingteam.Repository.model

//можно расширить и мапить все поля, в задаче пока не нужно
data class CityDao(
    var id: Long? = null,
    var name: String? = null,
    var lat: Double? = null,
    var long: Double? = null,
)
