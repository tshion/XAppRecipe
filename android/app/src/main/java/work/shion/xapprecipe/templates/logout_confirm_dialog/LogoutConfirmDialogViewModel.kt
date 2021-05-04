package work.shion.xapprecipe.templates.logout_confirm_dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogoutConfirmDialogViewModel : ViewModel() {

    /**
     * ダイアログが消された
     */
    val isCalledDismiss = MutableLiveData<Boolean>()
}
