package work.shion.xapprecipe.templates

import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import work.shion.xapprecipe.R

/**
 * ログアウト確認ダイアログ
 *
 * ## Example
 * ### ダイアログの呼び出し
 * ``` kotlin
 * activity.let { Navigation.findNavController(it, R.id.entrypoint) }
 *     .navigate(NavEntrypointDirections.navactShowLogoutConfirmDialog("request key"))
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
 *         setFragmentResultListener("request key") { _, _ ->
 *             // Do something
 *         }
 *     }
 *
 *     ......
 * }
 * ```
 *
 * @return 肯定的な選択をした場合に結果を通知する
 */
class LogoutConfirmDialog : DialogFragment() {

    private val args by navArgs<LogoutConfirmDialogArgs>()


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.TemplatesLogoutConfirmDialog)
            .setMessage(R.string.templates_logout_confirm_dialog_message)
            .setNegativeButton(R.string.templates_logout_confirm_dialog_negative, null)
            .setPositiveButton(R.string.templates_logout_confirm_dialog_positive) { _, _ ->
                dismiss()
                setFragmentResult(args.requestKey, bundleOf())
            }
            .setTitle(R.string.templates_logout_confirm_dialog_title)
            .create()
    }

    override fun onPause() {
        super.onPause()
        dismiss()
    }
}
