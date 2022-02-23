package work.shion.xapprecipe

import com.github.tshion.mktools_android.StrictModeActivator
import work.shion.androidpreparation.debugger.android.IWebViewAttacher
import work.shion.androidpreparation.debugger.stetho.IStethoAttacher

class DebugApplication : MainApplication(),
    IStethoAttacher, IWebViewAttacher, StrictModeActivator {

    override fun onCreate() {
        super.onCreate()

        activateStrictMode()
        setupStetho(applicationContext)
        setupWebDebugger()
    }
}
