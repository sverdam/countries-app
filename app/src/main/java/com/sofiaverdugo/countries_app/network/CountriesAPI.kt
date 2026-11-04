package com.sofiaverdugo.countries_app.network

import com.sofiaverdugo.countries_app.models.Country
import retrofit2.http.GET
import retrofit2.http.Query

interface CountriesAPI {
    // GET https://countries.dev/countries?fields=...
    @GET("countries")
    suspend fun getCountries(
        @Query("fields") campos: String
    ): List<Country>
}