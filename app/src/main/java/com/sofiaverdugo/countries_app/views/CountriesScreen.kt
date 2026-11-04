package com.sofiaverdugo.countries_app.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.sofiaverdugo.countries_app.models.Country

@Composable
fun CountryCard(country: Country, onClick: () -> Unit) {
    Card(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = country.flags.png,
                contentDescription = "${country.demonym} flag",
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(72.dp).height(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(country.name, style = MaterialTheme.typography.titleMedium)
                Text(country.region, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun CountriesScreen(
    countries: List<Country>,
    loading: Boolean,
    error: String,
    onRetry: () -> Unit,
    onCountryClick: (String) -> Unit,
    onPlay: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Countries", style = MaterialTheme.typography.headlineMedium)
            TextButton(onClick = onPlay) { Text("Guess the flag!") }
        }


        if (loading) {
            CircularProgressIndicator(modifier = Modifier.padding(32.dp))
        } else if (error.isNotEmpty()) {
            Text(text = error, modifier = Modifier.padding(16.dp))
            Button(onClick = onRetry) { Text("Try Again") }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(countries) { country ->
                    CountryCard(country, onClick = { onCountryClick(country.alpha3Code) })
                }
            }
        }
    }
}