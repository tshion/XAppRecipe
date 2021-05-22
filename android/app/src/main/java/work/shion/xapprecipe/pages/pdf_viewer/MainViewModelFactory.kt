package work.shion.xapprecipe.pages.pdf_viewer

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import okhttp3.OkHttpClient
import java.lang.ref.WeakReference
import work.shion.xapprecipe_data.apiWeb.Api as ApiWeb

class MainViewModelFactory(
    private val appContext: WeakReference<Context>,
    private val viewer: WeakReference<MainViewContract>
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            apiWeb = ApiWeb(
                client = OkHttpClient(),
            ),
            appContext = appContext,
            viewer = viewer,
        ).let { it as T }
    }
}
