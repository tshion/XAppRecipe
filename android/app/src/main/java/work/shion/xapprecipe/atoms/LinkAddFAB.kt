package work.shion.xapprecipe.atoms

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import work.shion.xapprecipe.R

/**
 * リンク追加FAB
 *
 * ## Example
 * ``` xml
 * <(package).atoms.LinkAddFAB
 *     android:layout_width="wrap_content"
 *     android:layout_height="wrap_content" />
 * ```
 */
class LinkAddFAB @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = com.google.android.material.R.attr.floatingActionButtonStyle,
) : FloatingActionButton(context, attrs, defStyleAttr) {

    init {
        ContextCompat.getColorStateList(context, R.color.app_accent)
            ?.also { backgroundTintList = it }

        contentDescription = context.getString(R.string.atoms_link_add_fab_icon)

        ContextCompat.getColorStateList(context, R.color.atoms_link_add_fab_icon)
            ?.also { imageTintList = it }
        setImageResource(R.drawable.icon_bookmark_add)
    }
}
