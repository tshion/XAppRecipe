package work.shion.androidrecipe.repositories.api_v1

import com.squareup.moshi.Json

/**
 * ToDoデータ
 * @param id 識別番号
 * @param isFinish 完了フラグ
 * @param title ToDoタイトル
 *
 * * [Open API](https://tentashion.github.io/AndroidRecipe/swaggerui/index.html)
 */
data class ToDo(
        val id: String?,
        @Json(name = "is_finish") val isFinish: Boolean?,
        val title: String?
)
