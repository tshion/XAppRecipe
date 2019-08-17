package work.shion.ktrecipe.pages.entrypoint.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.entrypoint_fragment_tab.*
import work.shion.ktrecipe.R
import work.shion.ktrecipe.pages.entrypoint.contracts.TabPresenterContract
import work.shion.ktrecipe.pages.entrypoint.contracts.TabViewContract
import work.shion.ktrecipe.pages.entrypoint.presenters.TabPresenter
import work.shion.ktrecipe.pages.websample.views.MainActivity
import java.lang.ref.WeakReference


/**
 * タブ表示用Fragment
 */
class TabFragment : Fragment(),
        TabViewContract {

    private val presenter: TabPresenterContract by lazy {
        TabPresenter(
                viewer = WeakReference(this)
        )
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.entrypoint_fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // BottomNavigation の設定
        entrypoint_fragment_tab_bottom_navigation.setOnNavigationItemSelectedListener { item ->
            presenter.onNavigationItemSelected(item)
        }
    }


    /**
     * ギャラリーに遷移
     */
    override fun goGallery() {
        val target = activity ?: return
        Navigation.findNavController(
                target,
                R.id.entrypoint_fragment_tab_navigation_host
        ).navigate(R.id.entrypoint_action_any_to_tab_gallery)
    }

    /**
     * Tab1st ページに遷移
     */
    override fun goTab1st() {
        val target = activity ?: return
        Navigation.findNavController(
                target,
                R.id.entrypoint_fragment_tab_navigation_host
        ).navigate(R.id.entrypoint_action_any_to_tab_1st)
    }

    /**
     * Tab2nd ページに遷移
     */
    override fun goTab2nd() {
        val target = activity ?: return
        Navigation.findNavController(
                target,
                R.id.entrypoint_fragment_tab_navigation_host
        ).navigate(R.id.entrypoint_action_any_to_tab_2nd)
    }

    /**
     * Web ページに遷移
     */
    override fun goWebPage() {
        val target = activity ?: return
        MainActivity.start(target)
    }
}
