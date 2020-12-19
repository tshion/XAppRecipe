package work.shion.androidrecipe.pages.todo_list

import work.shion.androidrecipe.entities.ToDoEntity

interface IMainPresenter {

    fun cancelTask()

    fun create()

    fun edit(item: ToDoEntity)

    fun load()

    fun remove(item: ToDoEntity)
}
