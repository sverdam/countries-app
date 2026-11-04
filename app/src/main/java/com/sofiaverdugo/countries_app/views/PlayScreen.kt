package com.sofiaverdugo.countries_app.views

import com.sofiaverdugo.countries_app.models.Country
import java.text.Normalizer

fun normalize(text: String): String {
    val separated = Normalizer.normalize(text.trim().lowercase(), Normalizer.Form.NFD)
    return separated.replace(Regex("\\p{Mn}"), "")
}
fun isCorrect(answer: String, country: Country): Boolean {
    val r = normalize(answer)
    return r == normalize(country.name)
}

fun randomCountry(countries: List<Country>): Country {
    return countries.random()
}

// RANDOM OPTIONS