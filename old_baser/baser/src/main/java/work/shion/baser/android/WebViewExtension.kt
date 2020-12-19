package work.shion.baser.android

import android.view.ViewGroup
import android.webkit.WebView


/**
 * WebView の破棄
 */
fun WebView.throwAway() {
    stopLoading()
    (parent as? ViewGroup)?.removeView(this)
    destroy()
}
