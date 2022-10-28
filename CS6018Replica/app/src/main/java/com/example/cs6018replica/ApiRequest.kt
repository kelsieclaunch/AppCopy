package com.example.cs6018replica

class ApiRequest(
    var main: Currency,
    var name: String,
    var wind: Wind,
    var weather: List<WeatherInfo>
)