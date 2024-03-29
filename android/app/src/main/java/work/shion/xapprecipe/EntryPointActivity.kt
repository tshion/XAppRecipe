package work.shion.xapprecipe

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.github.tshion.xapprecipe.R

/**
 * Native 実装のエントリーポイント
 */
class EntryPointActivity : AppCompatActivity(R.layout.entrypoint) {

    companion object {

        fun launch(from: Activity) {
            Intent(from, EntryPointActivity::class.java)
                .also { from.startActivity(it) }
        }
    }
}
