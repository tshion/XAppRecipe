package work.shion.xapprecipe

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * ネイティブ側のルート
 */
@Deprecated("画面分割するため")
class NativeRootActivity : AppCompatActivity() {

  companion object {

    fun launch(from: Activity) {
      Intent(from, NativeRootActivity::class.java)
        .also { from.startActivity(it) }
    }
  }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.native_root)
  }
}
