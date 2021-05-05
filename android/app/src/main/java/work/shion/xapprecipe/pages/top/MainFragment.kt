package work.shion.xapprecipe.pages.top

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.ACTION_SETTINGS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            viewer = WeakReference(this)
        )
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PagesTopBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // フッター
        binding?.pagesTopFooter?.setOnNavigationItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.pages_top_footer_home -> NavTopDirections.navactTopToHome()
                R.id.pages_top_footer_2,
                R.id.pages_top_footer_3,
                R.id.pages_top_footer_4,
                R.id.pages_top_footer_5 -> NavTopDirections.navactTopToSample()
                else -> null
            }?.also { direction ->
                activity?.let { Navigation.findNavController(it, R.id.pages_top_entrypoint) }
                    ?.navigate(direction)
            }
            return@setOnNavigationItemSelectedListener true
        }

        // ドロワーメニュー
        binding?.pagesTopMenu?.setup(true)
        binding?.pagesTopMenu?.tapListener = { viewModel.onTapMenu(it) }

        // 値の監視
        logoutConfirmDialogViewModel.isCalledDismiss.observe(viewLifecycleOwner) {
            if (it) {
                logoutConfirmDialogViewModel.isCalledDismiss.value = false
                showLogoutFinish()
            }
        }
        logoutFinishDialogViewModel.isCalledDismiss.observe(viewLifecycleOwner) {
            if (it) {
                logoutFinishDialogViewModel.isCalledDismiss.value = false
                goTop()
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
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
        Toast.makeText(requireContext(), "ログイン", Toast.LENGTH_SHORT).show()
    }

    /**
     * トップへ遷移
     */
    override fun goTop() {
        activity?.let { Navigation.findNavController(it, R.id.pages_top_entrypoint) }
            ?.navigate(NavEntrypointDirections.navactToTop())
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
