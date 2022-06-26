package work.shion.xapprecipe.atoms

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import com.github.tshion.xapprecipe.databinding.AtomsDotsIndicatorBinding

/**
 * 点のインジケーター
 *
 * ## Example
 * ``` xml
 * <androidx.viewpager2.widget.ViewPager2
 *     android:id="(id_pager)"
 *     android:layout_width="match_parent"
 *     android:layout_height="0dp"
 *     android:layout_weight="1" />
 *
 * <(package).atoms.DotsIndicator
 *     android:id="(id_dots)"
 *     android:layout_width="match_parent"
 *     android:layout_height="wrap_content" />
 * ```
 *
 * ``` kotlin
 * val dots = findViewById<DotsIndicator>(R.id.(id_dots))
 * val pager = findViewById<ViewPager2>(R.id.(id_pager))
 *
 * pager.adapter = (Adapter)
 * TablayoutMediator(dots, pager) { tab, position ->
 * }.attach()
 * ```
 */
class DotsIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding = AtomsDotsIndicatorBinding.inflate(
        LayoutInflater.from(context),
        this
    )

    val tabs = binding.atomsDotsIndicatorTabs
}
