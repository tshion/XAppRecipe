package com.github.tshion.xapprecipe_data.api_xapp_v1

import retrofit2.http.*

/**
 * XApp V1 WebAPI のエンドポイント定義
 * * [Open API](https://{{ api endpoint }}/swagger/index.html)
 */
internal interface APIEndpoint {

    @DELETE("v1/todo/{id}")
    suspend fun deleteToDo(
        @Path("id") id: String,
    )

    @GET("v1/todo")
    suspend fun getToDo(): GetToDoResponse

    @POST("v1/todo")
    suspend fun postToDo(
        @Body request: PostToDoRequest,
    )

    @PUT("v1/todo/{id}")
    suspend fun putToDo(
        @Path("id") id: String,
        @Body request: PutToDoRequest,
    )
}
