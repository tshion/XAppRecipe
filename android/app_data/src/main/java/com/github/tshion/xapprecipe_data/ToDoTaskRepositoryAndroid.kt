package com.github.tshion.xapprecipe_data

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
public class ToDoTaskRepositoryAndroid(
    private val apiXAppV1: ApiXAppV1,
    private val dispatchersDefault: CoroutineDispatcher = Dispatchers.Default,
) : ToDoTaskRepository {

    /** 編集 */
    override suspend fun edit(data: ToDoTaskEntity) {
        PutToDoRequest(
            is_finish = data.isFinish,
            title = data.title,
        ).also { apiXAppV1.putToDo(data.id, it) }
    }

    /** 一覧取得 */
    override suspend fun fetch(): List<ToDoTaskEntity> = withContext(dispatchersDefault) {
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

    /**
     * 登録
     *
     * @param title やること名
     */
    override suspend fun register(title: String) {
        PostToDoRequest(
            title = title,
        ).also { apiXAppV1.postToDo(it) }
    }

    /** 削除 */
    override suspend fun remove(data: ToDoTaskEntity) {
        apiXAppV1.deleteToDo(data.id)
    }
}
