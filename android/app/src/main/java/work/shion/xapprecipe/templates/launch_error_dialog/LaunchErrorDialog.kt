package work.shion.xapprecipe.templates.launch_error_dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import work.shion.xapprecipe.R
import work.shion.xapprecipe.contracts.DialogResultContract

/**
 * 起動失敗ダイアログ
 */
class LaunchErrorDialog : DialogFragment() {

    companion object {
        val TAG: String = LaunchErrorDialog::class.java.simpleName


        @JvmStatic
        fun newInstance(
            requestCode: Int,
            targetFragment: Fragment? = null
        ) = LaunchErrorDialog().apply {
            setTargetFragment(targetFragment, requestCode)
        }
    }


    private var listener: DialogResultContract? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCancelable = false
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = (targetFragment ?: context) as? DialogResultContract
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.TemplatesLaunchErrorDialog)
            .setMessage(R.string.templates_launch_error_dialog_message)
            .setPositiveButton(R.string.templates_launch_error_dialog_positive) { _, _ ->
                listener?.onDialogResult(
                    targetRequestCode,
                    Activity.RESULT_OK,
                    null
                )
                dismiss()
            }
            .setTitle(R.string.templates_launch_error_dialog_title)
            .create()
    }
}
