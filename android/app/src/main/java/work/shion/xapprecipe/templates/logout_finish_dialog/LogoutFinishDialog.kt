package work.shion.xapprecipe.templates.logout_finish_dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import work.shion.xapprecipe.R

/**
 * ログアウト完了ダイアログ
 *
 * ## Example
 * ### ダイアログの呼び出し
 * ``` kotlin
 * activity.let { Navigation.findNavController(it, R.id.entrypoint) }
 *     .navigate(NavEntrypointDirections.navactShowLogoutFinishDialog())
 * ```
 *
 * ### ダイアログ選択結果の受け取り
 * ``` kotlin
 * class Xxx : Fragment() {
 *     private val logoutFinishDialogViewModel by activityViewModels<LogoutFinishDialogViewModel>()
 *
 *     ......
 *
 *     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *         super.onViewCreated(view, savedInstanceState)
 *
 *         logoutFinishDialogViewModel.isCalledDismiss.observe(viewLifecycleOwner) {
 *             if (it) {
 *                 logoutFinishDialogViewModel.isCalledRetry.value = false
 *             }
 *         }
 *     }
 * }
 * ```
 */
class LogoutFinishDialog : DialogFragment() {

    private val viewModel by activityViewModels<LogoutFinishDialogViewModel>()


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.TemplatesLogoutFinishDialog)
            .setMessage(R.string.templates_logout_finish_dialog_message)
            .setPositiveButton(R.string.templates_logout_finish_dialog_positive, null)
            .setTitle(R.string.templates_logout_finish_dialog_title)
            .create()
    }

    override fun onDismiss(dialog: DialogInterface) {
        viewModel.isCalledDismiss.value = true
        super.onDismiss(dialog)
    }
}
