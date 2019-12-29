package work.shion.androidrecipe.repositories.api_v1

data class EditToDoRequest(
        val isFinish: Boolean,
        val title: String
)
