package work.shion.javarecipe.pages.websample.views;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


/**
 * メッセージ表示ダイアログ
 */
public class MessageDialog extends DialogFragment {
    public static final String TAG = MessageDialog.class.getSimpleName();

    private static final String ARGS_MESSAGE = "ARGS_MESSAGE";


    /**
     * インスタンス生成
     *
     * @param message 表示文言
     */
    public static MessageDialog newInstance(
            String message
    ) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGS_MESSAGE, message);

        MessageDialog dialog = new MessageDialog();
        dialog.setArguments(bundle);
        return dialog;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getArguments().getString(ARGS_MESSAGE, "読み込み失敗"));
        builder.setTitle("Native のDialog");
        return builder.create();
    }
}
