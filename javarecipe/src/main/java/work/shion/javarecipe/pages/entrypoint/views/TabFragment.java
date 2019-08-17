package work.shion.javarecipe.pages.entrypoint.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.entrypoint_fragment_tab, container, false);
    }

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
