package work.shion.ktrecipe.pages.entrypoint.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.entrypoint_fragment_tutorial.*
import work.shion.ktrecipe.R
import work.shion.ktrecipe.pages.entrypoint.contracts.TutorialPresenterContract
import work.shion.ktrecipe.pages.entrypoint.contracts.TutorialViewContract
import work.shion.ktrecipe.pages.entrypoint.presenters.TutorialPresenter
import java.lang.ref.WeakReference


/**
 * チュートリアル表示用Fragment
 */
class TutorialFragment : Fragment(),
        TutorialViewContract {

    private val presenter: TutorialPresenterContract by lazy {
        TutorialPresenter(
                viewer = WeakReference(this)
        )
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.entrypoint_fragment_tutorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        entrypoint_fragment_tutorial_button.setOnClickListener {
            presenter.callTabPage()
        }
    }


    /**
     * Tab ページに遷移
     */
    override fun goTabPage() {
        val target = activity ?: return
        Navigation.findNavController(
                target,
                R.id.entrypoint_navigation_host_main
        ).navigate(R.id.entrypoint_action_any_to_tab)
    }
}
