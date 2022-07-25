package work.shion.xapprecipe

import android.app.Application
import androidx.work.Configuration
import androidx.work.DelegatingWorkerFactory
import com.github.tshion.xapprecipe.BuildConfig
import com.github.tshion.xapprecipe_data.DataProvider
import java.lang.ref.WeakReference

open class MainApplication : Application(), Configuration.Provider {

    lateinit var dataProvider: DataProvider
    var provider: ModelProvider? = null


    override fun onCreate() {
        super.onCreate()

        dataProvider = DataProvider(
            baseUrlXAppV1Api = BuildConfig.API_XAPP_V1,
            isDevelopment = BuildConfig.DEBUG,
        )
        provider = ModelProvider(
            appContext = WeakReference(applicationContext),
        )
    }


    override fun getWorkManagerConfiguration(): Configuration {
        val factory = DelegatingWorkerFactory()
        factory.addFactory(
            MainWorkerFactory(
                showPdfUseCase = provider!!.showPdfUseCase,
            )
        )

        return Configuration.Builder()
            .setWorkerFactory(factory)
            .build()
    }
}
