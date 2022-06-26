package work.shion.xapprecipe.atoms.media_tile

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import com.github.tshion.xapprecipe.R
import com.github.tshion.xapprecipe.databinding.AtomMediaTileBinding

/**
 * メディア表示用タイル
 *
 * ## Example
 * ``` xml
 * <work.shion.xapprecipe.atoms.media_tile.MediaTile
 *     android:layout_width="match_parent"
 *     android:layout_height="wrap_content"
 *     android:src="(drawable)"
 *     android:text="title" />
 * ```
 */
class MediaTile @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var binding: AtomMediaTileBinding? = null

    /**
     * プレビュー画像
     */
    var preview: Drawable? = null
        set(value) {
            field = value
            binding?.atomMediaTilePreview?.setImageDrawable(value)
        }

    /**
     * タイトル
     */
    var title: String? = null
        set(value) {
            field = value
            binding?.atomMediaTileTitle?.text = value
        }


    init {
        binding = AtomMediaTileBinding.inflate(
            LayoutInflater.from(context),
            this
        )

        context.withStyledAttributes(
            attrs,
            R.styleable.AtomMediaTile,
            defStyleAttr,
            defStyleRes,
        ) {
            preview = getDrawable(R.styleable.AtomMediaTile_android_src)
            title = getString(R.styleable.AtomMediaTile_android_text)
        }
    }
}
