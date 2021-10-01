package work.shion.xapprecipe

import android.app.Application
import java.lang.ref.WeakReference

open class MainApplication : Application() {

    var provider: ModelProvider? = null


    override fun onCreate() {
        super.onCreate()

        provider = ModelProvider(
            appContext = WeakReference(applicationContext),
        )
    }
}
