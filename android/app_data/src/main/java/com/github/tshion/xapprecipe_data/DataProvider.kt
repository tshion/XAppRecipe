package com.github.tshion.xapprecipe_data

import com.github.tshion.xapprecipe_core.usecases.ShowPdfUseCase
import com.github.tshion.xapprecipe_core.usecases.ShowPdfUseCaseDefault
import com.github.tshion.xapprecipe_core.usecases.ToDoTaskUseCase
import com.github.tshion.xapprecipe_core.usecases.ToDoTaskUseCaseDefault
import com.github.tshion.xapprecipe_data.repositories.PdfRepositoryDefault
import com.github.tshion.xapprecipe_data.repositories.ToDoTaskRepositoryAndroid
import com.github.tshion.xapprecipe_data.storage.CacheStorageDefault
import com.github.tshion.xapprecipe_data.utils.setIgnoreSslError
import com.github.tshion.xapprecipe_data.web.WebAccessor
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.net.URI
import java.util.*
import com.github.tshion.xapprecipe_data.api_xapp_v1.APIEndpoint as XAppV1Api

/**
 * データ提供クラス
 *
 * @param baseUrlXAppV1Api XApp API V1 の基本パス
 * @param cacheRoot キャッシュ配置先のルート
 * @param isDevelopment 開発設定かどうか
 */
public class DataProvider(
    baseUrlXAppV1Api: String,
    cacheRoot: File,
    isDevelopment: Boolean,
) {

    public val showPdfUseCase: ShowPdfUseCase
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
        val cacheStorage = CacheStorageDefault()
        val webAccessor = WebAccessor(
            client = OkHttpClient(),
        )


        val pdfRepository = PdfRepositoryDefault(
            cacheStorage = cacheStorage,
            webAccessor = webAccessor,
        )
        val todoTaskRepository = ToDoTaskRepositoryAndroid(
            apiXAppV1 = apiXAppApi,
        )


        showPdfUseCase = ShowPdfUseCaseDefault(
            cacheRoot = cacheRoot,
            pdfRepository = pdfRepository,
        )
        todoTaskUseCase = ToDoTaskUseCaseDefault(
            todoTaskRepository = todoTaskRepository,
        )
    }
}
