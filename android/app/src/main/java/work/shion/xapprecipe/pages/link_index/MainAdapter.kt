package work.shion.xapprecipe.pages.link_index

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import work.shion.xapprecipe.R
import work.shion.xapprecipe.molecules.link_headline.LinkHeadline
import work.shion.xapprecipe.molecules.link_headline.LinkHeadlineViewHolder
import work.shion.xapprecipe_core.entities.WebLinkEntity

class MainAdapter(
    private val action: MainItemActionContract,
    diffCallback: MainAdapterDiffs,
) : RecyclerView.Adapter<LinkHeadlineViewHolder>() {

    private val differ = AsyncListDiffer(this, diffCallback)
    var displayData: List<WebLinkEntity>?
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkHeadlineViewHolder {
        val context = parent.context
        val space1 = context.resources.getDimensionPixelSize(R.dimen.app_space_1)
        val space2 = context.resources.getDimensionPixelSize(R.dimen.app_space_2)

        return LinkHeadline(context).apply {
            layoutParams = ViewGroup.MarginLayoutParams(
                MATCH_PARENT,
                WRAP_CONTENT
            ).apply {
                setMargins(
                    space2,
                    space1,
                    space2,
                    space1,
                )
            }
        }.let { LinkHeadlineViewHolder(it) }
    }

    override fun onBindViewHolder(holder: LinkHeadlineViewHolder, position: Int) {
        val data = differ.currentList.getOrNull(position)
        if (data == null) {
            holder.root.setOnClickListener(null)
            return
        }

        holder.root.apply {
            setOnClickListener { action.callDetail(data) }
            setup(
                description = data.description,
                imagePath = data.imagePath,
                title = data.title,
            )
        }
    }


    override fun getItemCount() = differ.currentList.size
}
