package work.shion.ktrecipe.pages.entrypoint.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import work.shion.ktrecipe.MainApplication
import work.shion.ktrecipe.R
import work.shion.ktrecipe.databinding.EntrypointFragmentGalleryBinding
import work.shion.ktrecipe.pages.entrypoint.GalleryIndexItemEntity
import work.shion.ktrecipe.pages.entrypoint.contracts.GalleryViewContract
import work.shion.ktrecipe.pages.entrypoint.presenters.GalleryPresenter
import work.shion.ktrecipe.pages.entrypoint.viewmodels.GalleryViewModel
import work.shion.ktrecipe.pages.image_detail.views.MainActivity
import java.lang.ref.WeakReference

/**
 * 画像一覧の表示用Fragment
 */
class GalleryFragment : Fragment(),
        GalleryViewContract {

    private lateinit var binding: EntrypointFragmentGalleryBinding
    private val presenter by lazy {
        GalleryPresenter(
                api = (activity?.application as? MainApplication)?.apiJewelSavior!!,
                viewer = WeakReference(this)
        )
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.entrypoint_fragment_gallery,
                container,
                false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.adapter = GalleryIndexItemAdapter(
                presenter = this@GalleryFragment.presenter
        )
        binding.viewModel = ViewModelProviders.of(this)
                .get(GalleryViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        presenter.refreshAdapterData()
    }

    override fun onStop() {
        presenter.killTask()
        super.onStop()
    }


    /**
     * 画像詳細画面に遷移
     * @param 詳細画像のURL
     */
    override fun goDetail(url: String?) {
        val target = activity ?: return
        MainActivity.start(target, url)
    }

    override fun hideProgress() {
        binding.viewModel?.isShowProgress?.set(false)
    }

    override fun replaceAdapterData(data: List<GalleryIndexItemEntity>) {
        binding.adapter?.setDisplayData(data)
        binding.viewModel?.hasResult?.set(data.isNotEmpty())
    }

    override fun showProgress() {
        binding.viewModel?.isShowProgress?.set(true)
    }
}
