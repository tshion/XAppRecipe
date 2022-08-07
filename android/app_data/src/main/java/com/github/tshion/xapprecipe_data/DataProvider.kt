package com.github.tshion.xapprecipe_data

import com.github.tshion.xapprecipe_core.usecases.ToDoTaskUseCase
import com.github.tshion.xapprecipe_core.usecases.ToDoTaskUseCaseDefault
import com.github.tshion.xapprecipe_data.repositories.ToDoTaskRepositoryAndroid
import com.github.tshion.xapprecipe_data.utils.setIgnoreSslError
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.URI
import java.util.*
import com.github.tshion.xapprecipe_data.api_xapp_v1.APIEndpoint as XAppV1Api

/**
 * データ提供クラス
 *
 * @param baseUrlXAppV1Api XApp API V1 の基本パス
 * @param isDevelopment 開発設定かどうか
 */
public class DataProvider(
    baseUrlXAppV1Api: String,
    isDevelopment: Boolean,
) {

    public val todoTaskUseCase: ToDoTaskUseCase


    init {
        val moshiConverterFactory = Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .addLast(KotlinJsonAdapterFactory())
            .build()
            .let { MoshiConverterFactory.create(it) }

        val okHttpClient = OkHttpClient.Builder()
            .apply {
                if (isDevelopment) {
                    setIgnoreSslError(URI(baseUrlXAppV1Api))
                }
            }
            .build()


        val apiXAppApi = Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(baseUrlXAppV1Api)
            .client(okHttpClient)
            .build()
            .create(XAppV1Api::class.java)


        val todoTaskRepository = ToDoTaskRepositoryAndroid(
            apiXAppV1 = apiXAppApi,
        )


        todoTaskUseCase = ToDoTaskUseCaseDefault(
            todoTaskRepository = todoTaskRepository,
        )
    }
}
