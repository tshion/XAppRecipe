package com.github.tshion.xapprecipe.webapp.contracts

/**
 * 実行中タスクのキャンセル定義
 */
interface CancelableActionContract {

    /** タスクキャンセル */
    fun cancelTasks()
}
