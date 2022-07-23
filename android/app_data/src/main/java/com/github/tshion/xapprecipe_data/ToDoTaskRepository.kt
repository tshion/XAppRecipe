package com.github.tshion.xapprecipe_data

import com.github.tshion.xapprecipe_data.api_xapp_v1.PostToDoRequest
import com.github.tshion.xapprecipe_data.api_xapp_v1.PutToDoRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import work.shion.xapprecipe_core.entities.ToDoTaskEntity
import work.shion.xapprecipe_core.repositories.ToDoTaskRepositoryContract
import com.github.tshion.xapprecipe_data.api_xapp_v1.APIEndpoint as ApiXAppV1

/**
 * やること情報のデータ操作
 */
class ToDoTaskRepository(
    private val apiXAppV1: ApiXAppV1,
    private val dispatchersDefault: CoroutineDispatcher = Dispatchers.Default,
) : ToDoTaskRepositoryContract {

    /** 編集 */
    override suspend fun edit(data: ToDoTaskEntity) {
        PutToDoRequest(
            is_finish = data.isFinish,
            title = data.title,
        ).also { apiXAppV1.putToDo(data.id!!, it) }
    }

    /** 一覧取得 */
    override suspend fun fetch() = withContext(dispatchersDefault) {
        val response = apiXAppV1.getToDo().items
        response
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

    /** 登録 */
    override suspend fun register(data: ToDoTaskEntity) {
        PostToDoRequest(
            title = data.title,
        ).also { apiXAppV1.postToDo(it) }
    }

    /** 削除 */
    override suspend fun remove(data: ToDoTaskEntity) {
        apiXAppV1.deleteToDo(data.id!!)
    }
}
