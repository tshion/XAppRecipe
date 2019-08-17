package work.shion.javarecipe.pages.entrypoint.views;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.Random;

import work.shion.javarecipe.R;
import work.shion.javarecipe.pages.entrypoint.contracts.MainPresenterContract;
import work.shion.javarecipe.pages.entrypoint.contracts.MainViewContract;
import work.shion.javarecipe.pages.entrypoint.models.MainModel;
import work.shion.javarecipe.pages.entrypoint.presenters.MainPresenter;


/**
 * メインコンテンツ表示用Activity
 */
public class MainActivity extends AppCompatActivity
        implements MainViewContract {

    private final MainPresenterContract presenter = new MainPresenter(
            new MainModel(),
            new WeakReference<>(this)
    );


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrypoint_activity_main);

        // 初期表示をランダムで変更する
        presenter.callFirstView();
    }


    /**
     * Tab ページに遷移
     */
    @Override
    public void goTabPage() {
        Navigation.findNavController(
                this,
                R.id.entrypoint_navigation_host_main
        ).navigate(R.id.entrypoint_action_any_to_tab);
    }

    /**
     * Tutorial ページに遷移
     */
    @Override
    public void goTutorialPage() {
        Navigation.findNavController(
                this,
                R.id.entrypoint_navigation_host_main
        ).navigate(R.id.entrypoint_action_any_to_tutorial);
    }
}
