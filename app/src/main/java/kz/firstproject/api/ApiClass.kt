package kz.firstproject.api

import kz.firstproject.entity.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClass {
    @GET("/data/2.5/onecall")
    fun getWeatherData(@Query("lat") lat: String,
                       @Query("lon") lon: String,
                       @Query("units") units: String,
                       @Query("exclude") exclude: String,
                       @Query("appid") appid: String): Call<WeatherData>
}