package work.shion.xapprecipe.pages.launcher

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import work.shion.xapprecipe.NavEntrypointDirections
import work.shion.xapprecipe.R
import work.shion.xapprecipe.atoms.CircleLoadingOverlay
import work.shion.xapprecipe.templates.LaunchErrorDialog
import java.lang.ref.WeakReference

/**
 * アプリ起動
 */
class MainFragment : Fragment(), MainViewContract {

    companion object {
        private const val REQUEST_LAUNCH_ERROR_DIALOG = 1000
    }


    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(
            viewer = WeakReference(this)
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = CircleLoadingOverlay(inflater.context).apply {
        layoutParams = LayoutParams(
            MATCH_PARENT,
            MATCH_PARENT,
        )
    }

    override fun onStart() {
        super.onStart()
        viewModel.judgePage()
    }

    override fun onStop() {
        viewModel.cancelTasks()
        super.onStop()
    }


    /**
     * 初回起動者向けフローへ遷移
     */
    override fun goBeginner() {
        activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
            ?.navigate(NavEntrypointDirections.navactToTutorial())
    }

    /**
     * 複数回起動者向けフローへ遷移
     */
    override fun goLoyalty() {
        activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
            ?.navigate(NavEntrypointDirections.navactToTop())
    }

    /**
     * ダイアログ選択結果の受け取り
     */
    override fun onDialogResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_LAUNCH_ERROR_DIALOG -> {
                if (resultCode == RESULT_OK) {
                    viewModel.judgePage()
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    /**
     * 起動失敗ダイアログの表示
     */
    override fun showLaunchErrorDialog() {
        LaunchErrorDialog.newInstance(
            REQUEST_LAUNCH_ERROR_DIALOG,
            this@MainFragment
        ).show(parentFragmentManager, LaunchErrorDialog.TAG)
    }
}
