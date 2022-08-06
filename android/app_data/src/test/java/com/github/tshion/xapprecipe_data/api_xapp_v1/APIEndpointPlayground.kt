package com.github.tshion.xapprecipe_data.api_xapp_v1

import androidx.core.net.toUri
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.tshion.xapprecipe_data.DataProvider.Companion.setupDevSettings
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import work.shion.xapprecipe_data.BuildConfig
import java.util.*

/**
 * XApp V1 WebAPI のお試し環境
 */
@Ignore("お試し環境のため")
@RunWith(AndroidJUnit4::class)
internal class APIEndpointPlayground {

    private val moshiConverterFactory = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()
        .let { MoshiConverterFactory.create(it) }

    private val api = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .setupDevSettings(BuildConfig.API_XAPP_V1.toUri())
        .build()
        .let {
            Retrofit.Builder()
                .addConverterFactory(moshiConverterFactory)
                .baseUrl(BuildConfig.API_XAPP_V1)
                .client(it)
                .build()
                .create(APIEndpoint::class.java)
        }

    @Test
    fun deleteToDo() = runBlocking {
        try {
            val id = "1"
            api.deleteToDo(id)
            val a = 0
        } catch (ex: Exception) {
            throw ex
        }
    }

    @Test
    fun getToDo() = runBlocking {
        try {
            val result = api.getToDo()
            val a = result
        } catch (ex: Exception) {
            throw ex
        }
    }

    @Test
    fun postToDo() = runBlocking {
        try {
            val request = PostToDoRequest(
                title = "何か新しいToDo"
            )
            api.postToDo(request)
            val a = 0
        } catch (ex: Exception) {
            throw ex
        }
    }

    @Test
    fun putToDo() = runBlocking {
        try {
            val id = "1"
            val request = PutToDoRequest(
                is_finish = true,
                title = "何か編集したToDo"
            )
            api.putToDo(id, request)
            val a = 0
        } catch (ex: Exception) {
            throw ex
        }
    }
}
