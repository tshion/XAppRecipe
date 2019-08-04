package work.shion.ktrecipe.pages.entrypoint.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import work.shion.ktrecipe.R
import java.util.Date
import kotlin.random.Random


/**
 * メインコンテンツ表示用Activity
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entrypoint_activity_main)

        // 初期表示をランダムで変更する
        val targetNavigationId = if (Random(Date().time).nextInt(100) < 50) {
            R.id.entrypoint_action_any_to_tutorial
        } else {
            R.id.entrypoint_action_any_to_tab
        }
        Navigation.findNavController(
                this@MainActivity,
                R.id.entrypoint_navigation_host_main
        ).navigate(targetNavigationId)
    }
}
