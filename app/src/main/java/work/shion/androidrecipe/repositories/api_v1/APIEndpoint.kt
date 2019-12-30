package work.shion.androidrecipe.repositories.api_v1

import retrofit2.http.*

/**
 * API エンドポイント
 * * [Open API](https://tentashion.github.io/AndroidRecipe/swaggerui/index.html)
 */
interface APIEndpoint {

    /**
     * ToDo編集
     */
    @PUT("v1/todo/{id}")
    suspend fun editToDo(
            @Path("id") id: Int,
            @Body request: EditToDoRequest
    )

    /**
     * 登録したToDo 一覧の取得
     */
    @GET("v1/todo")
    suspend fun getToDo(): GetToDoResponse

    /**
     * ToDo新規登録
     */
    @POST("v1/todo")
    suspend fun registerToDo(
            @Body request: RegisterToDoRequest
    )

    /**
     * ToDo削除
     */
    @DELETE("v1/todo/{id}")
    suspend fun removeToDo(
            @Path("id") id: Int
    )
}
