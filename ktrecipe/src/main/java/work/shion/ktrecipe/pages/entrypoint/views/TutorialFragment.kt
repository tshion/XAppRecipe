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


    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * [.onCreate] and [.onActivityCreated].
     *
     *
     * If you return a View from here, you will later be called in
     * [.onDestroyView] when the view is being released.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Return the View for the fragment's UI, or null.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.entrypoint_fragment_tutorial, container, false)
    }

    /**
     * Called immediately after [.onCreateView]
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     * @param view The View returned by [.onCreateView].
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
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
