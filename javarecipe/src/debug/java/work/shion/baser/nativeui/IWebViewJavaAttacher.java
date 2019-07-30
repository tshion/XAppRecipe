package work.shion.baser.nativeui;

import android.webkit.WebView;

import org.jetbrains.annotations.NotNull;


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
    default void destory(@NotNull WebView target) {
        IWebViewAttacher.DefaultImpls.destory(this, target);
    }
}
