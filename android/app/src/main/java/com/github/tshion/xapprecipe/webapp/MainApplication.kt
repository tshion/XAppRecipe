package com.github.tshion.xapprecipe.webapp

import android.app.Application
import androidx.work.Configuration
import androidx.work.DelegatingWorkerFactory
import java.lang.ref.WeakReference

open class MainApplication : Application(), Configuration.Provider {

    var provider: ModelProvider? = null


    override fun onCreate() {
        super.onCreate()

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
