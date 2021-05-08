package work.shion.xapprecipe.pages.link_index

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import work.shion.xapprecipe.NavEntrypointDirections
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.PagesLinkIndexBinding
import work.shion.xapprecipe_core.entities.WebLinkEntity
import work.shion.xapprecipe.pages.top.MainFragment as TopFragment

/**
 * リンク一覧
 */
class MainFragment : Fragment(), MainViewContract {

    private var adapter: MainAdapter? = MainAdapter(MainAdapterDiffs())
    private var binding: PagesLinkIndexBinding? = null


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

        binding?.pagesLinkIndexList?.adapter = adapter
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
        // TODO: 登録ダイアログの表示
    }

    /**
     * メニューの表示
     */
    override fun showMenu() {
        (parentFragment as? TopFragment?)?.openMenu()
    }
}
