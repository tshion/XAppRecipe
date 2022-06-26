package com.github.tshion.xapprecipe.webapp.molecules

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import com.github.tshion.xapprecipe.webapp.R
import com.github.tshion.xapprecipe.webapp.databinding.MoleculesHeaderBinding

/**
 * Header
 *
 * ## Example1
 * ``` xml
 * <(package).molecules.Header
 *     android:id="@+id/(id)"
 *     android:layout_width="match_parent"
 *     android:layout_height="?attr/actionBarSize"
 *     android:text="タイトル" />
 * ```
 *
 * ``` kotlin
 * (id).setupBackListener { v -> }
 * ```
 */
class Header @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.molecules_header,
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding = MoleculesHeaderBinding.inflate(
        LayoutInflater.from(context),
        this
    )


    init {
        context.withStyledAttributes(
            attrs,
            R.styleable.Header,
            defStyleAttr,
            defStyleRes,
        ) {
            binding.moleculesHeaderTitle.text = getString(R.styleable.Header_android_text)
        }
    }


    fun setupBackListener(listener: OnClickListener?) {
        binding.moleculesHeaderBack.apply {
            isVisible = listener != null
            setOnClickListener(listener)
        }
    }
}
