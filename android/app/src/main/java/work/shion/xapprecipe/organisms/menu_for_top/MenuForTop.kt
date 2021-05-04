package work.shion.xapprecipe.organisms.menu_for_top

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.OrganismsMenuForTopBinding

class MenuForTop @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.organisms_menu_for_top,
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val adapter = MenuForTopAdapter()

    private val binding = OrganismsMenuForTopBinding.inflate(
        LayoutInflater.from(context),
        this
    )

    private val model = MenuForTopModel()

    var tapListener: TapListener?
        get() = adapter.listener
        set(value) {
            adapter.listener = value
        }


    init {
        binding.organismsMenuForTopList.adapter = adapter
    }


    fun setup(isLogin: Boolean) {
        if (isLogin) {
            model.loadMenuLogin()
        } else {
            model.loadMenuLogout()
        }.also { adapter.displayData = it }
    }
}
