package com.example.weatherapp.Repository

import com.example.weatherapp.Server.ApiServices

class WetherRepository(private val api:ApiServices) {
    fun getCurrentWeather(lat:Double,lng:Double,unit:String)=
        api.getCurrentWeather(lat,lng,unit,"3633fa7ab8d272686078a647d780a2ed")
  fun getForecastWeather(lat:Double,lng:Double,unit:String)=
        api.getForecastWeather(lat,lng,unit,"3633fa7ab8d272686078a647d780a2ed")

}