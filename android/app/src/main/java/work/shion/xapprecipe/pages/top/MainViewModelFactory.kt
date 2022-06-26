package work.shion.xapprecipe.pages.top

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import work.shion.xapprecipe_core.usecases.CertifyAccountUseCaseContract
import java.lang.ref.WeakReference

class MainViewModelFactory(
    private val certifyAccountUseCase: CertifyAccountUseCaseContract,
    private val viewer: WeakReference<MainViewContract>,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            certifyAccountUseCase = certifyAccountUseCase,
            viewer = viewer,
        ).let { it as T }
    }
}
