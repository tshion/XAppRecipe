package work.shion.ktrecipe

import work.shion.baser.nativeui.webview.IWebViewAttacher
import work.shion.baser.stetho.IStethoAttacher
import work.shion.strictmode.IStrictModeAttacher


/**
 * デバッグ用アプリ状態の管理
 */
class DebugApplication : MainApplication(),
        IStethoAttacher, IStrictModeAttacher, IWebViewAttacher {

    override fun onCreate() {
        super.onCreate()

        // Stetho 設定
        attachStetho(applicationContext)

        // StrictMode の有効化
        setupThreadPolicy()
        setupVmPolicy()

        // WebView 設定
        super<IWebViewAttacher>.attachDebugger()
    }
}
