package com.github.tshion.xapprecipe.webapp.atoms

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.github.tshion.xapprecipe.webapp.R

/**
 * 肯定的な用途に使うボタン
 * ``` xml
 * <(package).atoms.PositiveButton
 *     android:layout_width="wrap_content"
 *     android:layout_height="wrap_content"
 *     android:text="button text" />
 * ```
 */
class PositiveButton(
    context: Context,
    attrs: AttributeSet?,
) : MaterialButton(context, attrs, R.attr.materialButtonStyle) {

    init {
        ContextCompat.getColor(context, R.color.app_accent)
            .also { setBackgroundColor(it) }
        ContextCompat.getColor(context, R.color.app_text_contrast)
            .also { setTextColor(it) }
    }
}
