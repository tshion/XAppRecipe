package work.shion.androidrecipe.repositories.api_v1

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

/**
 * 通信部分のお試し環境
 */
@Config(sdk = [Build.VERSION_CODES.P])
@Ignore("お試し環境のため")
@RunWith(AndroidJUnit4::class)
class APIEndpointPlayground {

    private val moshi = Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()

    private val api = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()
            .let {
                Retrofit.Builder()
                        .addConverterFactory(MoshiConverterFactory.create(moshi))
                        .baseUrl("http://localhost:8080")
                        .client(it)
                        .build()
                        .create(APIEndpoint::class.java)
            }


    @Test
    fun editToDo() = runBlocking {
        try {
            val id = "1"
            val request = EditToDoRequest(
                    isFinish = true,
                    title = "何か編集したToDo"
            )
            api.editToDo(id, request)
            val a = 0
        } catch (ex: Exception) {
            throw ex
        }
    }

    @Test
    fun getToDo() = runBlocking {
        try {
            val result = api.getToDo()
            val a = 0
        } catch (ex: Exception) {
            throw ex
        }
    }

    @Test
    fun registerToDo() = runBlocking {
        try {
            val request = RegisterToDoRequest(
                    title = "何か新しいToDo"
            )
            api.registerToDo(request)
            val a = 0
        } catch (ex: Exception) {
            throw ex
        }
    }

    @Test
    fun removeToDo() = runBlocking {
        try {
            val id = "1"
            api.removeToDo(id)
            val a = 0
        } catch (ex: Exception) {
            throw ex
        }
    }
}
