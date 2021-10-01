package work.shion.xapprecipe.molecules

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.withStyledAttributes
import androidx.databinding.DataBindingUtil
import work.shion.xapprecipe.R
import work.shion.xapprecipe.databinding.MoleculesEditFormBinding

/**
 * 入力欄
 *
 * ## Example
 * ### 前提
 * ``` xml
 * <(package).molecules.EditForm
 *     android:id="(id)"
 *     android:layout_width="match_parent"
 *     android:layout_height="wrap_content"
 *     android:hint="タイトル"
 *     android:inputType="text"
 *     android:privateImeOptions="" />
 * ```
 *
 * ### 入力データの取得
 * ``` kotlin
 * (id).input
 * ```
 *
 * ### 変更の検知
 * ``` kotlin
 * (id).listener = object : TextWatcher {
 *     ......
 * }
 *
 * ### エラーの設定
 * ``` kotlin
 * (id).errors = "..."
 * ```
 */
class EditForm @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private val binding: MoleculesEditFormBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.molecules_edit_form,
        this,
        true
    )

    var errors: String?
        get() = binding.message
        set(value) {
            binding.message = value
        }

    var input: String?
        get() = binding.input
        set(value) {
            binding.input = value
        }

    var listener: TextWatcher? = null
        set(value) {
            binding.moleculesEditFormInput.apply {
                if (value != null) {
                    addTextChangedListener(value)
                } else {
                    removeTextChangedListener(field)
                }
            }
            field = listener
        }


    init {
        orientation = VERTICAL

        context.withStyledAttributes(
            attrs,
            R.styleable.EditForm,
            defStyleAttr,
        ) {
            binding.input = getString(R.styleable.EditForm_android_text)
            binding.label = getString(R.styleable.EditForm_android_hint)

            binding.moleculesEditFormInput.inputType = getInt(R.styleable.EditForm_android_inputType, 0)
            binding.moleculesEditFormInput.privateImeOptions = getString(R.styleable.EditForm_android_privateImeOptions)
        }
    }
}
