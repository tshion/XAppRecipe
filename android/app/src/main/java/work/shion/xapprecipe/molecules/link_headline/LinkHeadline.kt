package work.shion.xapprecipe.molecules.link_headline

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.MoleculesLinkHeadlineBinding

/**
 * リンク概要表示
 */
class LinkHeadline @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.molecules_link_headline
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding = MoleculesLinkHeadlineBinding.inflate(
        LayoutInflater.from(context),
        this
    )


    fun setup(
        description: String?,
        imagePath: String?,
        title: String?,
    ) {
        if (imagePath?.isBlank() != true) {
            Picasso.get()
                .load(imagePath)
                .into(binding.moleculesLinkHeadlineImage)
        }

        binding.moleculesLinkHeadlineDescription.text = description
        binding.moleculesLinkHeadlineTitle.text = title
    }
}
