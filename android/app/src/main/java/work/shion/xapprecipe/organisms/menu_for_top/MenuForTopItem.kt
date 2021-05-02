package work.shion.xapprecipe.organisms.menu_for_top

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.OrganismsMenuForTopItemBinding

class MenuForTopItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding = OrganismsMenuForTopItemBinding.inflate(
        LayoutInflater.from(context),
        this
    )


    init {
        context.withStyledAttributes(
            attrs,
            R.styleable.MenuForTopItem,
            defStyleAttr,
            defStyleRes,
        ) {

            binding.organismsMenuForTopItemIcon.setImageDrawable(
                getDrawable(R.styleable.MenuForTopItem_android_src)
            )
            binding.organismsMenuForTopItemTitle.text = getString(R.styleable.MenuForTopItem_android_text)
        }
    }


    fun setup(
        @DrawableRes iconId: Int,
        @StringRes titleId: Int,
    ) {
        binding.organismsMenuForTopItemIcon.setImageDrawable(
            AppCompatResources.getDrawable(context, iconId)
        )
        binding.organismsMenuForTopItemTitle.text = resources.getText(titleId)
    }
}
