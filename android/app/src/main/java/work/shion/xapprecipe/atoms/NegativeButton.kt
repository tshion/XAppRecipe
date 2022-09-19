package work.shion.xapprecipe.atoms

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.github.tshion.xapprecipe.R
import com.google.android.material.button.MaterialButton

/**
 * 否定的な用途に使うボタン
 * ``` xml
 * <(package).atoms.NegativeButton
 *     android:layout_width="wrap_content"
 *     android:layout_height="wrap_content"
 *     android:text="button text" />
 * ```
 */
class NegativeButton(
    context: Context,
    attrs: AttributeSet?,
) : MaterialButton(context, attrs, com.google.android.material.R.attr.materialButtonOutlinedStyle) {

    init {
        ContextCompat.getColor(context, R.color.atoms_negative_button_background)
            .also { setBackgroundColor(it) }
        setStrokeColorResource(R.color.app_accent)
        ContextCompat.getColor(context, R.color.app_text)
            .also { setTextColor(it) }
    }
}
