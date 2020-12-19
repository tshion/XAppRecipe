package work.shion.androidrecipe.repositories

import work.shion.androidrecipe.entities.ToDoEntity

/**
 * ToDo関連のリポジトリ定義
 */
interface IToDoRepository {

    /**
     * 編集
     */
    suspend fun edit(data: ToDoEntity)

    /**
     * 取得
     */
    suspend fun fetch(): List<ToDoEntity>

    /**
     * 登録
     */
    suspend fun register(data: ToDoEntity)

    /**
     * 削除
     */
    suspend fun remove(data: ToDoEntity)
}
