package work.shion.xapprecipe.pages.top

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.ACTION_SETTINGS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import work.shion.androidpreparation.intentbuilder.OpenBrowserIntentBuilder
import work.shion.androidpreparation.intentbuilder.OpenMailerIntentBuilder
import work.shion.xapprecipe.NavEntrypointDirections
import work.shion.xapprecipe.NavTopDirections
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.PagesTopBinding
import work.shion.xapprecipe.getProvider
import work.shion.xapprecipe.templates.logout_confirm_dialog.LogoutConfirmDialogViewModel
import work.shion.xapprecipe.templates.logout_finish_dialog.LogoutFinishDialogViewModel
import java.lang.ref.WeakReference

/**
 * トップ
 */
class MainFragment : Fragment(), MainViewContract {

    private var binding: PagesTopBinding? = null
    private val logoutConfirmDialogViewModel by activityViewModels<LogoutConfirmDialogViewModel>()
    private val logoutFinishDialogViewModel by activityViewModels<LogoutFinishDialogViewModel>()
    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(
            certifyAccountUseCase = activity?.getProvider()!!.certifyAccountUseCase,
            viewer = WeakReference(this),
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PagesTopBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        // 値の監視
        logoutConfirmDialogViewModel.isCalledDismiss.observe(viewLifecycleOwner) {
            if (it) {
                logoutConfirmDialogViewModel.isCalledDismiss.value = false
                viewModel.doLogout()
            }
        }
        logoutFinishDialogViewModel.isCalledDismiss.observe(viewLifecycleOwner) {
            if (it) {
                logoutFinishDialogViewModel.isCalledDismiss.value = false
                closeMenu()
                viewModel.loadMenu()
            }
        }
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
            ?.navigate(NavEntrypointDirections.navactShowLogoutConfirmDialog())
    }

    /**
     * ログアウト完了の表示
     */
    override fun showLogoutFinish() {
        activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
            ?.navigate(NavEntrypointDirections.navactShowLogoutFinishDialog())
    }
}
