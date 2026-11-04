package com.sofiaverdugo.countries_app.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofiaverdugo.countries_app.models.Country
import com.sofiaverdugo.countries_app.network.RetrofitClient
import kotlinx.coroutines.launch

class CountriesVM : ViewModel() {
    private val _countries = mutableStateOf<List<Country>>(value = emptyList())
    val countries: State<List<Country>> = _countries
    private val _loading = mutableStateOf(true)
    val loading: State<Boolean> = _loading
    private val _error = mutableStateOf("")
    val error: State<String> = _error

    init {
        loadCountries()
    }

    fun loadCountries() {
        _loading.value = true
        _error.value = ""
        viewModelScope.launch {
            try {
                val list = RetrofitClient.api.getCountries(FIELDS)
                _countries.value = list.sortedBy { it.nameIs }
            } catch (e: Exception) {
                _error.value = "Couldn't load countries..."
            }
            _loading.value = false
        }
    }
    fun searchCountry(code: String): Country? {
        return _countries.value.find { it.alpha3Code == code }
    }
    companion object {
        const val FIELDS =
            "name,nativeName,alpha3Code,translations,flags,capital,region,demonym,population,languages,currencies"
    }
}