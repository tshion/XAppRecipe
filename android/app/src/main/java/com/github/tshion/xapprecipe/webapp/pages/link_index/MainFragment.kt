package com.github.tshion.xapprecipe.webapp.pages.link_index

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.github.tshion.xapprecipe.webapp.NavEntrypointDirections
import com.github.tshion.xapprecipe.webapp.R
import com.github.tshion.xapprecipe.webapp.databinding.PagesLinkIndexBinding
import com.github.tshion.xapprecipe.webapp.getProvider
import com.github.tshion.xapprecipe.webapp.templates.LinkInsertDialog
import com.github.tshion.xapprecipe.webapp_core.entities.WebLinkEntity
import java.lang.ref.WeakReference
import com.github.tshion.xapprecipe.webapp.pages.top.MainFragment as TopFragment

/**
 * リンク一覧
 */
class MainFragment : Fragment(R.layout.pages_link_index), MainViewContract {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PagesLinkIndexBinding.bind(view)

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
