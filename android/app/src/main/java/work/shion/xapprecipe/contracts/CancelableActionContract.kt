package work.shion.xapprecipe.contracts

/**
 * 実行中タスクのキャンセル定義
 */
interface CancelableActionContract {

    /** タスクキャンセル */
    fun cancelTasks()
}
