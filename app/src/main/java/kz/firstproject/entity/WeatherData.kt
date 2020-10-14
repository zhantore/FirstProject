package kz.firstproject.entity

import com.google.gson.annotations.SerializedName


class WeatherData {
    @SerializedName("timezone")
    lateinit var timezone: String

    @SerializedName("current")
    lateinit var current: CurrentWeather
}