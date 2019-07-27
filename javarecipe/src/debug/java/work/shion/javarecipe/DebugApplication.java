package work.shion.javarecipe;

import android.content.Context;
import androidx.annotation.NonNull;
import work.shion.baser.stetho.IStethoAttacher;


/**
 * デバッグ用アプリ状態の管理
 */
public class DebugApplication extends MainApplication
        implements IStethoAttacher {

    @Override
    public void onCreate() {
        super.onCreate();

        // Stetho 設定
        attachStetho(getApplicationContext());
    }


    /**
     * Stetho の組み込み
     *
     * @param appContext アプリケーションコンテキスト
     */
    @Override
    public void attachStetho(@NonNull Context appContext) {
        IStethoAttacher.DefaultImpls.attachStetho(this, appContext);
    }
}
