package work.shion.androidrecipe.pages.launcher

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.coroutines.*
import work.shion.androidrecipe.R
import kotlin.coroutines.CoroutineContext

class MainFragment : CoroutineScope, Fragment(R.layout.launcher) {

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    override fun onStart() {
        super.onStart()
        launch(Dispatchers.Default) {
            delay(1000)
            Navigation.findNavController(view!!)
                    .navigate(R.id.navact_todo_list)
        }
    }

    override fun onStop() {
        coroutineContext.cancelChildren()
        super.onStop()
    }
}
