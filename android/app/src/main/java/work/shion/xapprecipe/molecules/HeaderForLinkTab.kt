package work.shion.xapprecipe.molecules

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.withStyledAttributes
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.MoleculesHeaderForLinkTabBinding

/**
 * リンクタブ用のHeader
 *
 * ## Example1
 * ``` xml
 * <(package).molecules.HeaderForLinkTab
 *     android:id="@+id/(id)"
 *     android:layout_width="match_parent"
 *     android:layout_height="?attr/actionBarSize"
 *     android:text="タイトル" />
 * ```
 *
 * ``` kotlin
 *     (id).apply {
 *         isShowNewsBadge = true
 *         setupTapMenuListener { _ -> ... }
 *         setupTapNewsListener { _ -> ... }
 *     }
 * }
 * ```
 */
class HeaderForLinkTab @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private val binding = MoleculesHeaderForLinkTabBinding.inflate(
        LayoutInflater.from(context),
        this
    )

    var isShowNewsBadge: Boolean
        get() = binding.moleculesHeaderForLinkTabNews.isShowBadge
        set(value) {
            binding.moleculesHeaderForLinkTabNews.isShowBadge = value
        }


    init {
        orientation = HORIZONTAL
        setBackgroundResource(R.color.app_main)

        context.withStyledAttributes(
            attrs,
            R.styleable.HeaderForLinkTab,
            defStyleAttr,
        ) {
            binding.moleculesHeaderForLinkTabTitle.text = getString(R.styleable.HeaderForLinkTab_android_text)
        }
    }


    fun setupTapMenuListener(listener: OnClickListener?) {
        binding.moleculesHeaderForLinkTabMenu.setOnClickListener(listener)
    }

    fun setupTapNewsListener(listener: OnClickListener?) {
        binding.moleculesHeaderForLinkTabNews.setOnClickListener(listener)
    }
}
