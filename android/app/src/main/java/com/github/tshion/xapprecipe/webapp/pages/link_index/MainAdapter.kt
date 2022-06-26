package com.github.tshion.xapprecipe.webapp.pages.link_index

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.github.tshion.xapprecipe.webapp.R
import com.github.tshion.xapprecipe.webapp.molecules.link_headline.LinkHeadline
import com.github.tshion.xapprecipe.webapp.molecules.link_headline.LinkHeadlineViewHolder
import com.github.tshion.xapprecipe.webapp_core.entities.WebLinkEntity

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


    init {
        differ.addListListener { _, currentList ->
            action.changeListShowState(!currentList.isNullOrEmpty())
        }
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
            holder.root.apply {
                setOnClickListener(null)
                setup(
                    description = null,
                    imagePath = null,
                    title = null,
                )
            }
            return
        }

        holder.root.apply {
            setOnClickListener { action.callDetail(data) }
            setup(
                description = data.description ?: "(取得失敗)",
                imagePath = data.imagePath,
                title = data.title ?: "(取得失敗)",
            )
        }
    }


    override fun getItemCount() = differ.currentList.size
}
