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


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context!!).apply {
            setMessage(arguments?.getString(ARGS_MESSAGE, "読み込み失敗"))
            setTitle("Native のDialog")
        }.create()
    }
}
