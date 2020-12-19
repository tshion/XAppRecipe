package work.shion.androidrecipe.usecases

import work.shion.androidrecipe.entities.ToDoEntity

/**
 * ToDo関連フローの定義
 * * [uml](https://tentashion.github.io/AndroidRecipe/uml/ToDo%E9%96%A2%E9%80%A3%E3%83%95%E3%83%AD%E3%83%BC.png)
 */
interface IToDoUseCase {

    /**
     * 新規生成
     */
    suspend fun create(data: ToDoEntity)

    /**
     * 編集
     */
    suspend fun edit(data: ToDoEntity)

    /**
     * 読み込み
     */
    suspend fun load(): List<ToDoEntity>

    /**
     * 削除
     */
    suspend fun remove(data: ToDoEntity)
}
