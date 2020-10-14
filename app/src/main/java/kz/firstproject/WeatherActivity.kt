package kz.firstproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_weather.*
import kz.firstproject.adapters.WeatherAdapter
import kz.firstproject.api.ApiClass
import kz.firstproject.entity.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiClass::class.java)

        retrofit.getWeatherData("43.200576",
                "76.892289",
                "metric",
                "hourly,daily",
                "cc9c25043a889962ade46f38372d8d80").enqueue(object : Callback<WeatherData> {

            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                val weatherAdapter =
                        WeatherAdapter(applicationContext, response.body()!!)
                weatherRV.apply {
                    layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
                    adapter = weatherAdapter
                }
            }


            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
            }

        })
    }
}