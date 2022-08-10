package com.github.tshion.xapprecipe_data.api_connpass_v1

import com.github.tshion.xapprecipe_data.utils.LocalDateAdapter
import com.github.tshion.xapprecipe_data.utils.LocalDateTimeAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

/**
 * Connpass WebAPI のお試し環境
 */
@Ignore("お試し環境のため")
internal class ApiEndpointPlayground {

    private val moshiConverterFactory = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .addLast(KotlinJsonAdapterFactory())
        .add(LocalDateTimeAdapter())
        .add(LocalDateAdapter())
        .build()
        .let { MoshiConverterFactory.create(it) }

    private val api = Retrofit.Builder()
        .addConverterFactory(moshiConverterFactory)
        .baseUrl("https://connpass.com/api/")
        .build()
        .create(ApiEndpoint::class.java)


    @Test
    fun getNew() = runBlocking {
        val response = api.getEventSearch()
        print(response)
    }

    @Test
    fun getShion() = runBlocking {
        val response = api.getEventSearch(
            count = 100,
            nickname = arrayOf("Shion74431841"),
        )
        print(response)
    }
}
