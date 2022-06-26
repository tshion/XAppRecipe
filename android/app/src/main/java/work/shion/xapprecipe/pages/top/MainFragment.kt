package work.shion.xapprecipe.pages.top

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.ACTION_SETTINGS
import android.view.View
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.github.tshion.xapprecipe.NavEntrypointDirections
import com.github.tshion.xapprecipe.NavTopDirections
import com.github.tshion.xapprecipe.R
import com.github.tshion.xapprecipe.databinding.PagesTopBinding
import work.shion.androidpreparation.intentbuilder.OpenBrowserIntentBuilder
import work.shion.androidpreparation.intentbuilder.OpenMailerIntentBuilder
import work.shion.xapprecipe.getProvider
import java.lang.ref.WeakReference

/**
 * トップ
 */
class MainFragment : Fragment(R.layout.pages_top), MainViewContract {

    companion object {
        private const val REQUEST_LOGOUT_CONFIRM = "REQUEST_LOGOUT_CONFIRM"
        private const val REQUEST_LOGOUT_FINISH = "REQUEST_LOGOUT_FINISH"
    }


    private var binding: PagesTopBinding? = null
    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(
            certifyAccountUseCase = activity?.getProvider()!!.certifyAccountUseCase,
            viewer = WeakReference(this),
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(REQUEST_LOGOUT_CONFIRM) { _, _ ->
            viewModel.doLogout()
        }
        setFragmentResultListener(REQUEST_LOGOUT_FINISH) { _, _ ->
            closeMenu()
            viewModel.loadMenu()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PagesTopBinding.bind(view)

        // フッター
        binding?.pagesTopFooter?.setOnNavigationItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.pages_top_footer_links -> NavTopDirections.navactTopToLinkIndex()
                R.id.pages_top_footer_map -> NavTopDirections.navactTopToMapWeb()
                else -> null
            }?.also { direction ->
                activity?.let { Navigation.findNavController(it, R.id.pages_top_entrypoint) }
                    ?.navigate(direction)
            }
            return@setOnNavigationItemSelectedListener true
        }

        // ドロワーメニュー
        binding?.pagesTopMenu?.tapListener = { viewModel.onTapMenu(it) }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadMenu()
    }

    override fun onStop() {
        viewModel.cancelTasks()
        super.onStop()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


    /**
     * メニューを閉じる
     */
    override fun closeMenu() {
        binding?.pagesTopDrawer?.closeDrawer(GravityCompat.START)
    }

    /**
     * ライセンス表記へ遷移
     */
    override fun goLicense() {
        activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
            ?.navigate(MainFragmentDirections.navactToLicense())
    }

    /**
     * ログインへ遷移
     */
    override fun goLogin() {
        activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
            ?.navigate(NavEntrypointDirections.navactToLogin(canClose = true))
    }

    /**
     * 外部ブラウザの起動
     */
    override fun launchBrowser(uri: String) {
        OpenBrowserIntentBuilder()
            .uriString(uri)
            .build()
            ?.also { startActivity(it) }
    }

    /**
     * メーラーの起動
     */
    override fun launchMailer() {
        OpenMailerIntentBuilder()
            .appendTo("sample@example.com")
            .subject("件名")
            .text("本文")
            .build()
            .also { startActivity(it) }
    }

    /**
     * OS 設定の起動
     */
    override fun launchOsSettings() {
        Intent().apply {
            action = ACTION_SETTINGS
        }.also { startActivity(it) }
    }

    /**
     * メニューを開く
     */
    override fun openMenu() {
        binding?.pagesTopDrawer?.openDrawer(GravityCompat.START)
    }

    /**
     * ローディング状態の反映
     */
    override fun reflectLoading(shouldShow: Boolean) {
        binding?.pagesTopLoading?.isVisible = shouldShow
    }

    /**
     * メニュー項目の反映
     */
    override fun reflectMenu(isLogin: Boolean) {
        binding?.pagesTopMenu?.setup(isLogin)
    }

    /**
     * ログアウト確認の表示
     */
    override fun showLogoutConfirm() {
        activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
            ?.navigate(NavEntrypointDirections.navactShowLogoutConfirmDialog(REQUEST_LOGOUT_CONFIRM))
    }

    /**
     * ログアウト完了の表示
     */
    override fun showLogoutFinish() {
        activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
            ?.navigate(NavEntrypointDirections.navactShowLogoutFinishDialog(REQUEST_LOGOUT_FINISH))
    }
}
