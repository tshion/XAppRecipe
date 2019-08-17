package work.shion.javarecipe.pages.entrypoint.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.annimon.stream.Optional;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.ref.WeakReference;

import work.shion.javarecipe.R;
import work.shion.javarecipe.pages.entrypoint.contracts.TabPresenterContract;
import work.shion.javarecipe.pages.entrypoint.contracts.TabViewContract;
import work.shion.javarecipe.pages.entrypoint.presenters.TabPresenter;
import work.shion.javarecipe.pages.websample.views.MainActivity;


/**
 * タブ表示用Fragment
 */
public class TabFragment extends Fragment
        implements TabViewContract {

    private final TabPresenterContract presenter = new TabPresenter(
            new WeakReference<>(this)
    );


    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     *
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view
     *                           itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.entrypoint_fragment_tab, container, false);
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater,
     *                           ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // BottomNavigation の設定
        BottomNavigationView bottomNavigation = view.findViewById(
                R.id.entrypoint_fragment_tab_bottom_navigation
        );
        bottomNavigation.setOnNavigationItemSelectedListener(presenter::onNavigationItemSelected);
    }


    /**
     * Tab1st ページに遷移
     */
    @Override
    public void goTab1st() {
        FragmentActivity target = getActivity();
        if (target == null) {
            return;
        }

        Navigation.findNavController(
                target,
                R.id.entrypoint_fragment_tab_navigation_host
        ).navigate(R.id.entrypoint_action_any_to_tab_1st);
    }

    /**
     * Tab2nd ページに遷移
     */
    @Override
    public void goTab2nd() {
        FragmentActivity target = getActivity();
        if (target == null) {
            return;
        }

        Navigation.findNavController(
                target,
                R.id.entrypoint_fragment_tab_navigation_host
        ).navigate(R.id.entrypoint_action_any_to_tab_2nd);
    }

    /**
     * Web ページに遷移
     */
    @Override
    public void goWebPage() {
        Optional.ofNullable(getActivity())
                .ifPresent(MainActivity::start);
    }
}
