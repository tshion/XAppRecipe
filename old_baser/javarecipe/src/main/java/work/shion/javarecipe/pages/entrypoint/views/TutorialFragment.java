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


    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.entrypoint_fragment_tutorial, container, false);
    }

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
