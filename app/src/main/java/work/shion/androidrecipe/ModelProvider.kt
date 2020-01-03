package work.shion.androidrecipe

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import work.shion.androidrecipe.repositories.ToDoRepository
import work.shion.androidrecipe.repositories.api_v1.APIEndpoint
import work.shion.androidrecipe.usecases.IToDoUseCase
import work.shion.androidrecipe.usecases.ToDoUseCase

/**
 * ToDo: DI ライブラリに差し替えたい
 */
object ModelProvider {

    lateinit var todoUseCase: IToDoUseCase


    fun init() {
        val apiBuilder = OkHttpClient.Builder()
                .build()
                .let {
                    Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(BuildConfig.APIEndpoint)
                            .client(it)
                            .build()
                }
        val apiV1 = apiBuilder.create(APIEndpoint::class.java)


        val todoRepository = ToDoRepository(apiV1)


        todoUseCase = ToDoUseCase(todoRepository)
    }
}
