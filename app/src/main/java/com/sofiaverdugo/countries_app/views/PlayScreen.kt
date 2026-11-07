package com.sofiaverdugo.countries_app.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
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

fun randomOptions(
    countries: List<Country>,
    correctCountry: Country
): List<Country> {
    return countries
        .filter { it != correctCountry }
        .shuffled()
        .take(3)
}

@Composable
fun PlayScreen(countries: List<Country>) {

    if (countries.isEmpty()) {
        Text(
            "First load the list of countries",
            modifier = Modifier.padding(24.dp)
        )
        return
    }

    var country by remember {
        mutableStateOf(randomCountry(countries))
    }

    var options by remember {
        mutableStateOf(
            (randomOptions(countries, country) + country).shuffled()
        )
    }

    var message by remember { mutableStateOf("") }
    var correctAnswers by remember { mutableIntStateOf(0) }
    var attempts by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "What country is this?",
            style = MaterialTheme.typography.headlineMedium
        )

        Text("$correctAnswers out of $attempts correct")

        AsyncImage(
            model = country.flags.png,
            contentDescription = "Mystery flag",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 2f)
        )

        options.forEach { option ->

            Button(
                onClick = {
                    if (message.isEmpty()) {
                        attempts++

                        if (option == country) {
                            correctAnswers++
                            message = "Correct! (>0<)"
                        } else {
                            message = "Wrong. It was ${country.name} :("
                        }
                    }
                },
                enabled = message.isEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(option.name)
            }
        }

        if (message.isNotEmpty()) {

            Text(
                text = message,
                style = MaterialTheme.typography.titleLarge
            )

            Button(
                onClick = {
                    country = randomCountry(countries)

                    options = (
                            randomOptions(countries, country) + country
                            ).shuffled()

                    message = ""
                }
            ) {
                Text("Next")
            }
        }
    }
}