package work.shion.javarecipe.pages.websample.contracts;

import android.net.Uri;

import work.shion.baser.android.WebViewBuilder;


/**
 * メインコンテンツの表示定義
 */
public interface MainViewContract {

    /**
     * 外部ブラウザに遷移
     */
    void goBrowser(Uri uri);

    /**
     * WebView のセットアップ
     */
    void setupWebView(WebViewBuilder builder);

    /**
     * アラートダイアログの表示
     */
    void showAlert(String message);
}
