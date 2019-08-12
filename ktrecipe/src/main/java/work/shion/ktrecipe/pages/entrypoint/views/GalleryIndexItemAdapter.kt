package work.shion.ktrecipe.pages.entrypoint.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import work.shion.ktrecipe.R
import work.shion.ktrecipe.databinding.EntrypointItemGalleryIndexBinding
import work.shion.ktrecipe.pages.entrypoint.contracts.GalleryIndexItemPresenterContract
import work.shion.ktrecipe.pages.entrypoint.viewmodels.GalleryIndexItemViewModel

/**
 * 画像一覧項目のリスト表示管理
 */
class GalleryIndexItemAdapter(
        private val presenter: GalleryIndexItemPresenterContract
) : RecyclerView.Adapter<GalleryIndexItemViewHolder>() {
    /** 表示データ */
    private var displayData: List<GalleryIndexItemViewModel>? = null


    /**
     * 表示データ数
     */
    override fun getItemCount(): Int = displayData?.size ?: 0

    /**
     * 表示データの紐付け
     */
    override fun onBindViewHolder(holder: GalleryIndexItemViewHolder, position: Int) {
        val target = displayData?.getOrNull(position) ?: return
        holder.binding.apply {
            presenter = this@GalleryIndexItemAdapter.presenter
            viewmodel = target
            notifyChange()
            executePendingBindings()
        }
    }

    /**
     * 表示領域の作成
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryIndexItemViewHolder {
        val binding = DataBindingUtil.inflate<EntrypointItemGalleryIndexBinding>(
                LayoutInflater.from(parent.context),
                R.layout.entrypoint_item_gallery_index,
                parent,
                false
        )
        return GalleryIndexItemViewHolder(binding)
    }


    /**
     * 表示データの設定
     */
    fun setDisplayData(data: List<GalleryIndexItemViewModel>) {
        displayData = data
        notifyDataSetChanged()
    }
}
