package work.shion.xapprecipe.entrypoint

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import work.shion.xapprecipe.R

/**
 * Native 実装のエントリーポイント
 */
class MainActivity : AppCompatActivity(R.layout.entrypoint) {

  companion object {

    fun launch(from: Activity) {
      Intent(from, MainActivity::class.java)
        .also { from.startActivity(it) }
    }
  }
}
