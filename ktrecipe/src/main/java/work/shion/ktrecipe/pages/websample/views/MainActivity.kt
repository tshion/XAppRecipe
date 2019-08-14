package work.shion.ktrecipe.pages.websample.views

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.websample_fragment_main.*
import work.shion.baser.android.WebViewBuilder
import work.shion.baser.nativeui.webview.IWebViewAttacher
import work.shion.ktrecipe.R
import work.shion.ktrecipe.pages.websample.contracts.MainViewContract
import work.shion.ktrecipe.pages.websample.presenters.MainPresenter
import java.lang.ref.WeakReference


/**
 * メインコンテンツ表示用Activity
 */
class MainActivity : AppCompatActivity(),
        MainViewContract, IWebViewAttacher {

    companion object {
        /**
         * このActivity の起動
         */
        fun start(fromActivity: Activity) {
            val intent = Intent(fromActivity, MainActivity::class.java)
            fromActivity.startActivity(intent)
        }
    }


    private val presenter by lazy {
        MainPresenter(
                viewer = WeakReference(this)
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.websample_fragment_main)

        presenter.callFirstWebPage()
    }

    override fun onDestroy() {
        super<IWebViewAttacher>.destroy(websample_fragment_main_webview)
        super.onDestroy()
    }


    /**
     * 外部ブラウザに遷移
     */
    override fun goBrowser(uri: Uri) {
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    /**
     * WebView のセットアップ
     */
    override fun setupWebView(builder: WebViewBuilder) {
        builder.into(websample_fragment_main_webview)
                .loadUrl("file:///android_asset/websample/index.html")
    }

    /**
     * アラートダイアログの表示
     */
    override fun showAlert(message: String) {
        MessageDialog.newInstance(message).apply {
            show(supportFragmentManager, MessageDialog.TAG)
        }
    }
}
