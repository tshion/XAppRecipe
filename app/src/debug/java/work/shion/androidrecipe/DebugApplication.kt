package work.shion.androidrecipe

import android.os.StrictMode

class DebugApplication : MainApplication() {

    override fun onCreate() {
        super.onCreate()

        // StrictMode の有効化
        StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
                .also { policy -> StrictMode.setThreadPolicy(policy) }
        StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
                .also { policy -> StrictMode.setVmPolicy(policy) }
    }
}
