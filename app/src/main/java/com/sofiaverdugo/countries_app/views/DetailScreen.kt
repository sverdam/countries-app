package com.sofiaverdugo.countries_app.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.sofiaverdugo.countries_app.models.Country

@Composable
fun Data(tag: String, value: String) {
    Column {
        Text(
            text = tag,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}
@Composable
fun DetailScreen(country: Country?, onBack: () -> Unit) {
    if (country == null) {
        Text("Country not found", modifier = Modifier.padding(24.dp))
        return
    }
    val languages = country.languages?.joinToString { it.name } ?: "No info."
    val currencies = country.currencies?.joinToString { "${it.name} (${it.symbol})" } ?: "No info."
    val population = "%,d habitantes".format(country.population)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AsyncImage(
            model = country.flags.png,
            contentDescription = "Bandera de ${country.name}",
            modifier = Modifier.fillMaxWidth().aspectRatio(3f / 2f)
        )
        Text(country.name, style = MaterialTheme.typography.headlineLarge)
        Text(country.nativeName, style = MaterialTheme.typography.bodyMedium)
        HorizontalDivider()
        Data("Capital", country.capital ?: "No Capital")
        Data("Region", country.region)
        Data("Population", population)
        Data("Languages", languages)
        Data("Currency", currencies)
        Button(onClick = onBack) { Text("Go Back") }
    }
}