package com.github.tshion.xapprecipe_core.repositories

import com.github.tshion.xapprecipe_core.entities.ToDoTaskEntity

/**
 * やること情報のデータ操作の定義
 */
interface ToDoTaskRepositoryContract {

    /** 編集 */
    suspend fun edit(data: ToDoTaskEntity)

    /** 一覧取得 */
    suspend fun fetch(): List<ToDoTaskEntity>

    /** 登録 */
    suspend fun register(data: ToDoTaskEntity)

    /** 削除 */
    suspend fun remove(data: ToDoTaskEntity)
}
