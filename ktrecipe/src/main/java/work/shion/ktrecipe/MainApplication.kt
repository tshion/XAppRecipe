package work.shion.ktrecipe

import android.app.Application
import timber.log.Timber
import work.shion.baser.timber.ITimberAttacher

/**
 * アプリ状態の管理
 */
open class MainApplication : Application(),
        ITimberAttacher {

    override fun onCreate() {
        super.onCreate()

        setupTimber(BuildConfig.DEBUG)
    }


    /**
     * 本番用のTimber 設定の取得
     */
    override fun getProductTree(): Timber.Tree {
        return object : Timber.Tree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            }
        }
    }
}
