package work.shion.xapprecipe.templates

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import work.shion.xapprecipe.R

/**
 * ログアウト完了ダイアログ
 *
 * ## Example
 * ### ダイアログの呼び出し
 * ``` kotlin
 * activity.let { Navigation.findNavController(it, R.id.entrypoint) }
 *     .navigate(NavEntrypointDirections.navactShowLogoutFinishDialog("request key"))
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
 */
class LogoutFinishDialog : DialogFragment() {

    private val args by navArgs<LogoutFinishDialogArgs>()


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.TemplatesLogoutFinishDialog)
            .setMessage(R.string.templates_logout_finish_dialog_message)
            .setPositiveButton(R.string.templates_logout_finish_dialog_positive, null)
            .setTitle(R.string.templates_logout_finish_dialog_title)
            .create()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        setFragmentResult(args.requestKey, bundleOf())
    }
}
