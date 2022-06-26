package work.shion.xapprecipe.pages.link_index

import androidx.recyclerview.widget.DiffUtil
import work.shion.xapprecipe_core.entities.WebLinkEntity

class MainAdapterDiffs : DiffUtil.ItemCallback<WebLinkEntity>() {

    override fun areContentsTheSame(oldItem: WebLinkEntity, newItem: WebLinkEntity): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: WebLinkEntity, newItem: WebLinkEntity): Boolean {
        return oldItem.id == newItem.id
    }
}
