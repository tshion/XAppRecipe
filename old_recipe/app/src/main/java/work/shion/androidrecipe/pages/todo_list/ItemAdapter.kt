package work.shion.androidrecipe.pages.todo_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import work.shion.androidrecipe.R
import work.shion.androidrecipe.databinding.TodoListItemBinding
import work.shion.androidrecipe.entities.ToDoEntity

class ItemAdapter(
        private val presenter: IMainPresenter
) : RecyclerView.Adapter<ItemViewHolder>() {

    private var list: List<ToDoEntity>? = null


    override fun getItemCount() = list?.count() ?: 0

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.data = list?.getOrNull(position)
        holder.binding.presenter = presenter
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataBindingUtil.inflate<TodoListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.todo_list_item,
            parent,
            false
    ).let {
        ItemViewHolder(it)
    }


    fun setItems(list: List<ToDoEntity>) {
        this.list = list
        notifyDataSetChanged()
    }
}
