package com.sofiaverdugo.countries_app.models

data class Country(
    val name: String,
    val nativeName: String,
    val alpha3Code: String,
    val translations: Translations?,
    val flags: Flag,
    val capital: String?,
    val region: String,
    val demonym: String,
    val population: Long,
    val languages: List<Language>?,
    val currencies: List<Currency>?
) {
    val nameIs: String
        get() = translations?.es ?: name
}
data class Translations(val es: String?)
data class Flag(val png: String)
data class Language(val name: String)
data class Currency(val name: String, val symbol: String?)