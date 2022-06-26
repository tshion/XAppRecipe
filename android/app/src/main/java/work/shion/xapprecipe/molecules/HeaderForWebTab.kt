package work.shion.xapprecipe.molecules

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.withStyledAttributes
import com.github.tshion.xapprecipe.R
import com.github.tshion.xapprecipe.databinding.MoleculesHeaderForWebTabBinding

/**
 * WEB タブ用のHeader
 *
 * ## Example1
 * ``` xml
 * <(package).molecules.HeaderForWebTab
 *     android:id="@+id/(id)"
 *     android:layout_width="match_parent"
 *     android:layout_height="?attr/actionBarSize"
 *     android:text="タイトル" />
 * ```
 *
 * ``` kotlin
 *     (id).apply {
 *         setupTapMenuListener { _ -> ... }
 *         setupTapReloadListener { _ -> ... }
 *     }
 * }
 * ```
 */
class HeaderForWebTab @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private val binding = MoleculesHeaderForWebTabBinding.inflate(
        LayoutInflater.from(context),
        this
    )


    init {
        orientation = HORIZONTAL
        setBackgroundResource(R.color.app_main)

        context.withStyledAttributes(
            attrs,
            R.styleable.HeaderForWebTab,
            defStyleAttr,
        ) {
            binding.moleculesHeaderForWebTabTitle.text = getString(R.styleable.HeaderForWebTab_android_text)
        }
    }


    fun setupTapMenuListener(listener: OnClickListener?) {
        binding.moleculesHeaderForWebTabMenu.setOnClickListener(listener)
    }

    fun setupTapReloadListener(listener: OnClickListener?) {
        binding.moleculesHeaderForWebTabReload.setOnClickListener(listener)
    }
}
