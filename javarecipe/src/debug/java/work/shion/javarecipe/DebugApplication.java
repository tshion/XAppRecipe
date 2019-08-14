package work.shion.javarecipe;

import android.webkit.WebView;

import work.shion.baser.stetho.IStethoJavaAttacher;
import work.shion.baser.strictmode.IStrictModeJavaAttacher;


/**
 * デバッグ用アプリ状態の管理
 */
public class DebugApplication extends MainApplication
        implements IStethoJavaAttacher, IStrictModeJavaAttacher {

    @Override
    public void onCreate() {
        super.onCreate();

        // Stetho 設定
        attachStetho(getApplicationContext());

        // StrictMode の有効化
        setupThreadPolicy();
        setupVmPolicy();

        // WebView 設定
        WebView.setWebContentsDebuggingEnabled(true);
    }
}
