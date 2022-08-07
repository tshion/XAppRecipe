package com.github.tshion.xapprecipe_data.utils

import okhttp3.OkHttpClient
import java.net.URI
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

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
