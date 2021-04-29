package work.shion.xapprecipe.molecules

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.MoleculesHeaderForWebTabBinding

/**
 * WEB タブ用のHeader
 *
 * ## Example1
 * ``` xml
 * <(package).molecules.job_overview.HeaderForWebTab
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
    defStyleAttr: Int = 0,
    defStyleRes: Int = R.style.molecules_header_for_web_tab,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding = MoleculesHeaderForWebTabBinding.inflate(
        LayoutInflater.from(context),
        this
    )


    init {
        context.withStyledAttributes(
            attrs,
            R.styleable.HeaderForWebTab,
            defStyleAttr,
            defStyleRes,
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
