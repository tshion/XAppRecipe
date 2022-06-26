package work.shion.xapprecipe.atoms

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.github.tshion.xapprecipe.databinding.AtomsNewsIconBinding

/**
 * お知らせアイコン
 *
 * ## Example
 * ``` xml
 * <FrameLayout
 *     android:layout_width="match_parent"
 *     android:layout_height="?attr/actionBarSize"
 *     android:background="@color/app_main">
 *
 *     <work.shion.xapprecipe.atoms.AtomsNewsIcon
 *         android:id="@+id/(id)"
 *         android:layout_width="wrap_content"
 *         android:layout_height="match_parent"
 *         android:layout_gravity="end" />
 * </FrameLayout>
 * ```
 *
 * ``` kotlin
 * (id).isShowBadge = true // or false
 * ```
 */
class AtomsNewsIcon @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding = AtomsNewsIconBinding.inflate(
        LayoutInflater.from(context),
        this
    )


    /**
     * バッジ表示するかどうか
     */
    var isShowBadge: Boolean
        get() = binding.atomsNewsIconBadge.isVisible
        set(value) {
            binding.atomsNewsIconBadge.isVisible = value
        }
}
