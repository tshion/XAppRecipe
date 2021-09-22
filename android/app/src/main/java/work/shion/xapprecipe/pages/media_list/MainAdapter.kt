package work.shion.xapprecipe.pages.media_list

import android.content.Context
import android.util.Size
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import work.shion.xapprecipe.atoms.media_tile.MediaTile
import work.shion.xapprecipe.atoms.media_tile.MediaTileViewHolder

class MainAdapter(
    diffCallback: MainAdapterDiffs,
) : RecyclerView.Adapter<MediaTileViewHolder>() {

    private lateinit var context: Context
    private val differ = AsyncListDiffer(this, diffCallback)
    var displayData: List<MediaViewData>?
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaTileViewHolder {
        context = parent.context
        return MediaTile(context).apply {
            layoutParams = LayoutParams(
                MATCH_PARENT,
                WRAP_CONTENT,
            )
        }.let { MediaTileViewHolder(it) }
    }

    override fun onBindViewHolder(holder: MediaTileViewHolder, position: Int) {
        val data = differ.currentList.getOrNull(position) ?: return
        holder.root.apply {
            preview = data.thumbnail(
                appContext = context,
                size = Size(320, 160),
            )
            title = data.title
        }
    }


    override fun getItemCount() = differ.currentList.size
}
