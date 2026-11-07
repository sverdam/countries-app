package com.sofiaverdugo.countries_app.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sofiaverdugo.countries_app.viewmodels.CountriesVM

@Composable
fun AppNavigation(
    viewModel: CountriesVM,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "countries",
        modifier = modifier
    ) {
        composable("countries") {
            CountriesScreen(
                countries = viewModel.countries.value,
                loading = viewModel.loading.value,
                error = viewModel.error.value,
                onRetry = { viewModel.loadCountries() },
                onCountryClick = { code -> navController.navigate("detail/$code") },
                onPlay = { navController.navigate("play") }
            )
        }
        composable("detail/{code}") { input ->
            val code = input.arguments?.getString("code") ?: ""
            DetailScreen(
                country = viewModel.searchCountry(code),
                onBack = { navController.popBackStack() }
            )
        }
        composable("play") {
            PlayScreen(countries = viewModel.countries.value)
        }
    }
}