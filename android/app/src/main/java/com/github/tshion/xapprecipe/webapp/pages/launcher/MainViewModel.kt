package com.github.tshion.xapprecipe.webapp.pages.launcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference
import java.util.*

class MainViewModel(
    private val viewer: WeakReference<MainViewContract>
) : ViewModel(), MainActionContract {

    /** タスクキャンセル */
    override fun cancelTasks() = viewModelScope.coroutineContext.cancelChildren()

    /**
     * 表示ページの判定
     */
    override fun judgePage() {
        viewModelScope.launch {
            delay(1500)
            val random = Random(Date().time).nextInt(100)
            when {
                random < 10 -> viewer.get()?.showLaunchErrorDialog()
                random < 40 -> viewer.get()?.goBeginner()
                else -> viewer.get()?.goLoyalty()
            }
        }
    }
}
