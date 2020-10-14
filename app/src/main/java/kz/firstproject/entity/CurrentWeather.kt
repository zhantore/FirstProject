package kz.firstproject.entity

import com.google.gson.annotations.SerializedName

class CurrentWeather {

    @SerializedName("temp")
    lateinit var temp: String

    @SerializedName("humidity")
    lateinit var humidity: String
}