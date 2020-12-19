package work.shion.javarecipe.pages.websample.presenters;

import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

import com.annimon.stream.Optional;

import java.lang.ref.WeakReference;

import work.shion.baser.android.WebChromeClientBuilder;
import work.shion.baser.android.WebViewBuilder;
import work.shion.baser.android.WebViewClientBuilder;
import work.shion.javarecipe.pages.websample.contracts.MainPresenterContract;
import work.shion.javarecipe.pages.websample.contracts.MainViewContract;


/**
 * メインコンテンツの挙動実装
 */
public class MainPresenter implements MainPresenterContract {

    private final WeakReference<MainViewContract> viewer;


    public MainPresenter(
            WeakReference<MainViewContract> viewer
    ) {
        this.viewer = viewer;
    }


    /**
     * 最初のWeb ページ呼び出し
     */
    @Override
    public void callFirstWebPage() {
        WebChromeClient chromeClient = new WebChromeClientBuilder()
                .setOnJsAlert((view, url, message, result) -> {
                    if (viewer.get() != null && !TextUtils.isEmpty(message)) {
                        viewer.get().showAlert(message);
                        result.confirm();
                        return true;
                    } else {
                        return false;
                    }
                })
                .build();

        WebViewClient client = new WebViewClientBuilder()
                .setShouldOverrideUrlLoading((view, request) -> {
                    String scheme = request.getUrl().getScheme();
                    if (scheme != null && scheme.equalsIgnoreCase("file")) {
                        // WebView 内で表示させる
                        return false;
                    } else {
                        // file プロトコルでないものはアプリ外ブラウザで表示する
                        Optional.ofNullable(
                                this.viewer.get()
                        ).ifPresent(viewer ->
                                viewer.goBrowser(request.getUrl())
                        );
                        return true;
                    }
                })
                .build();

        Optional.ofNullable(viewer.get())
                .ifPresent(viewer -> {
                    viewer.setupWebView(new WebViewBuilder()
                            .setJavaScriptEnabled(true)
                            .setUserAgentString("偽装されたUserAgent")
                            .setWebChromeClient(chromeClient)
                            .setWebViewClient(client)
                    );
                });
    }
}
