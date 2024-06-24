package com.example.weatherapp.Repository

import com.example.weatherapp.Server.ApiServices

class CityRepository(private val api:ApiServices) {
    fun getCity(q:String,limit:Int)=
        api.getCitiesList(q,limit,"3633fa7ab8d272686078a647d780a2ed")

}