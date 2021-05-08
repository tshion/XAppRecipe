package work.shion.xapprecipe.pages.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import work.shion.xapprecipe.R
import work.shion.xapprecipe_core.entities.LoginEntity
import work.shion.xapprecipe_core.errors.LoginException
import work.shion.xapprecipe_core.usecases.CertifyAccountUseCaseContract
import java.lang.ref.WeakReference

class MainViewModel(
    private val certifyAccountUseCaseContract: CertifyAccountUseCaseContract,
    private val context: WeakReference<Context>,
    private val viewer: WeakReference<MainViewContract>
) : ViewModel(), MainActionContract {

    /**
     * ログインの実行
     */
    override fun doLogin(id: String?, pw: String?) {
        viewModelScope.launch {
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
