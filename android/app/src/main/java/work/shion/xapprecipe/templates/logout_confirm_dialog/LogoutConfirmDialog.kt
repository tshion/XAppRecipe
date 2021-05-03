package work.shion.xapprecipe.templates.logout_confirm_dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import work.shion.xapprecipe.R

/**
 * ログアウト確認ダイアログ
 *
 * ## Example
 * ### ダイアログの呼び出し
 * ``` kotlin
 * activity.let { Navigation.findNavController(it, R.id.entrypoint) }
 *     .navigate(NavEntrypointDirections.navactShowLogoutConfirmDialog())
 * ```
 *
 * ### ダイアログ選択結果の受け取り
 * ``` kotlin
 * class Xxx : Fragment() {
 *     private val logoutConfirmDialogViewModel by activityViewModels<LogoutConfirmDialogViewModel>()
 *
 *     ......
 *
 *     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *         super.onViewCreated(view, savedInstanceState)
 *
 *         logoutConfirmDialogViewModel.isCalledDismiss.observe(viewLifecycleOwner) {
 *             if (it) {
 *                 logoutConfirmDialogViewModel.isCalledRetry.value = false
 *             }
 *         }
 *     }
 * }
 * ```
 */
class LogoutConfirmDialog : DialogFragment() {

    private val viewModel by activityViewModels<LogoutConfirmDialogViewModel>()


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.TemplatesLogoutConfirmDialog)
            .setMessage(R.string.templates_logout_confirm_dialog_message)
            .setNegativeButton(R.string.templates_logout_confirm_dialog_negative, null)
            .setPositiveButton(R.string.templates_logout_confirm_dialog_positive) { _, _ ->
                viewModel.isCalledDismiss.value = true
                dismiss()
            }
            .setTitle(R.string.templates_logout_confirm_dialog_title)
            .create()
    }

    override fun onPause() {
        super.onPause()
        dismiss()
    }
}
