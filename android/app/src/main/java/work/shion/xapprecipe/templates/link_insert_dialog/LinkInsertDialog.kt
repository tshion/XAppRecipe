package work.shion.xapprecipe.templates.link_insert_dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import work.shion.xapprecipe.databinding.TemplatesLinkInsertDialogBinding

/**
 * リンク登録ダイアログ
 *
 * ## Example
 * ### ダイアログの呼び出し
 * ``` kotlin
 * activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
 *     ?.navigate(NavEntrypointDirections.navactShowLinkInsertDialog())
 * ```
 *
 * ### ダイアログ選択結果の受け取り
 * ``` kotlin
 * class Xxx : Fragment() {
 *     private val linkInsertDialogViewModel by activityViewModels<LinkInsertDialogViewModel>()
 *
 *     ......
 *
 *     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *         super.onViewCreated(view, savedInstanceState)
 *
 *         linkInsertDialogViewModel.input.observe(viewLifecycleOwner) {
 *             if (!it.isNullOrBlank()) {
 *                  linkInsertDialogViewModel.input.value = null
 *             }
 *         }
 *     }
 * }
 * ```
 */
class LinkInsertDialog : DialogFragment() {

    private var binding: TemplatesLinkInsertDialogBinding? = null
    private val viewModel by activityViewModels<LinkInsertDialogViewModel>()


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
            viewModel.input.value = binding?.templatesLinkInsertDialogInput?.input
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
