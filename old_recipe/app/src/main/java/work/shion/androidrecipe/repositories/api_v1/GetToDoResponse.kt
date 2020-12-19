package work.shion.androidrecipe.repositories.api_v1

/**
 * 登録したToDo 一覧の取得レスポンス
 * @param items ToDo一覧
 *
 * * [Open API](https://tentashion.github.io/AndroidRecipe/swaggerui/index.html)
 */
data class GetToDoResponse(
        val items: List<ToDo>?
)
