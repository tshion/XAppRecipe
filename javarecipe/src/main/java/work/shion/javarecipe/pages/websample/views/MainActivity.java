package work.shion.javarecipe.pages.websample.views;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

import work.shion.baser.android.WebViewBuilder;
import work.shion.baser.android.WebViewExtensionKt;
import work.shion.javarecipe.R;
import work.shion.javarecipe.pages.websample.contracts.MainPresenterContract;
import work.shion.javarecipe.pages.websample.contracts.MainViewContract;
import work.shion.javarecipe.pages.websample.presenters.MainPresenter;


/**
 * メインコンテンツ表示用Activity
 */
public class MainActivity extends AppCompatActivity
        implements MainViewContract {

    public static void start(Activity fromActivity) {
        final Intent intent = new Intent(fromActivity, MainActivity.class);
        fromActivity.startActivity(intent);
    }


    private final MainPresenterContract presenter = new MainPresenter(
            new WeakReference<>(this)
    );


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.websample_fragment_main);

        presenter.callFirstWebPage();
    }

    @Override
    protected void onDestroy() {
        WebViewExtensionKt.throwAway(findViewById(R.id.websample_fragment_main_webview));
        super.onDestroy();
    }


    /**
     * 外部ブラウザに遷移
     */
    @Override
    public void goBrowser(Uri uri) {
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    /**
     * WebView のセットアップ
     */
    @Override
    public void setupWebView(WebViewBuilder builder) {
        builder.into(findViewById(R.id.websample_fragment_main_webview))
                .loadUrl("file:///android_asset/websample/index.html");
    }

    /**
     * アラートダイアログの表示
     */
    @Override
    public void showAlert(String message) {
        final MessageDialog dialog = MessageDialog.newInstance(message);
        dialog.show(getSupportFragmentManager(), MessageDialog.TAG);
    }
}
