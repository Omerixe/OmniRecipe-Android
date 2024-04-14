package ch.omerixe.data.domain.ktor

import ch.omerixe.data.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.time.Duration

internal val client = HttpClient(OkHttp) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        })
    }

    engine {
        config {
            connectTimeout(Duration.ofMillis(2_000))
        }
    }

    defaultRequest {
        headers.append("X-API-Key", BuildConfig.API_KEY)
        url(BuildConfig.API_URL)
    }
}