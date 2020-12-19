package work.shion.androidrecipe.usecases

import work.shion.androidrecipe.entities.ToDoEntity
import work.shion.androidrecipe.repositories.IToDoRepository

/**
 * ToDo関連フロー
 */
class ToDoUseCase(
        private val todoRepository: IToDoRepository
) : IToDoUseCase {

    /**
     * 新規生成
     * ToDo: 入力検証
     */
    override suspend fun create(data: ToDoEntity) = todoRepository.register(data)

    /**
     * 編集
     * ToDo: 入力検証
     */
    override suspend fun edit(data: ToDoEntity) = todoRepository.edit(data)

    /**
     * 読み込み
     */
    override suspend fun load(): List<ToDoEntity> = todoRepository.fetch()

    /**
     * 削除
     * ToDo: 入力検証
     */
    override suspend fun remove(data: ToDoEntity) = todoRepository.remove(data)
}
