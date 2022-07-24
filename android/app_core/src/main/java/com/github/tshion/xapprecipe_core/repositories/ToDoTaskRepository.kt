package com.github.tshion.xapprecipe_core.repositories

import com.github.tshion.xapprecipe_core.entities.ToDoTaskEntity

/**
 * やること情報のデータ操作の定義
 */
public interface ToDoTaskRepository {

    /** 編集 */
    public suspend fun edit(data: ToDoTaskEntity)

    /** 一覧取得 */
    public suspend fun fetch(): List<ToDoTaskEntity>

    /**
     * 登録
     *
     * @param title やること名
     */
    public suspend fun register(title: String)

    /** 削除 */
    public suspend fun remove(data: ToDoTaskEntity)
}
