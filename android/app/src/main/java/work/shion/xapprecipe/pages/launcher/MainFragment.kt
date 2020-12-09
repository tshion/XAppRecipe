package work.shion.xapprecipe.pages.launcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import work.shion.xapprecipe.NavEntrypointDirections
import work.shion.xapprecipe.R
import work.shion.xapprecipe.atoms.CircleLoadingOverlay
import work.shion.xapprecipe.templates.launch_error_dialog.LaunchErrorDialogViewModel
import java.lang.ref.WeakReference

/**
 * アプリ起動
 */
class MainFragment : Fragment(), MainViewContract {

    private val launchErrorDialogViewModel by activityViewModels<LaunchErrorDialogViewModel>()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchErrorDialogViewModel.isCalledRetry.observe(viewLifecycleOwner) {
            if (it) {
                launchErrorDialogViewModel.isCalledRetry.value = false
                viewModel.judgePage()
            }
        }
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
     * 起動失敗ダイアログの表示
     */
    override fun showLaunchErrorDialog() {
        activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
            ?.navigate(NavEntrypointDirections.navactShowLaunchErrorDialog())
    }
}
