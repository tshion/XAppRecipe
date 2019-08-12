package work.shion.ktrecipe.pages.image_detail.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import work.shion.ktrecipe.R
import work.shion.ktrecipe.databinding.ImageDetailActivityMainBinding
import work.shion.ktrecipe.pages.image_detail.contracts.MainViewContract
import work.shion.ktrecipe.pages.image_detail.presenters.MainPresenter
import work.shion.ktrecipe.pages.image_detail.viewmodels.MainViewModel
import java.lang.ref.WeakReference

/**
 * 画像詳細表示用Activity
 */
class MainActivity : AppCompatActivity(),
        MainViewContract {

    companion object {
        private const val KEY_URL = "KEY_URL"


        /**
         * このActivity の起動
         * @param imageUrl 表示したい画像URL
         */
        fun start(
                fromActivity: Activity,
                imageUrl: String?
        ) {
            val intent = Intent(fromActivity, MainActivity::class.java)
            intent.putExtra(KEY_URL, imageUrl)
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

        val binding = DataBindingUtil.setContentView<ImageDetailActivityMainBinding>(
                this,
                R.layout.image_detail_activity_main
        )
        binding.presenter = presenter
        binding.viewmodel = ViewModelProviders.of(this)
                .get(MainViewModel::class.java)
                .apply {
                    imageUrl.set(intent.getStringExtra(KEY_URL))
                }
    }


    /**
     * 画面を閉じる
     */
    override fun close() {
        finish()
    }
}
