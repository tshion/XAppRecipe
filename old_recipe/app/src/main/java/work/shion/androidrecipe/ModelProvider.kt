package work.shion.androidrecipe

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import work.shion.androidrecipe.repositories.ToDoRepository
import work.shion.androidrecipe.repositories.api_v1.APIEndpoint
import work.shion.androidrecipe.usecases.IToDoUseCase
import work.shion.androidrecipe.usecases.ToDoUseCase
import java.util.*

/**
 * ToDo: DI ライブラリに差し替えたい
 */
object ModelProvider {

    lateinit var todoUseCase: IToDoUseCase


    fun init() {
        val moshi = Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter())
                .add(KotlinJsonAdapterFactory())
                .build()

        val apiBuilder = OkHttpClient.Builder()
                .build()
                .let {
                    Retrofit.Builder()
                            .addConverterFactory(MoshiConverterFactory.create(moshi))
                            .baseUrl(BuildConfig.APIEndpoint)
                            .client(it)
                            .build()
                }
        val apiV1 = apiBuilder.create(APIEndpoint::class.java)


        val todoRepository = ToDoRepository(apiV1)


        todoUseCase = ToDoUseCase(todoRepository)
    }
}
