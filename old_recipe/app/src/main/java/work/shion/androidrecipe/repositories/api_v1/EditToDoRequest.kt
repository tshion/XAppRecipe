package work.shion.androidrecipe.repositories.api_v1

import com.squareup.moshi.Json

/**
 * ToDo編集のリクエストパラメータ
 * @param isFinish 完了フラグ
 * @param title ToDoタイトル
 *
 * * [Open API](https://tentashion.github.io/AndroidRecipe/swaggerui/index.html)
 */
data class EditToDoRequest(
        @Json(name = "is_finish") val isFinish: Boolean,
        val title: String
)
