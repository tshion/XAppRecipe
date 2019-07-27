package work.shion.ktrecipe

import work.shion.strictmode.IStrictModeAttacher


/**
 * デバッグ用アプリ状態の管理
 */
class DebugApplication : MainApplication(),
    IStrictModeAttacher {

    override fun onCreate() {
        super.onCreate()

        // StrictMode の有効化
        setupThreadPolicy()
        setupVmPolicy()
    }
}
