package work.shion.baser.square

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient


/**
 * Stetho の通信インターセプターを追加
 * @see <a href="http://facebook.github.io/stetho/">Stetho</a>
 */
fun OkHttpClient.Builder.attachStetho() = addNetworkInterceptor(StethoInterceptor())
