package com.github.tshion.xapprecipe.webapp.organisms.menu_for_top

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView

internal class MenuForTopAdapter : RecyclerView.Adapter<MenuForTopItemViewHolder>() {

    var displayData: List<MenuForTopItemType>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener: TapListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuForTopItemViewHolder {
        return MenuForTopItem(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                MATCH_PARENT,
                WRAP_CONTENT,
            )
        }.let { MenuForTopItemViewHolder(it) }
    }

    override fun onBindViewHolder(holder: MenuForTopItemViewHolder, position: Int) {
        val data = displayData?.getOrNull(position) ?: return
        holder.root.apply {
            setOnClickListener { listener?.invoke(data) }
            setup(data)
        }
    }


    override fun getItemCount() = displayData?.count() ?: 0
}
