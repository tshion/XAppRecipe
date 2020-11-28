package work.shion.xapprecipe.atoms

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import work.shion.xapprecipe.R

/**
 * ローディング表示
 *
 * ## Example
 * ``` xml
 * <Framelayout
 *     android:layout_width="match_parent"
 *     android:layout_height="match_parent">
 *
 *     <!-- ローディング表示の下にあるコンテンツ -->
 *     <View
 *         android:layout_width="match_parent"
 *         android:layout_height="match_parent" />
 *
 *     <!-- ローディング表示 -->
 *     <(package).atoms.CircleLoadingOverlay
 *         android:layout_width="match_parent"
 *         android:layout_height="match_parent"
 *         android:visibility="visible" />
 * </Framelayout>
 * ```
 */
class CircleLoadingOverlay @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    init {
        View.inflate(context, R.layout.atoms_circle_loading_overlay, this)
        setBackgroundResource(R.color.atoms_circle_loading_overlay_background)
    }
}
