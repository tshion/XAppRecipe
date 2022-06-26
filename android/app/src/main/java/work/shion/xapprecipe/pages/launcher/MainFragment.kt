package work.shion.xapprecipe.pages.launcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.github.tshion.xapprecipe.NavEntrypointDirections
import com.github.tshion.xapprecipe.R
import work.shion.xapprecipe.atoms.CircleLoadingOverlay
import java.lang.ref.WeakReference

/**
 * アプリ起動
 */
class MainFragment : Fragment(), MainViewContract {

    companion object {
        private const val REQUEST_RETRY = "REQUEST_RETRY"
    }


    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(
            viewer = WeakReference(this)
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(REQUEST_RETRY) { _, _ ->
            viewModel.judgePage()
        }
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
     * 起動失敗ダイアログの表示
     */
    override fun showLaunchErrorDialog() {
        activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
            ?.navigate(NavEntrypointDirections.navactShowLaunchErrorDialog(REQUEST_RETRY))
    }
}
