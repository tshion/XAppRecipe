package com.github.tshion.xapprecipe_data

import androidx.core.net.toUri
import com.github.tshion.xapprecipe_core.usecases.ToDoTaskUseCase
import com.github.tshion.xapprecipe_core.usecases.ToDoTaskUseCaseDefault
import com.github.tshion.xapprecipe_data.repositories.ToDoTaskRepositoryAndroid
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.*
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
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
    private val baseUrlXAppV1ApiUri = baseUrlXAppV1Api.toUri()

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
                    setupDevSettings()
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


    /**
     * ※取り扱いに要注意！
     */
    private fun OkHttpClient.Builder.setupDevSettings(): OkHttpClient.Builder {
        val x509TrustManager = object : X509TrustManager {
            override fun checkClientTrusted(
                chain: Array<out X509Certificate>?,
                authType: String?
            ) {
            }

            override fun checkServerTrusted(
                chain: Array<out X509Certificate>?,
                authType: String?
            ) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }

        return this
            .sslSocketFactory(
                SSLContext.getInstance("SSL").apply {
                    init(
                        null,
                        arrayOf<TrustManager>(x509TrustManager),
                        SecureRandom()
                    )
                }.socketFactory,
                x509TrustManager,
            )
            .hostnameVerifier { hostname, _ -> baseUrlXAppV1ApiUri.host == hostname }
    }
}
