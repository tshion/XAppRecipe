package work.shion.ktrecipe.pages.entrypoint.presenters

import android.view.MenuItem
import work.shion.ktrecipe.R
import work.shion.ktrecipe.pages.entrypoint.contracts.TabPresenterContract
import work.shion.ktrecipe.pages.entrypoint.contracts.TabViewContract
import java.lang.ref.WeakReference


/**
 * タブの挙動実装
 */
class TabPresenter(
        private val viewer: WeakReference<TabViewContract>
) : TabPresenterContract {

    /**
     * NavigationItem 選択時
     */
    override fun onNavigationItemSelected(selected: MenuItem): Boolean {
        val viewer = viewer.get() ?: return false
        return when (selected.itemId) {
            R.id.entrypoint_menu_tab_1st -> {
                viewer.goTab1st()
                return true
            }
            R.id.entrypoint_menu_tab_2nd -> {
                viewer.goTab2nd()
                return true
            }
            R.id.entrypoint_menu_tab_web -> {
                viewer.goWebPage()
                return true
            }
            else -> false
        }
    }
}
