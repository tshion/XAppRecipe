package work.shion.javarecipe;

import android.app.Application;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import timber.log.Timber;
import work.shion.baser.timber.ITimberJavaAttacher;


/**
 * アプリ状態の管理
 */
public class MainApplication extends Application
        implements ITimberJavaAttacher {

    @Override
    public void onCreate() {
        super.onCreate();

        setupTimber(BuildConfig.DEBUG);
    }


    /**
     * 本番用のTimber 設定の取得
     */
    @NotNull
    @Override
    public Timber.Tree getProductTree() {
        return new Timber.Tree() {
            @Override
            protected void log(
                    int priority,
                    @Nullable String tag,
                    @NotNull String message,
                    @Nullable Throwable t
            ) {
            }
        };
    }
}
