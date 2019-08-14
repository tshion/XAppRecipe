package work.shion.baser.nativeui.webview

import android.view.ViewGroup
import android.webkit.WebView
import work.shion.baser.android.WebChromeClientBuilder
import work.shion.baser.android.WebViewBuilder
import work.shion.baser.android.WebViewClientBuilder


/**
 * WebView の機能付与
 */
interface IWebViewAttacher {

    /**
     * Debugger の設定
     */
    fun attachDebugger() {
        WebView.setWebContentsDebuggingEnabled(true)
    }

    /**
     * WebView の破棄
     */
    fun destroy(target: WebView) {
        target.stopLoading()
        (target.parent as? ViewGroup)?.removeView(target)
        target.destroy()
    }

    /**
     * WebView 設定ビルダーの新規生成
     */
    fun newBuilder() = WebViewBuilder()

    /**
     * WebChromeClient 設定ビルダーの新規生成
     */
    fun newChromeClientBuilder() = WebChromeClientBuilder()

    /**
     * WebViewClient 設定ビルダーの新規生成
     */
    fun newViewClientBuilder() = WebViewClientBuilder()
}
