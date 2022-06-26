package com.github.tshion.xapprecipe.webapp.pages.profile_editor

import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.TtsSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.tshion.xapprecipe.webapp.databinding.PagesProfileEditorBinding

/**
 * プロフィール編集
 */
class MainFragment : Fragment() {

    private var binding: PagesProfileEditorBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PagesProfileEditorBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.pagesProfileEditorHeader?.setupBackListener {
            activity?.onBackPressed()
        }

        binding?.pagesProfileEditorName?.listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                (s as? SpannableStringBuilder)?.let {
                    val allSpans = it.getSpans(0, it.length, TtsSpan::class.java)
                    val target = allSpans?.firstOrNull()

                    if (target?.type == TtsSpan.TYPE_TEXT) {
                        val result = target.args.getString(TtsSpan.ARG_TEXT)
                        it.removeSpan(target)
                        result
                    } else {
                        null
                    }
                }?.also {
                    binding?.pagesProfileEditorNameRuby?.input = it
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
