package work.shion.androidrecipe

import work.shion.androidpreparation.debugger.IStethoAttacher
import work.shion.androidpreparation.debugger.IStrictModeAttacher
import work.shion.androidpreparation.debugger.IWebViewAttacher

class DebugApplication : MainApplication(),
    IStethoAttacher, IStrictModeAttacher, IWebViewAttacher {

    override fun onCreate() {
        super.onCreate()

        setupThreadPolicy()
        setupVmPolicy()
        setupStetho(this)
        setupWebDebugger()
    }
}
