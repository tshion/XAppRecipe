package work.shion.baser.nativeui.webview;

import android.webkit.WebView;

import org.jetbrains.annotations.NotNull;

import work.shion.baser.android.WebChromeClientBuilder;
import work.shion.baser.android.WebViewBuilder;
import work.shion.baser.android.WebViewClientBuilder;


/**
 * WebView の機能付与のJava ラッパー
 */
public interface IWebViewJavaAttacher extends IWebViewAttacher {

    /**
     * Debugger の設定
     */
    @Override
    default void attachDebugger() {
        IWebViewAttacher.DefaultImpls.attachDebugger(this);
    }

    /**
     * WebView の破棄
     */
    @Override
    default void destroy(@NotNull WebView target) {
        IWebViewAttacher.DefaultImpls.destroy(this, target);
    }

    /**
     * WebView 設定ビルダーの新規生成
     */
    @NotNull
    @Override
    default WebViewBuilder newBuilder() {
        return IWebViewAttacher.DefaultImpls.newBuilder(this);
    }

    /**
     * WebChromeClient 設定ビルダーの新規生成
     */
    @NotNull
    @Override
    default WebChromeClientBuilder newChromeClientBuilder() {
        return IWebViewAttacher.DefaultImpls.newChromeClientBuilder(this);
    }

    /**
     * WebViewClient 設定ビルダーの新規生成
     */
    @NotNull
    @Override
    default WebViewClientBuilder newViewClientBuilder() {
        return IWebViewAttacher.DefaultImpls.newViewClientBuilder(this);
    }
}
