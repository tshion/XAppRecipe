package com.github.tshion.xapprecipe_data

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.github.tshion.xapprecipe_data.api_xapp_v1.APIEndpoint
import com.github.tshion.xapprecipe_data.repositories.ToDoTaskRepositoryAndroid
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

@RunWith(AndroidJUnit4::class)
internal class OkHttpInterceptorTest {

    private val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @get:Rule
    val testRule = ActivityTestRule(OkHttpTestActivity::class.java)


    @Test
    fun InspectorWork() {
        val server = MockWebServer()
        try {
            server.enqueue(MockResponse().apply {
                setResponseCode(400)
            })

            testRule.activity?.repository = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(server.url("/"))
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor { chain -> chain.proceed(chain.request()) }.build()
                )
                .build()
                .create(APIEndpoint::class.java)
                .let { ToDoTaskRepositoryAndroid(it) }

            onView(withText(OkHttpTestActivity.testSuccess)).perform(click())
        } finally {
            server.shutdown()
        }
    }

    @Test
    fun InspectorNotWork() {
        val server = MockWebServer()
        try {
            server.enqueue(MockResponse().apply {
                setResponseCode(400)
            })

            testRule.activity?.repository = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(server.url("/"))
                .client(OkHttpClient.Builder().addInterceptor { chain -> throw Error() }.build())
                .build()
                .create(APIEndpoint::class.java)
                .let { ToDoTaskRepositoryAndroid(it) }

            onView(withText(OkHttpTestActivity.testSuccess)).perform(click())
        } finally {
            server.shutdown()
        }
    }
}
