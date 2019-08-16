package work.shion.ktrecipe

import android.webkit.WebView
import work.shion.baser.stetho.IStethoAttacher
import work.shion.baser.strictmode.IStrictModeAttacher


/**
 * デバッグ用アプリ状態の管理
 */
class DebugApplication : MainApplication(),
        IStethoAttacher, IStrictModeAttacher {

    override fun onCreate() {
        super.onCreate()

        // Stetho 設定
        attachStetho(applicationContext)

        // StrictMode の有効化
        setupThreadPolicy()
        setupVmPolicy()

        // WebView 設定
        WebView.setWebContentsDebuggingEnabled(true)
    }
}
