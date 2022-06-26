package com.github.tshion.xapprecipe.webapp_data.apiConnpass.v1

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.github.tshion.xapprecipe.webapp_data.apiConpass.v1.ApiEndpoint
import com.github.tshion.xapprecipe.webapp_data.moshi.LocalDateAdapter
import com.github.tshion.xapprecipe.webapp_data.moshi.LocalDateTimeAdapter
import java.util.*

class ApiEndpointSample {

    private val api: ApiEndpoint
    private val jsonConverter: Converter.Factory


    init {
        jsonConverter = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .add(LocalDateTimeAdapter())
            .add(LocalDateAdapter())
            .build()
            .let { MoshiConverterFactory.create(it) }

        api = Retrofit.Builder()
            .addConverterFactory(jsonConverter)
            .baseUrl("https://connpass.com/api/")
            .build()
            .create(ApiEndpoint::class.java)
    }


    @Test
    fun getNew() {
        runBlocking {
            val response = api.getEventSearch()
            print(response)
        }
    }

    @Test
    fun getShion() {
        runBlocking {
            val response = api.getEventSearch(
                count = 100,
                nickname = arrayOf("Shion74431841"),
            )
            print(response)
        }
    }
}
