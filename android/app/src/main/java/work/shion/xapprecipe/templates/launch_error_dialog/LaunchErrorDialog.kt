package work.shion.xapprecipe.templates.launch_error_dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import work.shion.xapprecipe.R

/**
 * 起動失敗ダイアログ
 */
class LaunchErrorDialog : DialogFragment() {

    private val viewModel by activityViewModels<LaunchErrorDialogViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.TemplatesLaunchErrorDialog)
            .setMessage(R.string.templates_launch_error_dialog_message)
            .setPositiveButton(R.string.templates_launch_error_dialog_positive) { _, _ ->
                viewModel.isCalledRetry.value = true
                dismiss()
            }
            .setTitle(R.string.templates_launch_error_dialog_title)
            .create()
    }
}
