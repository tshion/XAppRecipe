package work.shion.xapprecipe.templates.logout_finish_dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogoutFinishDialogViewModel : ViewModel() {

    /**
     * ダイアログが消された
     */
    val isCalledDismiss = MutableLiveData<Boolean>()
}
