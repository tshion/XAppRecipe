package work.shion.xapprecipe

import work.shion.androidpreparation.debugger.IDebugger

class DebugApplication : MainApplication(), IDebugger {

    override fun onCreate() {
        super.onCreate()
        setup(applicationContext)
    }
}
