package work.shion.baser.strictmode

import android.os.StrictMode

/**
 * StrictMode の機能付与
 * @see <a href="https://developer.android.com/reference/android/os/StrictMode">StrictMode</a>
 */
interface IStrictModeAttacher {
    /**
     * ThreadPolicy のセットアップ
     */
    fun setupThreadPolicy() {
        StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
                .also { policy -> StrictMode.setThreadPolicy(policy) }
    }

    /**
     * VmPolicy のセットアップ
     */
    fun setupVmPolicy() {
        StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
                .also { policy -> StrictMode.setVmPolicy(policy) }
    }
}
