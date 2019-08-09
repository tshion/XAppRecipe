package work.shion.ktrecipe.pages.websample.views

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


/**
 * メッセージ表示ダイアログ
 */
class MessageDialog : DialogFragment() {

    companion object {
        val TAG = MessageDialog::class.java.simpleName

        private val ARGS_MESSAGE = "ARGS_MESSAGE"


        /**
         * インスタンス生成
         *
         * @param message 表示文言
         */
        fun newInstance(
                message: String
        ) = MessageDialog().also { dialog ->
            dialog.arguments = Bundle().apply {
                putString(ARGS_MESSAGE, message)
            }
        }
    }


    /**
     * Override to build your own custom Dialog container.  This is typically
     * used to show an MessageDialog instead of a generic Dialog; when doing so,
     * [.onCreateView] does not need
     * to be implemented since the MessageDialog takes care of its own content.
     *
     *
     * This method will be called after [.onCreate] and
     * before [.onCreateView].  The
     * default implementation simply instantiates and returns a [Dialog]
     * class.
     *
     *
     * *Note: DialogFragment own the [ Dialog.setOnCancelListener][Dialog.setOnCancelListener] and [ Dialog.setOnDismissListener][Dialog.setOnDismissListener] callbacks.  You must not set them yourself.*
     * To find out about these events, override [.onCancel]
     * and [.onDismiss].
     *
     * @param savedInstanceState The last saved instance state of the Fragment,
     * or null if this is a freshly created Fragment.
     *
     * @return Return a new Dialog instance to be displayed by the Fragment.
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context!!).apply {
            setMessage(arguments?.getString(ARGS_MESSAGE, "読み込み失敗"))
            setTitle("Native のDialog")
        }.create()
    }
}
