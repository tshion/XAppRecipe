package work.shion.javarecipe.pages.entrypoint.views;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import java.util.Date;
import java.util.Random;

import work.shion.javarecipe.R;


/**
 * メインコンテンツ表示用Activity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrypoint_activity_main);

        // 初期表示をランダムで変更する
        final @IdRes int targetNavigationId = new Random(new Date().getTime()).nextInt(100) < 50
                ? R.id.entrypoint_action_any_to_tutorial
                : R.id.entrypoint_action_any_to_tab;
        Navigation.findNavController(
                this,
                R.id.entrypoint_navigation_host_main
        ).navigate(targetNavigationId);
    }
}
