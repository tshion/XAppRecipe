package work.shion.javarecipe.pages.entrypoint.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.lang.ref.WeakReference;

import work.shion.javarecipe.R;
import work.shion.javarecipe.pages.entrypoint.contracts.TutorialPresenterContract;
import work.shion.javarecipe.pages.entrypoint.contracts.TutorialViewContract;
import work.shion.javarecipe.pages.entrypoint.presenters.TutorialPresenter;


/**
 * チュートリアル表示用Fragment
 */
public class TutorialFragment extends Fragment
        implements TutorialViewContract {

    private final TutorialPresenterContract presenter = new TutorialPresenter(
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
        return inflater.inflate(R.layout.entrypoint_fragment_tutorial, container, false);
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

        View button = view.findViewById(R.id.entrypoint_fragment_tutorial_button);
        button.setOnClickListener(v -> {
            presenter.callTabPage();
        });
    }


    /**
     * Tab ページに遷移
     */
    @Override
    public void goTabPage() {
        if (getActivity() == null) {
            return;
        }

        Navigation.findNavController(
                getActivity(),
                R.id.entrypoint_navigation_host_main
        ).navigate(R.id.entrypoint_action_any_to_tab);
    }
}
