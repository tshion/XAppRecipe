package com.github.tshion.xapprecipe.webapp.templates

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.github.tshion.xapprecipe.webapp.databinding.TemplatesLinkInsertDialogBinding

/**
 * リンク登録ダイアログ
 *
 * ## Example
 * ### ダイアログの呼び出し
 * ``` kotlin
 * activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
 *     ?.navigate(NavEntrypointDirections.navactShowLinkInsertDialog("request key"))
 * ```
 *
 * ### ダイアログ選択結果の受け取り
 * ``` kotlin
 * class Xyz : Fragment() {
 *
 *     ......
 *
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *         setFragmentResultListener("request key") { _, result ->
 *             val input = LinkInsertDialog.pickInput(result)
 *             // Do something.
 *         }
 *     }
 *
 *     ......
 * }
 * ```
 */
class LinkInsertDialog : DialogFragment() {

    companion object {
        private const val ARGS_INPUT = "ARGS_INPUT"

        @JvmStatic
        fun pickInput(result: Bundle) = result.getString(ARGS_INPUT)
    }


    private val args by navArgs<LinkInsertDialogArgs>()
    private var binding: TemplatesLinkInsertDialogBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TemplatesLinkInsertDialogBinding.inflate(
            inflater,
            container,
            false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.templatesLinkInsertDialogClose?.setOnClickListener { dismiss() }
        binding?.templatesLinkInsertDialogRegister?.setOnClickListener {
            dismiss()
            setFragmentResult(
                args.requestKey,
                bundleOf(
                    ARGS_INPUT to binding?.templatesLinkInsertDialogInput?.input
                )
            )
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        }
    }

    override fun onPause() {
        super.onPause()
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
