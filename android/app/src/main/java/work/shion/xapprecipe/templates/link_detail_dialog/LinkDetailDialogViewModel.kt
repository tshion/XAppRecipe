package work.shion.xapprecipe.templates.link_detail_dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LinkDetailDialogViewModel : ViewModel() {

    /**
     * 削除要求された
     */
    val isCalledDelete = MutableLiveData<Boolean>()
}
