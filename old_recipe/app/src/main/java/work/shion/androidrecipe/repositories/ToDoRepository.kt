package work.shion.androidrecipe.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import work.shion.androidrecipe.entities.ToDoEntity
import work.shion.androidrecipe.repositories.api_v1.APIEndpoint
import work.shion.androidrecipe.repositories.api_v1.EditToDoRequest
import work.shion.androidrecipe.repositories.api_v1.RegisterToDoRequest

/**
 * ToDo関連のリポジトリ
 */
class ToDoRepository(
        private val apiV1: APIEndpoint
) : IToDoRepository {

    /**
     * 編集
     */
    override suspend fun edit(data: ToDoEntity): Unit = withContext(Dispatchers.Default) {
        apiV1.editToDo(data.id!!, EditToDoRequest(
                isFinish = data.isFinish,
                title = data.title
        ))
    }

    /**
     * 取得
     */
    override suspend fun fetch() = withContext(Dispatchers.Default) {
        apiV1.getToDo().items
                ?.filter { item -> item.id.isNullOrBlank().not() }
                ?.map { item ->
                    ToDoEntity(
                            id = item.id!!,
                            isFinish = item.isFinish ?: false,
                            title = item.title ?: "(未設定)",
                            updateDate = item.updateDate
                    )
                }
                ?: listOf()
    }

    /**
     * 登録
     */
    override suspend fun register(data: ToDoEntity): Unit = withContext(Dispatchers.Default) {
        apiV1.registerToDo(RegisterToDoRequest(data.title))
    }

    /**
     * 削除
     */
    override suspend fun remove(data: ToDoEntity): Unit = withContext(Dispatchers.Default) {
        apiV1.removeToDo(data.id!!)
    }
}
