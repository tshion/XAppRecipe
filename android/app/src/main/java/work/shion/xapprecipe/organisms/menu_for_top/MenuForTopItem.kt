package work.shion.xapprecipe.organisms.menu_for_top

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import work.shion.xapprecipe.databinding.OrganismsMenuForTopItemBinding

internal class MenuForTopItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding = OrganismsMenuForTopItemBinding.inflate(
        LayoutInflater.from(context),
        this
    )


    fun setup(menu: MenuForTopItemType) {
        binding.organismsMenuForTopItemIcon.setImageDrawable(
            AppCompatResources.getDrawable(context, menu.iconId)
        )
        binding.organismsMenuForTopItemTitle.setText(menu.titleId)
    }
}
