package work.shion.androidrecipe

import work.shion.androidpreparation.debugger.IStethoAttacher
import work.shion.androidpreparation.debugger.IStrictModeAttacher

class DebugApplication : MainApplication(),
        IStethoAttacher, IStrictModeAttacher {

    override fun onCreate() {
        super.onCreate()

        setupThreadPolicy()
        setupVmPolicy()
        super<IStethoAttacher>.setup(this)
    }
}
