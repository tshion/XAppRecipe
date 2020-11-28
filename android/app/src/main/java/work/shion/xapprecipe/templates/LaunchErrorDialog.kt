package work.shion.xapprecipe.templates

import android.app.Activity.RESULT_OK
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


        @Deprecated("JetPack Navigation からの呼び出しを検討してください")
        @JvmStatic
        fun newInstance(
            requestCode: Int,
            targetFragment: Fragment? = null
        ) = LaunchErrorDialog().apply {
            setTargetFragment(targetFragment, requestCode)
        }
    }


    private var listener: DialogResultContract? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as? DialogResultContract
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.TemplatesLaunchErrorDialog)
            .setCancelable(false)
            .setMessage(R.string.templates_launch_error_dialog_message)
            .setPositiveButton(R.string.templates_launch_error_dialog_positive) { _, _ ->
                listener?.onDialogResult(
                    targetRequestCode,
                    RESULT_OK,
                    null
                )
                dismiss()
            }
            .setTitle(R.string.templates_launch_error_dialog_title)
            .create()
    }
}
