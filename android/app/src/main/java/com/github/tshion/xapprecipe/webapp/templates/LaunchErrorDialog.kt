package com.github.tshion.xapprecipe.webapp.templates

import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.github.tshion.xapprecipe.webapp.R

/**
 * 起動失敗ダイアログ
 *
 * ## Example
 * ### ダイアログの呼び出し
 * ``` kotlin
 * activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
 *     ?.navigate(NavEntrypointDirections.navactShowLaunchErrorDialog("request key"))
 * ```
 *
 * ### ダイアログ選択結果の受け取り
 * ``` kotlin
 * class Xxx : Fragment() {
 *
 *     ......
 *
 *     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *         super.onViewCreated(view, savedInstanceState)
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
class LaunchErrorDialog : DialogFragment() {

    private val args by navArgs<LaunchErrorDialogArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.TemplatesLaunchErrorDialog)
            .setMessage(R.string.templates_launch_error_dialog_message)
            .setPositiveButton(R.string.templates_launch_error_dialog_positive) { _, _ ->
                dismiss()
                setFragmentResult(args.requestKey, bundleOf())
            }
            .setTitle(R.string.templates_launch_error_dialog_title)
            .create()
    }
}
