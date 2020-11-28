package work.shion.xapprecipe.entrypoint

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import work.shion.xapprecipe.NavEntrypointDirections
import work.shion.xapprecipe.R

/**
 * Native 実装のエントリーポイント
 */
class MainActivity : AppCompatActivity(R.layout.entrypoint), MainViewContract {

    companion object {

        private const val REQUEST_LAUNCH_ERROR_DIALOG = 1000


        fun launch(from: Activity) {
            Intent(from, MainActivity::class.java)
                .also { from.startActivity(it) }
        }
    }


    /**
     * 初回起動者向けフローへ遷移
     */
    override fun goBeginner() = findNavController(R.id.entrypoint)
        .navigate(NavEntrypointDirections.navactToTutorial())

    /**
     * 複数回起動者向けフローへ遷移
     */
    override fun goLoyalty() = findNavController(R.id.entrypoint)
        .navigate(NavEntrypointDirections.navactToTop())

    /**
     * ダイアログ選択結果の受け取り
     */
    override fun onDialogResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_LAUNCH_ERROR_DIALOG -> {
                if (resultCode == RESULT_OK) {
                } else {
                }
            }
        }
    }

    /**
     * 起動失敗ダイアログの表示
     */
    override fun showLaunchErrorDialog() = findNavController(R.id.entrypoint)
        .navigate(NavEntrypointDirections.navactShowLaunchErrorDialog(REQUEST_LAUNCH_ERROR_DIALOG))
}
