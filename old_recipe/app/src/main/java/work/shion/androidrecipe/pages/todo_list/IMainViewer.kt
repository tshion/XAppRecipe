package work.shion.androidrecipe.pages.todo_list

import work.shion.androidrecipe.entities.ToDoEntity

interface IMainViewer {

    fun hideLoading()

    fun setList(list: List<ToDoEntity>)

    fun showError(error: Throwable)

    fun showLoading()

    fun update()
}
