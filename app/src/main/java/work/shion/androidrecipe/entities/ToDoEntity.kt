package work.shion.androidrecipe.entities

/**
 * ToDoデータ
 * @param id 識別番号
 * @param isFinish 完了したかどうか
 * @param title ToDo名
 */
data class ToDoEntity(
        val id: String?,
        val isFinish: Boolean,
        val title: String
)
