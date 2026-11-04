package com.sofiaverdugo.countries_app.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val URL_BASE = "https://countries.dev/"
    val api: CountriesAPI = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CountriesAPI::class.java)
}
