package com.example.synchronyproject

import models.CountryInfo
import retrofit2.Call
import retrofit2.http.GET

public interface CountryAPI {
    @GET("rest/v2/all?fields=name;capital;region;subregion;population")
    fun getCountryList(): Call<ArrayList<CountryInfo>>
}