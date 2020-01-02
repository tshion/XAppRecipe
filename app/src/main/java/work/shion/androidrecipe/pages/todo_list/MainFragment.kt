package work.shion.androidrecipe.pages.todo_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import work.shion.androidrecipe.R
import work.shion.androidrecipe.databinding.TodoListBinding
import work.shion.androidrecipe.entities.ToDoEntity
import java.lang.ref.WeakReference

class MainFragment : Fragment(), IMainViewer {

    private lateinit var binding: TodoListBinding
    private val presenter: IMainPresenter by lazy {
        MainPresenter(viewer = WeakReference(this))
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.todo_list,
                container,
                false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adapter = ItemAdapter(presenter)
        binding.presenter = presenter
    }

    override fun onStart() {
        super.onStart()
        presenter.load()
    }

    override fun onStop() {
        presenter.cancelTask()
        super.onStop()
    }


    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setList(list: List<ToDoEntity>) {
        binding.adapter?.setItems(list)
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
