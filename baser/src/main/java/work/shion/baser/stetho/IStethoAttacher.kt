package work.shion.baser.stetho

import android.content.Context
import com.facebook.stetho.Stetho


/**
 * Stetho の機能付与
 * @see <a href="https://github.com/facebook/stetho">facebook/stetho</a>
 */
interface IStethoAttacher {

    /**
     * Stetho の組み込み
     *
     * @param appContext アプリケーションコンテキスト
     */
    fun attachStetho(appContext: Context) {
        Stetho.newInitializerBuilder(appContext)
            .enableDumpapp(Stetho.defaultDumperPluginsProvider(appContext))
            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(appContext))
            .build()
            .also { builder -> Stetho.initialize(builder) }
    }
}
