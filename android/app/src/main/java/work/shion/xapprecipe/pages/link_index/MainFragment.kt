package work.shion.xapprecipe.pages.link_index

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import work.shion.xapprecipe.NavEntrypointDirections
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.PagesLinkIndexBinding
import work.shion.xapprecipe.getProvider
import work.shion.xapprecipe.templates.link_detail_dialog.LinkDetailDialogViewModel
import work.shion.xapprecipe.templates.link_insert_dialog.LinkInsertDialogViewModel
import work.shion.xapprecipe_core.entities.WebLinkEntity
import java.lang.ref.WeakReference
import work.shion.xapprecipe.pages.top.MainFragment as TopFragment

/**
 * リンク一覧
 */
class MainFragment : Fragment(), MainViewContract {

    private var adapter: MainAdapter? = null
    private var binding: PagesLinkIndexBinding? = null
    private val linkDetailViewModel by activityViewModels<LinkDetailDialogViewModel>()
    private val linkInsertViewModel by activityViewModels<LinkInsertDialogViewModel>()
    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(
            bookmarkWebUseCase = activity?.getProvider()!!.bookmarkUseCase,
            viewer = WeakReference(this),
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PagesLinkIndexBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.pagesLinkIndexAdd?.setOnClickListener { showEditor() }

        binding?.pagesLinkIndexHeader?.apply {
            setupTapMenuListener { showMenu() }
            setupTapNewsListener { goNews() }
        }

        adapter = MainAdapter(
            action = viewModel,
            diffCallback = MainAdapterDiffs(),
        )
        binding?.pagesLinkIndexList?.adapter = adapter


        linkDetailViewModel.isCalledDelete.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.remove()
                linkDetailViewModel.isCalledDelete.value = false
            }
        }

        linkInsertViewModel.input.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank()) {
                viewModel.register(it)
                linkInsertViewModel.input.value = null
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.load()
    }

    override fun onStop() {
        viewModel.cancelTasks()
        super.onStop()
    }

    override fun onDestroyView() {
        adapter = null
        binding = null
        super.onDestroyView()
    }


    /**
     * 通知一覧へ遷移
     */
    override fun goNews() {
        activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
            ?.navigate(NavEntrypointDirections.navactToNotice())
    }

    /**
     * リスト表示用データの反映
     */
    override fun reflectList(data: List<WebLinkEntity>?) {
        adapter?.displayData = data
        binding?.pagesLinkIndexList?.isVisible = adapter?.displayData.isNullOrEmpty().not()
    }

    /**
     * ローディング表示状態の反映
     */
    override fun reflectLoading(shouldShow: Boolean) {
        binding?.pagesLinkIndexLoading?.isVisible = shouldShow
    }

    /**
     * 登録ダイアログの表示
     */
    override fun showEditor() {
        activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
            ?.navigate(NavEntrypointDirections.navactShowLinkInsertDialog())
    }

    /**
     * リンク詳細の表示
     */
    override fun showLinkDetail(data: WebLinkEntity) {
        activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
            ?.navigate(NavEntrypointDirections.navactShowLinkDetailDialog(data.webPath))
    }

    /**
     * メニューの表示
     */
    override fun showMenu() {
        (parentFragment as? TopFragment?)?.openMenu()
    }
}
