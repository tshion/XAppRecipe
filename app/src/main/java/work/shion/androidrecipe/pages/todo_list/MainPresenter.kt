package work.shion.androidrecipe.pages.todo_list

import kotlinx.coroutines.*
import work.shion.androidrecipe.ModelProvider
import work.shion.androidrecipe.entities.ToDoEntity
import work.shion.androidrecipe.usecases.IToDoUseCase
import java.lang.ref.WeakReference

class MainPresenter(
        private val todoUseCase: IToDoUseCase = ModelProvider.todoUseCase,
        private val viewer: WeakReference<IMainViewer>
) : IMainPresenter {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)


    override fun cancelTask() {
        scope.coroutineContext.cancelChildren()
    }

    override fun create() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun edit(item: ToDoEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun load() {
        scope.launch {
            val list = todoUseCase.load()
            withContext(Dispatchers.Main) {
                viewer.get()?.setList(list)
            }
        }
    }

    override fun remove(item: ToDoEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
