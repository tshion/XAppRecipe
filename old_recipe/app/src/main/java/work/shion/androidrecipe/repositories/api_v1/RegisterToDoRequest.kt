package work.shion.androidrecipe.repositories.api_v1

/**
 * ToDo新規登録のリクエストパラメータ
 * @param title ToDoタイトル
 *
 * * [Open API](https://tentashion.github.io/AndroidRecipe/swaggerui/index.html)
 */
data class RegisterToDoRequest(
        val title: String
)
