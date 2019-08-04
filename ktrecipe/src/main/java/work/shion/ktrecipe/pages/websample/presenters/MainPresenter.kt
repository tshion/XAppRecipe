package work.shion.ktrecipe.pages.websample.presenters

import work.shion.baser.nativeui.webview.IWebViewAttacher
import work.shion.ktrecipe.pages.websample.contracts.MainPresenterContract
import work.shion.ktrecipe.pages.websample.contracts.MainViewContract
import java.lang.ref.WeakReference


/**
 * メインコンテンツの挙動実装
 */
class MainPresenter(
        private val viewer: WeakReference<MainViewContract>
) : MainPresenterContract, IWebViewAttacher {

    /**
     * 最初のWeb ページ呼び出し
     */
    override fun callFirstWebPage() {
        viewer.get()?.setupWebView(super<IWebViewAttacher>.newBuilder()
                .setJavaScriptEnabled(true)
                .setUserAgentString("偽装されたUserAgent")
                .setWebChromeClient(super<IWebViewAttacher>.newChromeClientBuilder()
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
                .setWebViewClient(super<IWebViewAttacher>.newViewClientBuilder()
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
