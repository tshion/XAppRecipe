package work.shion.xapprecipe.templates.launch_error_dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LaunchErrorDialogViewModel : ViewModel() {

    /**
     * リトライが呼び出された
     */
    val isCalledRetry = MutableLiveData<Boolean>()
}
