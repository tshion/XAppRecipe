package work.shion.baser.nativeui

import android.view.ViewGroup
import android.webkit.WebView


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
    fun destory(target: WebView) {
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
