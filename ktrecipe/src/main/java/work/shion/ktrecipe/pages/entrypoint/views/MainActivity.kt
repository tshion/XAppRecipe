package work.shion.ktrecipe.pages.entrypoint.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import work.shion.ktrecipe.R
import work.shion.ktrecipe.pages.entrypoint.contracts.MainPresenterContract
import work.shion.ktrecipe.pages.entrypoint.contracts.MainViewContract
import work.shion.ktrecipe.pages.entrypoint.models.MainModel
import work.shion.ktrecipe.pages.entrypoint.presenters.MainPresenter
import java.lang.ref.WeakReference


/**
 * メインコンテンツ表示用Activity
 */
class MainActivity : AppCompatActivity(),
        MainViewContract {

    private val presenter: MainPresenterContract by lazy {
        MainPresenter(
                model = MainModel(),
                viewer = WeakReference(this)
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entrypoint_activity_main)

        // 初期表示をランダムで変更する
        presenter.callFirstView()
    }


    /**
     * Tab ページに遷移
     */
    override fun goTabPage() {
        Navigation.findNavController(
                this@MainActivity,
                R.id.entrypoint_navigation_host_main
        ).navigate(R.id.entrypoint_action_any_to_tab)
    }

    /**
     * Tutorial ページに遷移
     */
    override fun goTutorialPage() {
        Navigation.findNavController(
                this@MainActivity,
                R.id.entrypoint_navigation_host_main
        ).navigate(R.id.entrypoint_action_any_to_tutorial)
    }
}
