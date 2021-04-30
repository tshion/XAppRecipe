package work.shion.xapprecipe.atoms

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import work.shion.xapprecipe.databinding.AtomsDotsIndicatorBinding

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
