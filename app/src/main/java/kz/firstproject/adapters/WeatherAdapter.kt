package kz.firstproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.firstproject.R
import kz.firstproject.entity.WeatherData

class WeatherAdapter(val context: Context, val weatherData: WeatherData) : RecyclerView.Adapter<WeatherAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityTV: TextView = itemView.findViewById(R.id.cityTV)
        val tempTV: TextView = itemView.findViewById(R.id.tempTV)
        val humidityTV: TextView = itemView.findViewById(R.id.humidityTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.weather_item, parent, false)

        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.cityTV.text = "Город: " + weatherData.timezone
        holder.tempTV.text = "Температура: " + weatherData.current.temp + " °C"
        holder.humidityTV.text = "Влажность: " + weatherData.current.humidity + " %"
    }

    override fun getItemCount(): Int {
        return 1
    }

}