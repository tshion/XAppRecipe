package com.github.tshion.xapprecipe.webapp.pages.link_index

import androidx.recyclerview.widget.DiffUtil
import com.github.tshion.xapprecipe.webapp_core.entities.WebLinkEntity

class MainAdapterDiffs : DiffUtil.ItemCallback<WebLinkEntity>() {

    override fun areContentsTheSame(oldItem: WebLinkEntity, newItem: WebLinkEntity): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: WebLinkEntity, newItem: WebLinkEntity): Boolean {
        return oldItem.id == newItem.id
    }
}
