package com.github.tshion.xapprecipe.webapp.pages.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import com.github.tshion.xapprecipe.webapp.R
import com.github.tshion.xapprecipe.webapp_core.entities.LoginEntity
import com.github.tshion.xapprecipe.webapp_core.errors.LoginException
import com.github.tshion.xapprecipe.webapp_core.usecases.CertifyAccountUseCaseContract
import java.lang.ref.WeakReference

class MainViewModel(
    private val certifyAccountUseCaseContract: CertifyAccountUseCaseContract,
    private val context: WeakReference<Context>,
    private val viewer: WeakReference<MainViewContract>
) : ViewModel(), MainActionContract {

    /** タスクキャンセル */
    override fun cancelTasks() = viewModelScope.coroutineContext.cancelChildren()

    /**
     * ログインの実行
     */
    override fun doLogin(id: String?, pw: String?) {
        viewModelScope.launch {
            viewer.get()?.reflectLoading(true)
            try {
                LoginEntity(id, pw)
                    .also { certifyAccountUseCaseContract.login(it) }
                viewer.get()?.goTop()
            } catch (error: LoginException) {
                viewer.get()?.reflectError(
                    idError = error.idErrors?.mapNotNull {
                        when (it) {
                            is IllegalArgumentException -> context.get()
                                ?.getString(R.string.pages_login_error_id_invalid)
                            is NullPointerException -> context.get()
                                ?.getString(R.string.pages_login_error_empty)
                            else -> null
                        }
                    }?.joinToString(separator = "") { "・${it}\n" },
                    pwError = error.pwErrors?.mapNotNull {
                        when (it) {
                            is IllegalArgumentException -> context.get()
                                ?.getString(R.string.pages_login_error_pw_invalid)
                            is NullPointerException -> context.get()
                                ?.getString(R.string.pages_login_error_empty)
                            else -> null
                        }
                    }?.joinToString(separator = "") { "・${it}\n" },
                )
            } finally {
                viewer.get()?.reflectLoading(false)
            }
        }
    }

    /**
     * スキップの実行
     */
    override fun doSkip() {
        viewer.get()?.goTop()
    }
}
