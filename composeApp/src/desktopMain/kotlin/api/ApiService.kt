@file:Suppress("ktlint:standard:no-wildcard-imports")

package api

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlin.text.get

class ApiService {
    private val httpClient =
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    },
                )
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15000
            }
        }

    suspend fun fetchData(): String {
        val result =
            httpClient.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "dummyjson.com"
                    path("test")
                }
            }
        return if (result.status.isSuccess())
            {
                result.bodyAsText()
            } else {
            result.status.description
        }
    }
}
