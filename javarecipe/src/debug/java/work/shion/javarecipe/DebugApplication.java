package work.shion.javarecipe;

import work.shion.baser.nativeui.webview.IWebViewJavaAttacher;
import work.shion.baser.stetho.IStethoJavaAttacher;
import work.shion.baser.strictmode.IStrictModeJavaAttacher;


/**
 * デバッグ用アプリ状態の管理
 */
public class DebugApplication extends MainApplication
        implements IStethoJavaAttacher, IStrictModeJavaAttacher, IWebViewJavaAttacher {

    @Override
    public void onCreate() {
        super.onCreate();

        // Stetho 設定
        attachStetho(getApplicationContext());

        // StrictMode の有効化
        setupThreadPolicy();
        setupVmPolicy();

        // WebView 設定
        IWebViewJavaAttacher.super.attachDebugger();
    }
}
