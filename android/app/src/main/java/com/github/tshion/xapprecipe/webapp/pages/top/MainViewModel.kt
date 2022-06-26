package com.github.tshion.xapprecipe.webapp.pages.top

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import com.github.tshion.xapprecipe.webapp.BuildConfig
import com.github.tshion.xapprecipe.webapp.organisms.menu_for_top.MenuForTopItemType
import com.github.tshion.xapprecipe.webapp.organisms.menu_for_top.MenuForTopItemType.*
import com.github.tshion.xapprecipe.webapp_core.usecases.CertifyAccountUseCaseContract
import java.lang.ref.WeakReference

class MainViewModel(
    private val certifyAccountUseCase: CertifyAccountUseCaseContract,
    private val viewer: WeakReference<MainViewContract>,
) : ViewModel(), MainActionContract {

    /** タスクキャンセル */
    override fun cancelTasks() = viewModelScope.coroutineContext.cancelChildren()

    /**
     * ログアウトの実行
     */
    override fun doLogout() {
        viewModelScope.launch {
            try {
                viewer.get()?.reflectLoading(true)
                certifyAccountUseCase.logout()
                viewer.get()?.showLogoutFinish()
            } catch (ex: Throwable) {
                // TODO: エラー表示
            } finally {
                viewer.get()?.reflectLoading(false)
            }
        }
    }

    /**
     * メニュー項目の読み込み
     */
    override fun loadMenu() {
        viewModelScope.launch {
            try {
                viewer.get()?.reflectLoading(true)
                certifyAccountUseCase.isAuthenticated()
                    .also { viewer.get()?.reflectMenu(it) }
            } catch (ex: Throwable) {
                // TODO: エラー表示
            } finally {
                viewer.get()?.reflectLoading(false)
            }
        }
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
