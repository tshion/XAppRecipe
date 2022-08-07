package com.github.tshion.xapprecipe_data.utils

import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import java.net.URI
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * OkHttp Call の実行をKotlin Coroutine で待つ
 */
@JvmSynthetic
internal suspend fun Call.await(): Response {
    return suspendCancellableCoroutine { continuation ->
        continuation.invokeOnCancellation {
            cancel()
        }
        enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                continuation.resumeWithException(e)
            }

            override fun onResponse(call: Call, response: Response) {
                continuation.resume(response)
            }
        })
    }
}

/**
 * SSL 証明書エラーの無視する設定の適用
 * ※開発用途以外で使わないでください
 *
 * @param target 無視するサーバーのURL
 */
@JvmSynthetic
internal fun OkHttpClient.Builder.setIgnoreSslError(target: URI): OkHttpClient.Builder {
    val x509TrustManager = object : X509TrustManager {
        override fun checkClientTrusted(
            chain: Array<out X509Certificate>?,
            authType: String?,
        ) {
        }

        override fun checkServerTrusted(
            chain: Array<out X509Certificate>?,
            authType: String?,
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
                    SecureRandom(),
                )
            }.socketFactory,
            x509TrustManager,
        )
        .hostnameVerifier { hostname, _ -> target.host == hostname }
}
