package work.shion.ktrecipe.pages.websample.contracts

import android.net.Uri
import work.shion.baser.nativeui.webview.WebViewBuilder


/**
 * メインコンテンツの表示定義
 */
interface MainViewContract {

    /**
     * 外部ブラウザに遷移
     */
    fun goBrowser(uri: Uri)

    /**
     * WebView のセットアップ
     */
    fun setupWebView(builder: WebViewBuilder)

    /**
     * アラートダイアログの表示
     */
    fun showAlert(message: String)
}
