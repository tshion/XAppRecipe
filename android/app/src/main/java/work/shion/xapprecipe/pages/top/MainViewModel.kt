package work.shion.xapprecipe.pages.top

import androidx.lifecycle.ViewModel
import work.shion.xapprecipe.BuildConfig
import work.shion.xapprecipe.organisms.menu_for_top.MenuForTopItemType
import work.shion.xapprecipe.organisms.menu_for_top.MenuForTopItemType.*
import java.lang.ref.WeakReference

class MainViewModel(
    private val viewer: WeakReference<MainViewContract>,
) : ViewModel(), MainActionContract {

    /**
     * ログアウトの実行
     */
    override fun doLogout() {
        // TODO: 認証状態の変更
        viewer.get()?.showLogoutFinish()
    }

    /**
     * メニュータップ時
     */
    override fun onTapMenu(type: MenuForTopItemType) {
        viewer.get()?.run {
            when (type) {
                CONTACT -> launchMailer()
                LICENSE -> goLicense()
                LOGIN -> goLogin()
                LOGOUT -> showLogoutConfirm()
                SETTINGS -> launchOsSettings()
                TWITTER -> launchBrowser(BuildConfig.URL_Twitter)
                YOUTUBE -> launchBrowser(BuildConfig.URL_YouTube)
            }
        }
    }
}
