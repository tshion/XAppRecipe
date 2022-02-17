package work.shion.xapprecipe

import android.os.StrictMode.VmPolicy
import com.github.tshion.mktools_android.StrictModeActivator
import work.shion.androidpreparation.debugger.android.IWebViewAttacher
import work.shion.androidpreparation.debugger.stetho.IStethoAttacher

class DebugApplication : MainApplication(),
    IStethoAttacher, IWebViewAttacher, StrictModeActivator {

    override val vmPolicy: VmPolicy
        get() = VmPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .build()


    override fun onCreate() {
        super.onCreate()

        activateStrictMode()
        setupStetho(applicationContext)
        setupWebDebugger()
    }
}
