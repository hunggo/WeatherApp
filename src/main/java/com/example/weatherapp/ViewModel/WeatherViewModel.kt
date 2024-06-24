package com.example.weatherapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.weatherapp.Repository.WetherRepository
import com.example.weatherapp.Server.ApiClient
import com.example.weatherapp.Server.ApiServices
import retrofit2.create

class WeatherViewModel (private val repository:WetherRepository):ViewModel(){

    constructor():this(WetherRepository(ApiClient().getClient().create(ApiServices::class.java)))

    fun loadCurrentWeather(lat:Double,lng:Double,unit:String)=
        repository.getCurrentWeather(lat,lng,unit)
    fun loadForecastWeather(lat:Double,lng:Double,unit:String)=
        repository.getForecastWeather(lat,lng,unit)
}