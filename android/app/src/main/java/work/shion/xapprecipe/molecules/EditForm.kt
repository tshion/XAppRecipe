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
        get() = binding.errors
        set(value) {
            binding.errors = value
        }

    var input: String?
        get() = binding.input
        set(value) {
            binding.input = value
        }


    init {
        orientation = VERTICAL

        context.withStyledAttributes(
            attrs,
            R.styleable.EditForm,
            defStyleAttr,
        ) {
            binding.title = getString(R.styleable.EditForm_android_text)
        }
    }


    fun registerListener(listener: TextWatcher?) {
        binding.moleculesEditFormInput.apply {
            addTextChangedListener(listener)
        }
    }
}
