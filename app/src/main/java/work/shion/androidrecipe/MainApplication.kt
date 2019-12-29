package work.shion.androidrecipe

import android.app.Application
import timber.log.Timber

open class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // ログ設定
        Timber.plant(if (BuildConfig.DEBUG) {
            Timber.DebugTree()
        } else {
            object : Timber.Tree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                }
            }
        })
    }
}
