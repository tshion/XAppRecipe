package work.shion.androidrecipe.repositories.api_v1

import retrofit2.http.*

interface APIEndpoint {

    @PUT("v1/todo/{id}")
    suspend fun editToDo(
            @Path("id") id: Int,
            @Body request: EditToDoRequest
    )

    @GET("v1/todo")
    suspend fun getToDo(): GetToDoResponse

    @POST("v1/todo")
    suspend fun registerToDo(
            @Body request: RegisterToDoRequest
    )

    @DELETE("v1/todo/{id}")
    suspend fun removeToDo(
            @Path("id") id: Int
    )
}
