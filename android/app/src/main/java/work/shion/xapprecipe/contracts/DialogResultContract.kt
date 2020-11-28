package work.shion.xapprecipe.contracts

import android.content.Intent

/**
 * ダイアログ選択結果受け取り方法の定義
 */
interface DialogResultContract {

    /**
     * ダイアログ選択結果の受け取り
     */
    fun onDialogResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    )
}
