package work.shion.xapprecipe

import android.app.Application
import androidx.work.Configuration
import java.lang.ref.WeakReference

open class MainApplication : Application(), Configuration.Provider {

    var provider: ModelProvider? = null


    override fun onCreate() {
        super.onCreate()

        provider = ModelProvider(
            appContext = WeakReference(applicationContext),
        )
    }


    override fun getWorkManagerConfiguration() = Configuration.Builder()
        .setWorkerFactory(
            MainWorkerFactory(
                showPdfUseCase = provider!!.showPdfUseCase,
            )
        )
        .build()
}
