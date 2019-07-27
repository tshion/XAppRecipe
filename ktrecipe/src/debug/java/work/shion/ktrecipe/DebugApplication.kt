package work.shion.ktrecipe

import work.shion.baser.stetho.IStethoAttacher


/**
 * デバッグ用アプリ状態の管理
 */
class DebugApplication : MainApplication(),
    IStethoAttacher {

    override fun onCreate() {
        super.onCreate()

        // Stetho 設定
        attachStetho(applicationContext)
    }
}
