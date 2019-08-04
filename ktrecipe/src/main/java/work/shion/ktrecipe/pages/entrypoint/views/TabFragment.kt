package work.shion.ktrecipe.pages.entrypoint.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.entrypoint_fragment_tab.*
import work.shion.ktrecipe.R


/**
 * タブ表示用Fragment
 */
class TabFragment : Fragment() {

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
        return inflater.inflate(R.layout.entrypoint_fragment_tab, container, false)
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

        // BottomNavigation の設定
        entrypoint_fragment_tab_bottom_navigation.setOnNavigationItemSelectedListener { item ->
            val hostId = R.id.entrypoint_fragment_tab_navigation_host
            val target = activity ?: return@setOnNavigationItemSelectedListener false

            when (item.itemId) {
                R.id.entrypoint_menu_tab_1st -> {
                    Navigation.findNavController(target, hostId)
                            .navigate(R.id.entrypoint_action_any_to_tab_1st)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.entrypoint_menu_tab_2nd -> {
                    Navigation.findNavController(target, hostId)
                            .navigate(R.id.entrypoint_action_any_to_tab_2nd)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}
