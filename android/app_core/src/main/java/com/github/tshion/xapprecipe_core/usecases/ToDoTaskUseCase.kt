package com.github.tshion.xapprecipe_core.usecases

import com.github.tshion.xapprecipe_core.entities.ToDoTaskEntity
import kotlinx.coroutines.flow.StateFlow

/**
 * やること情報の取り扱い方法定義
 *
 * 1. 最初にデータストリームを取得する
 * 2. 最新データを取得し、ストリームに流す
 * 3. 各操作を行い、手順1 のものを通じて変化を受け取る
 *     * 新規作成
 *     * 編集
 *     * タスクの完了 or 諦める
 */
public interface ToDoTaskUseCase {

    /** やること情報の変化が流れてくるストリーム */
    public val stream: StateFlow<List<ToDoTaskEntity>>


    /** 最新データをストリームに流す */
    public suspend fun refreshStream()

    /**
     * 新規作成
     *
     * @param todoName やること名
     */
    public suspend fun create(todoName: String?)

    /**
     * 編集
     *
     * @param target 対象データ
     * @param newToDoName 変更後のやること名
     */
    public suspend fun edit(
        target: ToDoTaskEntity,
        newToDoName: String?,
    )

    /** タスクの完了 */
    public suspend fun done(target: ToDoTaskEntity)

    /** タスクを諦める */
    public suspend fun giveUp(target: ToDoTaskEntity)
}
