package com.github.tshion.xapprecipe_data.repositories

import com.github.tshion.xapprecipe_core.entities.ToDoTaskEntity
import com.github.tshion.xapprecipe_core.repositories.ToDoTaskRepository
import com.github.tshion.xapprecipe_data.api_xapp_v1.PostToDoRequest
import com.github.tshion.xapprecipe_data.api_xapp_v1.PutToDoRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.github.tshion.xapprecipe_data.api_xapp_v1.APIEndpoint as ApiXAppV1

/**
 * やること情報のデータ操作
 */
internal class ToDoTaskRepositoryAndroid(
    private val apiXAppV1: ApiXAppV1,
    private val dispatchersDefault: CoroutineDispatcher = Dispatchers.Default,
) : ToDoTaskRepository {

    /** 編集 */
    override suspend fun edit(data: ToDoTaskEntity) {
        val request = PutToDoRequest(
            is_finish = data.isFinish,
            title = data.title,
        )
        apiXAppV1.putToDo(data.id, request)
    }

    /** 一覧取得 */
    override suspend fun fetch(): List<ToDoTaskEntity> {
        val response = apiXAppV1.getToDo()
        val result = withContext(dispatchersDefault) {
            response.items
                .filter { it.id.isNotBlank() }
                .map {
                    ToDoTaskEntity(
                        id = it.id,
                        isFinish = it.is_finish,
                        title = it.title,
                        updateDate = it.update_date,
                    )
                }
        }
        return result
    }

    /**
     * 登録
     *
     * @param title やること名
     */
    override suspend fun register(title: String) {
        val request = PostToDoRequest(
            title = title,
        )
        apiXAppV1.postToDo(request)
    }

    /** 削除 */
    override suspend fun remove(data: ToDoTaskEntity) {
        apiXAppV1.deleteToDo(data.id)
    }
}
