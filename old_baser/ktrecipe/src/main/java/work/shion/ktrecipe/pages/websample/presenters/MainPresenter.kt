package work.shion.ktrecipe.pages.websample.presenters

import work.shion.baser.android.WebChromeClientBuilder
import work.shion.baser.android.WebViewBuilder
import work.shion.baser.android.WebViewClientBuilder
import work.shion.ktrecipe.pages.websample.contracts.MainPresenterContract
import work.shion.ktrecipe.pages.websample.contracts.MainViewContract
import java.lang.ref.WeakReference


/**
 * メインコンテンツの挙動実装
 */
internal class MainPresenter(
        private val viewer: WeakReference<MainViewContract>
) : MainPresenterContract {

    /**
     * 最初のWeb ページ呼び出し
     */
    override fun callFirstWebPage() {
        viewer.get()?.setupWebView(WebViewBuilder()
                .setJavaScriptEnabled(true)
                .setUserAgentString("偽装されたUserAgent")
                .setWebChromeClient(WebChromeClientBuilder()
                        .setOnJsAlert { view, url, message, result ->
                            val viewer = viewer.get() ?: return@setOnJsAlert false
                            if (!message.isNullOrEmpty()) {
                                viewer.showAlert(message)
                                result?.confirm()
                                true
                            } else {
                                false
                            }
                        }
                        .build()
                )
                .setWebViewClient(WebViewClientBuilder()
                        .setShouldOverrideUrlLoading { view, request ->
                            val viewer = viewer.get() ?: return@setShouldOverrideUrlLoading true
                            when (request?.url?.scheme?.equals("file", ignoreCase = true)) {
                                // WebView 内で表示させる
                                true -> false

                                // file プロトコルでないものはアプリ外ブラウザで表示する
                                else -> {
                                    viewer.goBrowser(request?.url!!)
                                    true
                                }
                            }
                        }
                        .build()
                )
        )
    }
}
