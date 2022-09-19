package work.shion.xapprecipe.pages.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import work.shion.xapprecipe_core.usecases.CertifyAccountUseCaseContract
import java.lang.ref.WeakReference

class MainViewModelFactory(
    private val certifyAccountUseCaseContract: CertifyAccountUseCaseContract,
    private val context: WeakReference<Context>,
    private val viewer: WeakReference<MainViewContract>
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            certifyAccountUseCaseContract = certifyAccountUseCaseContract,
            context = context,
            viewer = viewer,
        ).let { it as T }
    }
}
