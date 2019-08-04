package work.shion.ktrecipe.pages.entrypoint.contracts

import android.view.MenuItem


/**
 * タブの挙動定義
 */
interface TabPresenterContract {

    /**
     * NavigationItem 選択時
     */
    fun onNavigationItemSelected(selected: MenuItem): Boolean
}
