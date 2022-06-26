package work.shion.xapprecipe.pages.media_list

import androidx.recyclerview.widget.DiffUtil

class MainAdapterDiffs : DiffUtil.ItemCallback<MediaViewData>() {

    override fun areContentsTheSame(oldItem: MediaViewData, newItem: MediaViewData): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: MediaViewData, newItem: MediaViewData): Boolean {
        return oldItem.id == newItem.id
    }
}
