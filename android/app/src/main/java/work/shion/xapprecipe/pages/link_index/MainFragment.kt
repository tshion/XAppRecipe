package work.shion.xapprecipe.pages.link_index

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import work.shion.xapprecipe.NavEntrypointDirections
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.PagesLinkIndexBinding
import work.shion.xapprecipe.getProvider
import work.shion.xapprecipe.templates.LinkInsertDialog
import work.shion.xapprecipe_core.entities.WebLinkEntity
import java.lang.ref.WeakReference
import work.shion.xapprecipe.pages.top.MainFragment as TopFragment

/**
 * リンク一覧
 */
class MainFragment : Fragment(), MainViewContract {

    companion object {
        private val REQUEST_LINK_DETAIL =
            "${MainFragment::class.java.simpleName}.REQUEST_LINK_DETAIL"
        private val REQUEST_LINK_INSERT =
            "${MainFragment::class.java.simpleName}.REQUEST_LINK_INSERT"
    }


    private var adapter: MainAdapter? = null
    private var binding: PagesLinkIndexBinding? = null
    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(
            bookmarkWebUseCase = activity?.getProvider()!!.bookmarkUseCase,
            viewer = WeakReference(this),
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(REQUEST_LINK_DETAIL) { _, _ ->
            viewModel.remove()
        }
        setFragmentResultListener(REQUEST_LINK_INSERT) { _, result ->
            val input = LinkInsertDialog.pickInput(result)
            if (!input.isNullOrBlank()) {
                viewModel.register(input)
            }
        }
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
    }

    /**
     * リスト表示状態の反映
     */
    override fun reflectListShowState(shouldShow: Boolean) {
        binding?.pagesLinkIndexList?.isVisible = shouldShow
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
        findNavController()
            .navigate(MainFragmentDirections.navactShowLinkInsertDialog(REQUEST_LINK_INSERT))
    }

    /**
     * リンク詳細の表示
     */
    override fun showLinkDetail(data: WebLinkEntity) {
        findNavController().navigate(
            MainFragmentDirections.navactShowLinkDetailDialog(
                requestKey = REQUEST_LINK_DETAIL,
                uri = data.webPath
            )
        )
    }

    /**
     * メニューの表示
     */
    override fun showMenu() {
        (parentFragment as? NavHostFragment)
            ?.let { it.parentFragment as? TopFragment }
            ?.openMenu()
    }
}
